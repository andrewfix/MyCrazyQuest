package servlet;

import exception.NewQuestException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import service.HttpServletService;

import java.io.IOException;

@WebServlet(urlPatterns = "/start")
public class StartServlet extends HttpServletService {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute(USER_NAME_ATTRIBUTE_NAME) != null) {
            resp.sendRedirect("/play");
        } else {
            // Добавляем данные для отображения, используемые для всех страниц
            this.setDefaultAttributesToRequest(req);
            req.setAttribute("gameDescription", gameService.getGameDescription());
            req.getRequestDispatcher("/template/start.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        if (!StringUtils.isEmpty(userName) && !StringUtils.isBlank(userName)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute(USER_NAME_ATTRIBUTE_NAME, userName);
            try {
                gameService.newGame();
            } catch (NewQuestException e) {
                throw new ServletException(e);
            }
            resp.sendRedirect("/play");
        } else {
            // Добавляем данные для отображения, используемые для всех страниц
            this.setDefaultAttributesToRequest(req);
            req.setAttribute("errorMessages", "Имя не может быть пустым!");
            req.getRequestDispatcher("/template/start.jsp").forward(req, resp);
        }
    }
}
