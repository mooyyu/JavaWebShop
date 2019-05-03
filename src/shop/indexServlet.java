package shop;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import shop.Dao.catagoryDao;
import shop.obj.BookItem;
import shop.Dao.BookItemDao;

@WebServlet(name = "indexServlet")
public class indexServlet extends HttpServlet {
    private List<BookItem> list = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String cataId = request.getParameter("cataId");
        if (cataId == null) {
            list = new BookItemDao().findNew(300);
            request.setAttribute("showTitle", "最新的图书");
        } else {
            list = new BookItemDao().findAllinCatagory(Integer.valueOf(cataId));
            request.setAttribute("showTitle", String.format("分类 %s 下的图书", new catagoryDao().getName(Integer.valueOf(cataId))));
        }

        request.setAttribute("itemlist", list);
        request.getRequestDispatcher("/WEB-INF/shop/showIndex.jsp").forward(request, response);
    }
}
