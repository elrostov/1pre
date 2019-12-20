package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        req.setAttribute("userId", req.getParameter("id"));
        req.setAttribute("userName", req.getParameter("name"));
        req.getRequestDispatcher("WEB-INF/update.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println();
        System.out.println("---UpdateServlet doPost START---");
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (name.isEmpty() && password.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if (UserService.getInstance().updateUser(new User(id, name, password))) {
            List<User> users = UserService.getInstance().getAllUsers();
            if (users == null) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } else {
                req.setAttribute("users", users);
                req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
            }
            System.out.println("---UpdateServlet doPost END---");
            System.out.println();
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println("---UpdateServlet doPost END---");
            System.out.println();
        }
    }
}
