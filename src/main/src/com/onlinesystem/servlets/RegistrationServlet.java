import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt; // Import Bcrypt library

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String userType = request.getParameter("userType"); // Get the selected user type

        // Hash the password using Bcrypt (replace with your preferred password hashing method)
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Assuming you have a User entity class (similar to the login example)
        UserEntity newUser = new UserEntity(username, hashedPassword, email, userType);

        // Add code to save the new user to the database (use your DAO or JPA for this)
        saveUserToDatabase(newUser);

        // Optionally, set session attributes or redirect to a success page
        HttpSession session = request.getSession();
        session.setAttribute("registrationSuccess", true);
        response.sendRedirect("registration-success.jsp");
    }

    // Add a method to save the new user to the database (similar to the login example)
    private void saveUserToDatabase(UserEntity user) {
        // Add your database logic here (use DAO, JPA, or another method)
    }
}
