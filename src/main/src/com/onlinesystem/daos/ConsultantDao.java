import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ConsultantDao {

    private final EntityManagerFactory emf;

    public ConsultantDao() {
        // Initialize the EntityManagerFactory using the persistence unit name
        this.emf = Persistence.createEntityManagerFactory("MyPersistence");
    }

    public void createConsultant(Consultant consultant) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(consultant);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Consultant getConsultantById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Consultant.class, id);
        } finally {
            em.close();
        }
    }

    public List<Consultant> getAllConsultants() {
        EntityManager em = emf.createEntityManager();
        try {
            // Use a JPQL query to retrieve all consultants
            return em.createQuery("SELECT c FROM Consultant c", Consultant.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void updateConsultant(Consultant consultant) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(consultant);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deleteConsultant(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Consultant consultant = em.find(Consultant.class, id);
            if (consultant != null) {
                em.remove(consultant);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}
