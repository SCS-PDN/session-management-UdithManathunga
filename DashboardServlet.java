import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

// Assuming Course model is in the same package or properly imported
import models.Course;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Check if user is logged in (session)
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.html");
            return;
        }

        // 2. Create a list of courses (hardcoded)
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("101", "Web Development", "Prof. Smith"));
        courseList.add(new Course("102", "Data Structures", "Dr. Jones"));
        courseList.add(new Course("103", "Database Systems", "Dr. Brown"));

        // 3. Store courses in request attribute
        request.setAttribute("courseList", courseList);

        // 4. Forward to dashboard.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
