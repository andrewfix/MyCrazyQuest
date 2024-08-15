package service;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class HttpServletService extends HttpServlet {
    protected GameService gameService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        gameService = (GameService) servletContext.getAttribute("gameService");
    }
}
