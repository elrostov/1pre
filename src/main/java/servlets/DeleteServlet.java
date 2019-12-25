package servlets;

import model.User;
import service.UserServiceImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8"); //Для поддержки кириллицы
        Long id = Long.parseLong(req.getParameter("id"));
        if (UserServiceImpl.getInstance().deleteUser(new User(id))) {
            resp.sendRedirect("/admin");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
