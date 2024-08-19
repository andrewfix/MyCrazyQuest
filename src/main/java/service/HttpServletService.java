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
        Object obj = servletContext.getAttribute("gameService");
        if (obj instanceof Exception) {
            throw new ServletException(((Exception) obj).getMessage());
        } else {
            gameService = (GameService) obj;
        }
    }
}
