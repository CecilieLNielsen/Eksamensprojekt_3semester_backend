package facades;

import DTO.DogDTO;
import entities.Dog;
import entities.User;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class DogFacade {

    private static DogFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private DogFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static DogFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DogFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DogDTO addDog(DogDTO dogDTO, String username) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            Dog dog = new Dog(dogDTO);
            dog.setUser(user);
            em.persist(dog);
            em.getTransaction().commit();
            return new DogDTO(dog);
        } finally {
            em.close();
        }
    }

    public List<DogDTO> getAllDogsByUser(String username) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Dog> query = em.createQuery("SELECT d FROM Dog d WHERE d.user.userName = :username", Dog.class).setParameter("username", username);;
            List<Dog> res = query.getResultList();
            if (res.isEmpty()) {
                throw new NotFoundException("User has no dogs stored");
            }
            List<DogDTO> dogs = new ArrayList();
            for (Dog dog : res) {
                dogs.add(new DogDTO(dog));
            }
            return dogs;
        } finally {
            em.close();
        }
    }

    public DogDTO editDog(DogDTO dogDTO) {
        EntityManager em = getEntityManager();
        Dog dog = em.find(Dog.class, dogDTO.getId());

        dog.setName(dogDTO.getName());
        dog.setDateOfBirth(dogDTO.getDateOfBirth());
        dog.setInfo(dogDTO.getInfo());
        dog.setBreed(dogDTO.getBreedName());

        try {
            em.getTransaction().begin();
            em.merge(dog);
            em.getTransaction().commit();
            return new DogDTO(dog);
        } finally {
            em.close();
        }
    }

    public DogDTO deleteDog(int id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Dog dog = em.find(Dog.class, id);
        if (dog == null) {
            throw new NotFoundException("Could not delete, provided id does not exist");
        }
        try {
            em.getTransaction().begin();
            em.remove(dog);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new DogDTO(dog);
    }

}
