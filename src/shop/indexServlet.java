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

import shop.obj.BookItem;
import shop.Dao.BookItemDao;

@WebServlet(name = "indexServlet")
public class indexServlet extends HttpServlet {
    private List<BookItem> list = null;

    @Override
    public void init() throws ServletException {
        BookItemDao dao = new BookItemDao();
        try {
            list = dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        if (list != null) {
            request.setAttribute("itemlist", list);
            request.getRequestDispatcher("/WEB-INF/shop/showIndex.jsp").forward(request, response);
        } else {
            PrintWriter writer = response.getWriter();
            writer.println("Error");
        }
    }
}
