import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String expertise;

    // Constructors, getters, setters, and other methods
    // ...

    // Constructors
    public Consultant() {
    }

    public Consultant(String firstName, String lastName, String email, String phoneNumber, String expertise) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.expertise = expertise;
    }

    // Getters and Setters
    // ...
}
