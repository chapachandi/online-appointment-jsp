import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        // Create a DAO factory and retrieve the DAO
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL); // Replace with your factory implementation
        LoginDAO loginDAO = new LoginDAO(factory);

        // Retrieve the hashed password from the database
        String storedPasswordHash = loginDAO.getPasswordHash(username, userType);

        if (storedPasswordHash != null && BCrypt.checkpw(password, storedPasswordHash)) {
            // Passwords match, user is authenticated
            if ("user".equals(userType)) {
                // Handle user login and redirection
                response.sendRedirect("/user-dashboard.jsp");
            } else if ("consultant".equals(userType)) {
                // Handle consultant login and redirection
                response.sendRedirect("/consultant-dashboard.jsp");
            } else if ("admin".equals(userType)) {
                // Handle admin login and redirection
                response.sendRedirect("/admin-dashboard.jsp");
            }
        } else {
            // Invalid credentials
            response.sendRedirect("/login.jsp?error=true");
        }
    }
}
