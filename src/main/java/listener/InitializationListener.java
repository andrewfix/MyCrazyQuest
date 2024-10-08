package listener;

import exception.NewQuestException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import service.GameService;

import java.net.URL;

@WebListener
public class InitializationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        try {
            URL fileName = this.getClass().getResource("/scenario.yaml");
            GameService gameService = new GameService(fileName);

            servletContext.setAttribute("gameService", gameService);
            servletContext.setAttribute("contextPath", servletContext.getContextPath());

        } catch (Exception | NewQuestException e) {
            servletContext.setAttribute("gameService", e);
        }
    }
}
