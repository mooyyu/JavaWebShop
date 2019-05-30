package shop;

import shop.Dao.CookieDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "exitLoginServlet")
public class exitLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        new CookieDao().clearCookie(request, response);
        if (request.getParameter("method") != null && request.getParameter("method").equals("relogin")) {
            response.sendRedirect("/login.jsp");
        } else {
            response.sendRedirect("/index.jsp");
        }
    }
}
