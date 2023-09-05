import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String userType = request.getParameter("userType");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Create a RegistrationEntity with the retrieved data
        RegistrationEntity user = new RegistrationEntity(name, email, phoneNumber, userType, username, password);

        // Create a DAO factory and retrieve the DAO
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL); // Replace with your factory implementation
        RegistrationDAO registrationDAO = new RegistrationDAO(factory);

        // Register the user
        boolean registrationSuccessful = registrationDAO.registerUser(user);

        if (registrationSuccessful) {
            // Registration was successful, redirect to a success page
            response.sendRedirect("/login.jsp");
        } else {
            // Registration failed, display an error message
            response.sendRedirect("/signup.jsp?error=true");
        }
    }
}
