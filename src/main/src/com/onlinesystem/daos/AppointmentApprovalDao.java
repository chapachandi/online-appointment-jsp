import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppointmentApprovalDao {

    private final EntityManagerFactory emf;

    public AppointmentApprovalDao() {
        // Initialize the EntityManagerFactory using the persistence unit name
        this.emf = Persistence.createEntityManagerFactory("MyPersistence");
    }

    public void approveAppointment(Appointment appointment) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Update the appointment's approval status
            appointment.setApproved(true);
            em.merge(appointment); // Merge the updated appointment into the persistence context
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void disapproveAppointment(Appointment appointment) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Update the appointment's approval status
            appointment.setApproved(false);
            em.merge(appointment); // Merge the updated appointment into the persistence context
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}
