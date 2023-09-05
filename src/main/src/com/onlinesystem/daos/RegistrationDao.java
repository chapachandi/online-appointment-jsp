import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RegistrationDao {

    private final EntityManagerFactory emf;

    public RegistrationDao() {
        // Initialize the EntityManagerFactory using the persistence unit name
        this.emf = Persistence.createEntityManagerFactory("MyPersistence");
    }

    public void createRegistration(Registration registration) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(registration);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Registration getRegistrationById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Registration.class, id);
        } finally {
            em.close();
        }
    }

    // Add other CRUD methods as needed

    public void close() {
        emf.close();
    }
}
