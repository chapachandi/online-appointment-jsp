import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt; // Import Bcrypt library
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Retrieve the stored hashed password for the user (from a database or elsewhere)
        String storedHashedPassword = getStoredHashedPassword(username);

        if (storedHashedPassword != null && BCrypt.checkpw(password, storedHashedPassword)) {
            // Password matches the stored hashed password

            // Determine the user type based on the username (you can use your own logic)
            String userType = determineUserType(username);

            if (userType != null) {
                // Login successful, set session attribute for user type
                HttpSession session = request.getSession();
                session.setAttribute("userType", userType);

                // Redirect to the respective dashboard
                response.sendRedirect(userType + "-dashboard.jsp");
            } else {
                // Invalid login, redirect to the login page with an error message
                response.sendRedirect("login.jsp?error=1");
            }
        } else {
            // Invalid login, redirect to the login page with an error message
            response.sendRedirect("login.jsp?error=1");
        }
    }

    // Replace this method with your logic to retrieve hashed passwords from a database
    private String getStoredHashedPassword(String username) {
        // Simulated logic - replace with database access
        if ("admin".equals(username)) {
            return "$2a$12$MqHXNeYxvwN4BQW0YKLcC.yt/lkvQ8H0noUEy8gCwhDfKEXV/kGyW"; // Hashed password for "adminpassword"
        } else if ("consultant".equals(username)) {
            return "$2a$12$gFZ8Vi/kC7cCtV5QXM3pNOe6l64JdKrxym4vIOn3AD4Y44jy8BauK"; // Hashed password for "consultantpassword"
        } else if ("user".equals(username)) {
            return "$2a$12$ekmqzt1nGLP0atJFiTQWfu.TaKZdbREq49CBxxzr8gENe1cO12toO"; // Hashed password for "userpassword"
        } else {
            return null; // User not found
        }
    }

    // Replace this method with your logic to determine the user type based on the username
    private String determineUserType(String username) {
        // Simulated logic - replace with your own user type determination logic
        if ("admin".equals(username)) {
            return "admin";
        } else if ("consultant".equals(username)) {
            return "consultant";
        } else if ("user".equals(username)) {
            return "user";
        } else {
            return null; // User not found
        }
    }
}
