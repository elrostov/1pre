package filters;

import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/admin/*"})
public class FirstFilterServlet implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if ("admin".equals(user.getRole().toLowerCase())) {
                chain.doFilter(request, response);
            } else if ("user".equals(user.getRole().toLowerCase())) {
                if (request.getRequestURI().substring(request.getContextPath().length()).startsWith("/admin")){
                    request.getRequestDispatcher("WEB-INF/user.jsp").forward(request, response);
                }
                response.sendRedirect("/user");
            }
        } else{
            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
        }
    }
}
