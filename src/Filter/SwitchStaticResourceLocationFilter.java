package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "SwitchStaticResourceLocationFilter")
public class SwitchStaticResourceLocationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
//        req.setAttribute("StaticResourceLocation", "/book_img/");
        req.setAttribute("StaticResourceLocation", "/StaticResource/Shop/book_img/");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
