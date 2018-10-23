package twitter.web;

import twitter.model.Dashboard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        registerTwitterServlet(event.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void registerTwitterServlet(ServletContext servletContext) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = factory.createEntityManager();
        Dashboard dashboard = new Dashboard(entityManager);

        servletContext.addServlet("TwitterServlet", new TwitterServlet(dashboard)).addMapping("/twitter");
    }
}
