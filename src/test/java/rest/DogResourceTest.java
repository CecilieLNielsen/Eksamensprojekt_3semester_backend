package rest;

import DTO.DogDTO;
import entities.Dog;
import entities.User;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test

//@Disabled
public class DogResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private static String securityToken;

    private static void login() {
        String json = String.format("{username: \"%s\", password: \"%s\"}", "user", "user123");
        securityToken = given()
                .contentType("application/json")
                .body(json)
                .when().post("/login")
                .then()
                .extract().path("token");
    }

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Dog").executeUpdate();
            em.createQuery("DELETE from User").executeUpdate();
            em.createNativeQuery("ALTER TABLE `DOG` AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("ALTER TABLE `USER` AUTO_INCREMENT = 1").executeUpdate();
            User user = new User("user", "user123");
            Dog dog1 = new Dog(new DogDTO("Sofus", "01/01/2021", "A happy dog", "Golden Retriever"));
            Dog dog2 = new Dog(new DogDTO("Balou", "01/01/2021", "A cute dog", "Golden Retriever"));
            dog1.setUser(user);
            dog2.setUser(user);
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void serverIsRunning() {
        given().when().get("/dog").then().statusCode(404);
    }

    @Test
    public void testAddDog() throws Exception {
        String json = "{\n"
                + "    \"name\": \"Balou\", \n"
                + "    \"dateOfBirth\": \"01/01/2021\",\n"
                + "    \"info\" : \"A cute dog\",\n"
                + "    \"breedName\" : \"Golden Retriever\"\n"
                + "}";
        login();
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                //.header("x-access-token", securityToken)
                .body(json)
                .when()
                .post("/dog/user").then()
                .statusCode(200);
    }

    @Test
    public void testGetAllDogsByUser() throws Exception {
        login();
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                //.header("x-access-token", securityToken)
                .when()
                .get("/dog/user").then()
                .statusCode(200);
    }

    @Test
    public void testGetAllBreeds() throws Exception {
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .get("/dog/breeds").then()
                .statusCode(200);
    }

    @Test
    public void testGetBreedInformation() throws Exception {
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .get("/dog/breeds/husky").then()
                .statusCode(200);
    }
    
    @Test
    public void testDeleteDog() throws Exception {
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .delete("/dog/1").then()
                .statusCode(200);
    }

}
