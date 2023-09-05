import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "jobs") // Specify the table name
public class JobsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specify the primary key generation strategy
    @Column(name = "job_id") // Specify the column name in the table
    private Long id; // Assuming you want an auto-generated ID

    @Column(name = "country") // Specify the column name in the table
    private String country;

    @Column(name = "job_title") // Specify the column name in the table
    private String jobTitle;

    @Column(name = "job_type") // Specify the column name in the table
    private String jobType;

    @Column(name = "location") // Specify the column name in the table
    private String location;

    @Column(name = "published_date") // Specify the column name in the table
    private Date publishedDate;

    @Column(name = "description") // Specify the column name in the table
    private String description;

    // Constructors, getters, and setters

    public JobsEntity() {
        // Default constructor
    }

    public JobsEntity(String country, String jobTitle, String jobType, String location, Date publishedDate, String description) {
        this.country = country;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.location = location;
        this.publishedDate = publishedDate;
        this.description = description;
    }

    // Getter and Setter methods for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
