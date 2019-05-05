package shop;

import org.json.JSONObject;
import shop.Dao.BookItemDao;
import shop.Dao.connectDao;

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
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder("");

        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();

        JSONObject book = new JSONObject(sb.toString());
        if (book.has("logined_email") && book.has("check_str") && book.has("uuid") && book.has("catagoryId") && book.has("name") && book.has("author") && book.has("hownew") && book.has("price") && book.has("info")) {
            if (new connectDao().checkLogined(book.getString("logined_email"), book.getString("check_str"))) {
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
