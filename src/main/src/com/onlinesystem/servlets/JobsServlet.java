import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jobs")
public class JobsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private JobsDAO jobsDAO;

    public void init() {
        // Initialize the JobsDAO with your DBConnectionFactory (replace with your implementation)
        jobsDAO = new JobsDAO(DBConnectionFactory.getDAOFactory(DAOFactory.MYSQL));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listJobs(request, response);
                break;
            case "searchByCountry":
                searchByCountry(request, response);
                break;
            case "searchByJobType":
                searchByJobType(request, response);
                break;
            case "add":
                // Display the job listing form (you can implement this separately)
                break;
            case "edit":
                // Display the job editing form (you can implement this separately)
                break;
            case "delete":
                deleteJob(request, response);
                break;
            default:
                listJobs(request, response);
                break;
        }
    }

    private void listJobs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<JobsEntity> jobsList = jobsDAO.getAllJobs();
        request.setAttribute("jobsList", jobsList);
        request.getRequestDispatcher("/job-listing.jsp").forward(request, response);
    }

    private void searchByCountry(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String countryName = request.getParameter("countryName");
        List<JobsEntity> jobsList = jobsDAO.searchByCountry(countryName);
        request.setAttribute("jobsList", jobsList);
        request.getRequestDispatcher("/job-listing.jsp").forward(request, response);
    }

    private void searchByJobType(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobType = request.getParameter("jobType");
        List<JobsEntity> jobsList = jobsDAO.searchByJobType(jobType);
        request.setAttribute("jobsList", jobsList);
        request.getRequestDispatcher("/job-listing.jsp").forward(request, response);
    }

    private void deleteJob(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long jobId = Long.parseLong(request.getParameter("jobId"));
        boolean deleted = jobsDAO.deleteJob(jobId);

        if (deleted) {
            response.sendRedirect(request.getContextPath() + "/jobs?action=list");
        } else {
            // Handle deletion failure
            response.sendRedirect(request.getContextPath() + "/jobs?action=list&deleteError=true");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addJob(request, response);
                    break;
                case "edit":
                    // Implement updating a job listing separately if needed
                    break;
                default:
                    listJobs(request, response);
                    break;
            }
        }
    }

    private void addJob(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data and create a JobsEntity
        String country = request.getParameter("country");
        String jobTitle = request.getParameter("jobTitle");
        String jobType = request.getParameter("jobType");
        String location = request.getParameter("location");
        // Parse other form fields as needed

        JobsEntity job = new JobsEntity(country, jobTitle, jobType, location, new Date(), "Description goes here");

        boolean added = jobsDAO.addJob(job);

        if (added) {
            response.sendRedirect(request.getContextPath() + "/jobs?action=list");
        } else {
            // Handle addition failure
            response.sendRedirect(request.getContextPath() + "/jobs?action=list&addError=true");
        }
    }
}
