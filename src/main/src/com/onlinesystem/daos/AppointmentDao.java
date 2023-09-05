import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AppointmentDao {

    private final EntityManagerFactory emf;

    public AppointmentDao() {
        // Initialize the EntityManagerFactory using the persistence unit name
        this.emf = Persistence.createEntityManagerFactory("MyPersistence");
    }

    public void createAppointment(Appointment appointment) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(appointment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Appointment getAppointmentById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Appointment.class, id);
        } finally {
            em.close();
        }
    }

    public List<Appointment> getAppointmentsByJobSeeker(String jobSeekerId) {
        EntityManager em = emf.createEntityManager();
        try {
            // Use a JPQL query to retrieve appointments for a specific job seeker
            return em.createQuery("SELECT a FROM Appointment a WHERE a.jobSeekerId = :jobSeekerId", Appointment.class)
                    .setParameter("jobSeekerId", jobSeekerId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Appointment> getAppointmentsByConsultant(String consultantId) {
        EntityManager em = emf.createEntityManager();
        try {
            // Use a JPQL query to retrieve appointments for a specific consultant
            return em.createQuery("SELECT a FROM Appointment a WHERE a.consultantId = :consultantId", Appointment.class)
                    .setParameter("consultantId", consultantId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Add other CRUD methods as needed

    public void close() {
        emf.close();
    }
}
