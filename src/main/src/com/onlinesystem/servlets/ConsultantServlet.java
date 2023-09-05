import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConsultantServlet")
public class ConsultantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConsultantDao consultantDao;

    public void init() {
        consultantDao = new ConsultantDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Default action: List consultants
        }

        switch (action) {
            case "list":
                listConsultants(request, response);
                break;
            case "add":
                // Show the form for adding a new consultant
                request.getRequestDispatcher("add-consultant.jsp").forward(request, response);
                break;
            case "edit":
                // Show the form for editing an existing consultant
                editConsultant(request, response);
                break;
            case "delete":
                // Delete an existing consultant
                deleteConsultant(request, response);
                break;
            default:
                listConsultants(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Default action: List consultants
        }

        switch (action) {
            case "add":
                addConsultant(request, response);
                break;
            case "update":
                updateConsultant(request, response);
                break;
            default:
                listConsultants(request, response);
        }
    }

    private void listConsultants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Consultant> consultants = consultantDao.getAllConsultants();
        request.setAttribute("consultants", consultants);
        request.getRequestDispatcher("list-consultants.jsp").forward(request, response);
    }

    private void addConsultant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve request parameters to create a new Consultant entity
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String expertise = request.getParameter("expertise");

        Consultant newConsultant = new Consultant(firstName, lastName, email, phoneNumber, expertise);
        consultantDao.createConsultant(newConsultant);

        // Redirect to the list page after adding
        response.sendRedirect(request.getContextPath() + "/ConsultantServlet?action=list");
    }

    private void editConsultant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the consultant ID to be edited from the request parameter
        Long consultantId = Long.parseLong(request.getParameter("id"));
        Consultant consultant = consultantDao.getConsultantById(consultantId);

        // Set the consultant as an attribute for the edit page
        request.setAttribute("consultant", consultant);

        // Forward to the edit page
        request.getRequestDispatcher("edit-consultant.jsp").forward(request, response);
    }

    private void updateConsultant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve request parameters for updating the consultant
        Long consultantId = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String expertise = request.getParameter("expertise");

        // Retrieve the existing consultant from the database
        Consultant consultant = consultantDao.getConsultantById(consultantId);

        // Update the consultant's information
        consultant.setFirstName(firstName);
        consultant.setLastName(lastName);
        consultant.setEmail(email);
        consultant.setPhoneNumber(phoneNumber);
        consultant.setExpertise(expertise);

        // Save the updated consultant to the database
        consultantDao.updateConsultant(consultant);

        // Redirect to the list page after updating
        response.sendRedirect(request.getContextPath() + "/ConsultantServlet?action=list");
    }

    private void deleteConsultant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the consultant ID to be deleted from the request parameter
        Long consultantId = Long.parseLong(request.getParameter("id"));

        // Delete the consultant from the database
        consultantDao.deleteConsultant(consultantId);

        // Redirect to the list page after deleting
        response.sendRedirect(request.getContextPath() + "/ConsultantServlet?action=list");
    }

    public void destroy() {
        consultantDao.close();
    }
}
