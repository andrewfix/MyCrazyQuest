package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.GameService;

import java.io.IOException;

@WebServlet(urlPatterns = "/play")
public class PlayServlet extends HttpServlet {

    GameService gameService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        gameService = (GameService) servletContext.getAttribute("gameService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("userName") == null) {
            resp.sendRedirect("/start");
        } else {
            req.setAttribute("gameService", this.gameService);
            if (this.gameService.isGameEnded()) {
                req.getRequestDispatcher("/template/end.jsp").forward(req, resp);
                httpSession.removeAttribute("userName");
                gameService.newGame();
            } else {
                req.getRequestDispatcher("/template/play.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String transition = req.getParameter("transition");
        try {
            gameService.goTo(transition);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/play");

    }
}
