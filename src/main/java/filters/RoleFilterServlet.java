package filters;

import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/user/*", "/admin/*"})
public class RoleFilterServlet implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        if ("admin".equals(user.getRole().toLowerCase())) {
                chain.doFilter(request, response);
        } else if (request.getRequestURI().substring(request.getContextPath().length()).startsWith("/admin")) {
               response.sendRedirect("/user");
        } else {
           chain.doFilter(request, response);
        }
    }
}
