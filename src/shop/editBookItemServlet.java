package shop;

import shop.Dao.BookItemDao;
import shop.Dao.CookieDaoServlet;
import shop.Dao.catagoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editBookItemServlet")
public class editBookItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        if (new CookieDaoServlet().checkLogined(request)) {
            if (uuid != null && new BookItemDao().checkUuid(uuid)) {
                request.setAttribute("item", new BookItemDao().showItem(uuid));
                request.setAttribute("cataList", new catagoryDao().getAll());
                request.getRequestDispatcher("/WEB-INF/shop/editBookItem.jsp").forward(request, response);
            } else {
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect("/shop/exitLoginServlet");
        }
    }
}
