import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/consultants")
public class ConsultantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConsultantDAO consultantDAO;

    public void init() {
        // Initialize the ConsultantDAO with your DBConnectionFactory (replace with your implementation)
        consultantDAO = new ConsultantDAO(DBConnectionFactory.getDAOFactory(DAOFactory.MYSQL));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listConsultants(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteConsultant(request, response);
                break;
            case "view":
                viewConsultant(request, response);
                break;
            default:
                listConsultants(request, response);
                break;
        }
    }

    private void listConsultants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ConsultantEntity> consultantsList = consultantDAO.getAllConsultants();
        request.setAttribute("consultantsList", consultantsList);
        request.getRequestDispatcher("/consultant-list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display the form for adding a new consultant (you can create a JSP for this)
        request.getRequestDispatcher("/consultant-add-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long consultantId = Long.parseLong(request.getParameter("consultantId"));
        ConsultantEntity consultant = consultantDAO.getConsultantById(consultantId);

        if (consultant != null) {
            request.setAttribute("consultant", consultant);
            request.getRequestDispatcher("/consultant-edit-form.jsp").forward(request, response);
        } else {
            // Handle consultant not found
            response.sendRedirect(request.getContextPath() + "/consultants?action=list&editError=true");
        }
    }

    private void deleteConsultant(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long consultantId = Long.parseLong(request.getParameter("consultantId"));
        boolean deleted = consultantDAO.deleteConsultant(consultantId);

        if (deleted) {
            response.sendRedirect(request.getContextPath() + "/consultants?action=list");
        } else {
            // Handle deletion failure
            response.sendRedirect(request.getContextPath() + "/consultants?action=list&deleteError=true");
        }
    }

    private void viewConsultant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long consultantId = Long.parseLong(request.getParameter("consultantId"));
        ConsultantEntity consultant = consultantDAO.getConsultantById(consultantId);

        if (consultant != null) {
            request.setAttribute("consultant", consultant);
            request.getRequestDispatcher("/consultant-view.jsp").forward(request, response);
        } else {
            // Handle consultant not found
            response.sendRedirect(request.getContextPath() + "/consultants?action=list&viewError=true");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addConsultant(request, response);
                    break;
                case "edit":
                    editConsultant(request, response);
                    break;
                default:
                    listConsultants(request, response);
                    break;
            }
        }
    }

    private void addConsultant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data and create a new ConsultantEntity
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String userType = request.getParameter("userType");
        String jobType = request.getParameter("jobType");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ConsultantEntity consultant = new ConsultantEntity(name, email, phoneNumber, userType, jobType, username, password);

        boolean added = consultantDAO.addConsultant(consultant);

        if (added) {
            response.sendRedirect(request.getContextPath() + "/consultants?action=list");
        } else {
            // Handle addition failure
            response.sendRedirect(request.getContextPath() + "/consultants?action=list&addError=true");
        }
    }

    private void editConsultant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long consultantId = Long.parseLong(request.getParameter("consultantId"));
        ConsultantEntity consultant = consultantDAO.getConsultantById(consultantId);

        if (consultant != null) {
            // Retrieve form data for editing
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String userType = request.getParameter("userType");
            String jobType = request.getParameter("jobType");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Update the consultant entity
            consultant.setName(name);
            consultant.setEmail(email);
            consultant.setPhoneNumber(phoneNumber);
            consultant.setUserType(userType);
            consultant.setJobType(jobType);
            consultant.setUsername(username);
            consultant.setPassword(password);

            boolean updated = consultantDAO.updateConsultant(consultant);

            if (updated) {
                response.sendRedirect(request.getContextPath() + "/consultants?action=list");
            } else {
                // Handle update failure
                response.sendRedirect(request.getContextPath() + "/consultants?action=edit&consultantId=" + consultantId + "&editError=true");
            }
        } else {
            // Handle consultant not found
            response.sendRedirect(request.getContextPath() + "/consultants?action=list&editError=true");
        }
    }
}
