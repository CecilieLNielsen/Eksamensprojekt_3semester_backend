package rest;

import DTO.BreedCombinedDTO;
import DTO.BreedFactsDTO;
import DTO.BreedImageDTO;
import DTO.BreedInformationDTO;
import DTO.DogDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import errorhandling.NotFoundException;
import utils.EMF_Creator;
import facades.DogFacade;
import java.io.IOException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.HttpUtils;

@Path("dog")
public class DogResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final DogFacade FACADE = DogFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private SecurityContext securityContext;
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public String addDog(String dog) {
        String username = securityContext.getUserPrincipal().getName();
        DogDTO dogDTO = GSON.fromJson(dog, DogDTO.class);
        return GSON.toJson(FACADE.addDog(dogDTO, username));
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public String getAllDogsByUser() throws NotFoundException {
        String username = securityContext.getUserPrincipal().getName();
        return GSON.toJson(FACADE.getAllDogsByUser(username));
    }
    
    @GET
    @Path("/breeds")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllBreeds() throws IOException {
        return HttpUtils.fetchData("https://dog-info.cooljavascript.dk/api/breed");
    }
    
    @GET
    @Path("/breeds/{breed}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(@PathParam("breed") String breed) throws IOException {

        String breedInformation = HttpUtils.fetchData("https://dog-info.cooljavascript.dk/api/breed/" + breed);
        BreedInformationDTO breedInformationDTO = GSON.fromJson(breedInformation, BreedInformationDTO.class);

        String breedImage = HttpUtils.fetchData("https://dog-image.cooljavascript.dk/api/breed/random-image/" + breed);
        BreedImageDTO breedImageDTO = GSON.fromJson(breedImage, BreedImageDTO.class);
        
        String breedFacts = HttpUtils.fetchData("https://dog-api.kinduff.com/api/facts");
        BreedFactsDTO breedFactsDTO = GSON.fromJson(breedFacts, BreedFactsDTO.class);
        
        BreedCombinedDTO breedCombinedDTO = new BreedCombinedDTO(breedInformationDTO, breedImageDTO, breedFactsDTO);

        String combinedJSON = GSON.toJson(breedCombinedDTO);

        return combinedJSON;
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public String editDog(@PathParam("id") int id, String dog) {
        DogDTO dogDTO = GSON.fromJson(dog, DogDTO.class);
        dogDTO.setId(id);
        return GSON.toJson(FACADE.editDog(dogDTO));
    }
    
    @Path("/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("user")
    public String deleteDog(@PathParam("id") int id) throws NotFoundException {
            return GSON.toJson(FACADE.deleteDog(id));
    }
}
