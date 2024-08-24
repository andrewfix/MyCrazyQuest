package servlet;

import exception.InputDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.HttpServletService;

import java.io.IOException;

@WebServlet(urlPatterns = "/play")
public class PlayServlet extends HttpServletService {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute(USER_NAME_ATTRIBUTE_NAME) == null) {
            resp.sendRedirect("/start");
        } else {
            req.setAttribute("gameService", this.gameService);
            req.setAttribute(USER_NAME_ATTRIBUTE_NAME, httpSession.getAttribute(USER_NAME_ATTRIBUTE_NAME));
            if (this.gameService.isGameEnded()) {
                req.getRequestDispatcher("/template/end.jsp").forward(req, resp);
                httpSession.removeAttribute(USER_NAME_ATTRIBUTE_NAME);
            } else {
                req.getRequestDispatcher("/template/play.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String transition = req.getParameter("transition");
        try {
            if (transition == null) {
                throw new InputDataException("Вы ничего не выбрали!");
            } else {
                gameService.goTo(transition);
                resp.sendRedirect("/play");
            }
        } catch (Exception e) {
            req.setAttribute("errorMessages", e.getMessage());
            req.getRequestDispatcher("/template/play.jsp").forward(req, resp);
        }
    }
}
