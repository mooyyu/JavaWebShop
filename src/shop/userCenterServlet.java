package shop;

import shop.Dao.CookieDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userCenterServlet")
public class userCenterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (new CookieDao().checkLogined(request)) {
            request.getRequestDispatcher("/WEB-INF/shop/userCenter.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login.jsp");
        }
    }
}
