import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve job seeker and consultant details from the request
        String jobSeekerId = request.getParameter("jobSeekerId");
        String consultantId = request.getParameter("consultantId");
        String date = request.getParameter("date");
        String time = request.getParameter("time");

        // Perform validation and business logic here

        // Assuming you have an Appointment entity class, create a new appointment
        Appointment appointment = new Appointment();
        appointment.setJobSeekerId(jobSeekerId);
        appointment.setConsultantId(consultantId);
        appointment.setDate(date);
        appointment.setTime(time);

        // Save the appointment to the database using your DAO class
        AppointmentDao appointmentDao = new AppointmentDao();
        appointmentDao.createAppointment(appointment);

        // Redirect to a success page
        response.sendRedirect("success.jsp");
    }
}
