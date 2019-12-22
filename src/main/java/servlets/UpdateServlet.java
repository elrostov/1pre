package servlets;

import model.User;
import service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Для поддержки кириллицы
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if ("yes".equals(req.getParameter("updatePage"))) {
            req.setAttribute("userId", id);
            req.setAttribute("userName", name);
            req.setAttribute("userPassword", password);
            req.getRequestDispatcher("WEB-INF/update.jsp").forward(req, resp);
        }
        if (!UserServiceImpl.getInstance().updateUser(new User(id, name, password))) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            resp.sendRedirect("/");
        }
    }
}
