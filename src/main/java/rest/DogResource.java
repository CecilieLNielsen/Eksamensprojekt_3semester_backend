package rest;

import DTO.BreedCombinedDTO;
import DTO.BreedFactsDTO;
import DTO.BreedImageDTO;
import DTO.BreedInformationDTO;
import DTO.DogDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.DogFacade;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.HttpUtils;

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
    
    @Path("/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteDog(@PathParam("id") int id) {
            return GSON.toJson(FACADE.deleteDog(id));
    }
}
