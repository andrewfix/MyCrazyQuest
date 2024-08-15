package servlet;

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
        if (httpSession.getAttribute("userName") != null) {
            resp.sendRedirect("/play");
        } else {
            req.setAttribute("gameService", this.gameService);
            req.getRequestDispatcher("/template/start.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        System.out.println("!" + userName + "!");
        if (!StringUtils.isEmpty(userName)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userName", userName);
            resp.sendRedirect("/play");
        } else {
            req.setAttribute("gameService", this.gameService);
            req.setAttribute("errorMessages", "Имя не может быть пустым!");
            req.getRequestDispatcher("/template/start.jsp").forward(req, resp);
        }

    }
}
