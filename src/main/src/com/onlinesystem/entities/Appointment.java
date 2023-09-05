import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the appointment

    private String jobSeekerId; // ID of the job seeker scheduling the appointment
    private String consultantId; // ID of the consultant being contacted
    private String date; // Date of the appointment (you can use a more appropriate date type)
    private String time; // Time of the appointment (you can use a more appropriate time type)
    private String status; // Status of the appointment (e.g., "Scheduled", "Canceled", "Completed")
    private boolean isApproved; // Indicates if the appointment is approved by the job seeker

    // Constructors, getters, setters, and other methods
    // ...

    // Constructors
    public Appointment() {
    }

    public Appointment(String jobSeekerId, String consultantId, String date, String time, String status) {
        this.jobSeekerId = jobSeekerId;
        this.consultantId = consultantId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    // Getters and Setters
    // ...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
}
