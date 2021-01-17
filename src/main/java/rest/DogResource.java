package rest;

import DTO.DogDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.DogFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("dog")
public class DogResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final DogFacade FACADE = DogFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @POST
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // @RolesAllowed("user")
    public String addDog(String dog, @PathParam("username") String username) {
        DogDTO dogDTO = GSON.fromJson(dog, DogDTO.class);
        return GSON.toJson(FACADE.addDog(dogDTO, username));
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed("user")
    public String getAllDogsByUser(@PathParam("username") String username) {
        return GSON.toJson(FACADE.GetAllDogsByUser(username));
    }
}
