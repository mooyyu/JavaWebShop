package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebFilter(filterName = "loggingFilter")
public class loggingFilter implements Filter {
    private PrintWriter logger;

    public void destroy() {
        if (logger != null) {
            logger.close();
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        logger.println(new Date() + " " + ((HttpServletRequest)req).getHeader("referer") + " " + ((HttpServletRequest) req).getRequestURI());
        logger.flush();
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config)
            throws ServletException {
        try {
            logger = new PrintWriter(new File(config.getServletContext().getRealPath("/"), "logging.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
