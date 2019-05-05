package shop;

import shop.Dao.BookItemDao;
import shop.Dao.CookieDaoServlet;
import shop.Dao.collectDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "showBookItemServlet")
public class showBookItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        if (uuid != null && new BookItemDao().checkUuid(uuid)) {
            request.setAttribute("item", new BookItemDao().showItem(uuid));
            CookieDaoServlet cookieDao = new CookieDaoServlet();
            if (cookieDao.getValueByKey(request, "isLogin") != null && cookieDao.getValueByKey(request, "userId") != null) {
                request.setAttribute("collectStatus", new collectDao().getStatus(Integer.valueOf(cookieDao.getValueByKey(request, "userId")), uuid));
            } else {
                request.setAttribute("collectStatus", false);
            }
            request.getRequestDispatcher("/WEB-INF/shop/showBookItem.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }
}
