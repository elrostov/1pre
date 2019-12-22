package servlets;

import model.User;
import service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = new String //Для поддержки кириллицы
                (req.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String password = new String //Для поддержки кириллицы
                (req.getParameter("password").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        if (name.isEmpty() || password.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Wrong values!");
        }
        if (!UserServiceImpl.getInstance().addUser(new User(name, password))) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            resp.sendRedirect("/");
        }
    }
}
