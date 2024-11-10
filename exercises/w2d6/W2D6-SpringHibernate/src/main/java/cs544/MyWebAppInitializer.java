package cs544;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

public class MyWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        // Create the Spring 'root' application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(Config.class);
        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // ServletRegistration.Dynamic testServlet = container.addServlet("TestServlet",
        // new HttpServlet() {
        // @Override
        // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
        // IOException {
        // resp.getWriter().write("@@@ Test servlet response");
        // }
        // });
        // if (testServlet != null) {
        // System.out.println("@@@ Test servlet registration returned: " + testServlet);
        // testServlet.addMapping("/test");
        // testServlet.setLoadOnStartup(1);
        // } else {
        // System.out.println("@@@ Test servlet registration returned null");
        // }

        ServletRegistration.Dynamic servlet = container.addServlet("StudentsCourseServlet",
                new StudentsCourseServlet());

        // System.out.println("@@@ Servlet Class: " +
        // StudentsCourseServlet.class.getName());
        System.out.println("@@@ servlet: " + servlet);

        // NOTE: config manually here
        // OR in web.xml
        // OR @WebServlet annotation in the servlet class
        // servlet.addMapping("/StudentsCourseServlet");
    }
}