package service;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public class HttpServletService extends HttpServlet {
    protected transient GameService gameService;
    protected static final String USER_NAME_ATTRIBUTE_NAME = "userName";

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

    protected void setDefaultAttributesToRequest(HttpServletRequest req) {
        req.setAttribute("gameTitle", this.gameService.getGameTitle());

    }

}
