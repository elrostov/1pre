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
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = new String //Для поддержки кириллицы
                (request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String password = new String //Для поддержки кириллицы
                (request.getParameter("password").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        Optional<User> userOptional = UserServiceImpl.getInstance().getUserIfExists(new User(name, password));
        if (userOptional.isPresent()) {
            request.getSession().setAttribute("user", userOptional.get());
            if ("admin".equals(userOptional.get().getRole().toLowerCase())) {
                response.sendRedirect("/admin");
            } else {
                response.sendRedirect("/user");
            }
        } else {
            response.sendRedirect("/");
        }
    }
}
