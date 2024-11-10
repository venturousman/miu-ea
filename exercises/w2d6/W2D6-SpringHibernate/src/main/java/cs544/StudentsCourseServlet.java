package cs544;

import java.io.IOException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// controller
@WebServlet(name = "StudentsCourseServlet", urlPatterns = "/StudentsCourseServlet")
public class StudentsCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext context = getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);

		if (applicationContext == null) {
			throw new ServletException("Spring WebApplicationContext could not be found.");
		}

		StudentService studentService = applicationContext.getBean("studentService", StudentService.class);

		if (studentService == null) {
			throw new ServletException("StudentService bean could not be found.");
		}

		String studentIdStr = request.getParameter("studentid");

		long studentid = -1;
		Student student = null;

		if (studentIdStr != null && studentIdStr.matches("\\d+")) {
			studentid = Long.parseLong(studentIdStr);
			// StudentService studentService = new StudentService();
			student = studentService.getStudent(studentid);
		}

		request.setAttribute("student", student);
		request.getRequestDispatcher("student.jsp").forward(request, response);
	}
}
