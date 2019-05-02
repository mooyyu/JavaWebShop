package shop;

import shop.Dao.BookItemDao;

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
        if (uuid != null) {
            request.setAttribute("item", new BookItemDao().showItem(uuid));
        }
        request.getRequestDispatcher("/WEB-INF/shop/showBookItem.jsp").forward(request, response);
    }
}
