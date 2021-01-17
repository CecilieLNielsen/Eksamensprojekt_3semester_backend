package facades;

import DTO.DogDTO;
import entities.Dog;
import entities.User;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class DogFacadeTest {

    private static EntityManagerFactory emf;
    private static DogFacade facade;

    public DogFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = DogFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
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
            Dog dog2 = new Dog (new DogDTO("Balou", "01/01/2021", "A cute dog", "Golden Retriever"));
            dog1.setUser(user);
            dog2.setUser(user);
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

       @Test
    public void testAddDog() {
        // Arrange
        DogDTO dogDTO = new DogDTO("Togo", "01/01/2021", "A honourable dog", "Golden Retriever");
        
        // Act
       DogDTO result = facade.addDog(dogDTO, "user");
        // Assert
        assertEquals(3, result.getId());
    }
    
      @Test
    public void testGetAllDogsByUser() {
        // Arrange

        // Act
        List<DogDTO> result = facade.GetAllDogsByUser("user");
        // Assert
        assertEquals(2, result.size());
    }
    

}
