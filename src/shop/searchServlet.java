package shop;

import shop.Dao.BookItemDao;
import shop.obj.BookItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "searchServlet")
public class searchServlet extends HttpServlet {
    private List<BookItem> list = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchStr = request.getParameter("searchString");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        if (searchStr != null) {
            list = new BookItemDao().search(searchStr);
            request.setAttribute("showTitle", String.format("搜索 %s 的结果", searchStr));
            request.setAttribute("itemlist", list);
            request.getRequestDispatcher("/WEB-INF/shop/showIndex.jsp").forward(request, response);
        } else {
            PrintWriter writer = response.getWriter();
            writer.println("Error.");
        }
    }
}
