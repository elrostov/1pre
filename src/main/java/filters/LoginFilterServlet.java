package filters;

import com.mysql.cj.Session;
import model.User;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@WebFilter(urlPatterns = {"/*"})
public class LoginFilterServlet implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            if (request.getSession().getAttribute("user") == null) {
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                if (name != null && password != null) {
                    name = new String (name.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    password = new String (password.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    Optional<User> userOptional = UserServiceImpl.getInstance().getUserIfExists(new User(name, password));
                    userOptional.ifPresent(user -> request.getSession().setAttribute("user", user));
                }
            }
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
        }
    }
}
