package shop;

import org.json.JSONObject;
import shop.Dao.BookItemDao;
import shop.Dao.CookieDao;
import shop.utils.getPost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "updateBookItemServlet")
public class updateBookItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject book = new getPost().getPostJson(request);
        if (book.has("uuid") && book.has("catagoryId") && book.has("name") && book.has("author") && book.has("hownew") && book.has("price") && book.has("info")) {
            if (new CookieDao().checkLogined(request)) {
                new BookItemDao().updateItem(
                        book.getString("uuid"),
                        Integer.valueOf(book.getString("catagoryId")),
                        book.getString("name"), book.getString("author"),
                        Integer.valueOf(book.getString("hownew")),
                        Integer.valueOf(book.getString("price")),
                        book.getString("info")
                );
            }
        }
    }
}
