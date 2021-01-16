package rest;

import DTO.demo.AwesomeDTO;
import DTO.demo.ChuckDTO;
import DTO.demo.CombinedDTO;
import DTO.demo.DadDTO;
import DTO.demo.InsultDTO;
import DTO.demo.PlanetDTO;
import com.google.gson.Gson;
import entities.User;
import java.io.IOException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;
import utils.HttpUtils;

@Path("info")
public class DemoResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("select u from User u", entities.User.class);
            List<User> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }

    @GET
    @Path("data")
    @Produces(MediaType.APPLICATION_JSON)
    public String getData() throws IOException {
        Gson gson = new Gson();

        String chuck = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        ChuckDTO chuckDTO = gson.fromJson(chuck, ChuckDTO.class);

        String dad = HttpUtils.fetchData("https://icanhazdadjoke.com");
        DadDTO dadDTO = gson.fromJson(dad, DadDTO.class);
        
        String insult = HttpUtils.fetchData("https://evilinsult.com/generate_insult.php?lang=en&type=json");
        InsultDTO insultDTO = gson.fromJson(insult, InsultDTO.class);
        
        String awesome = HttpUtils.fetchData("https://www.foaas.com/awesome/Cecilie");
        AwesomeDTO awesomeDTO = gson.fromJson(awesome, AwesomeDTO.class);
        
        String planet = HttpUtils.fetchData("https://swapi.dev/api/planets/1");
        PlanetDTO planetDTO = gson.fromJson(planet, PlanetDTO.class);

        CombinedDTO combinedDTO = new CombinedDTO(chuckDTO, dadDTO, insultDTO, awesomeDTO, planetDTO);

        String combinedJSON = gson.toJson(combinedDTO);

        return combinedJSON;
    }
}
