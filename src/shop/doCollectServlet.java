package shop;

import org.json.JSONObject;
import shop.Dao.BookItemDao;
import shop.Dao.CookieDao;
import shop.Dao.collectDao;
import shop.utils.getPost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "doCollectServlet")
public class doCollectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");

        response.setContentType("text/html");

        if (method != null && new CookieDao().checkLogined(request)) {
            String userId = new CookieDao().getValueByKey(request, "userId");

            if (method.equals("clearAll")) {
                new collectDao().clearAll(Integer.valueOf(userId));
            } else {
                JSONObject json = new getPost().getPostJson(request);

                if (json != null && json.has("uuid")) {
                    String uuid = json.getString("uuid");

                    if (method.equals("toggleCollect")) {
                        if (new BookItemDao().getUserId(uuid).equals(userId)) {
                            response.getWriter().println(2);
                        } else {
                            response.getWriter().println(new collectDao().toggleStatus(Integer.valueOf(userId), uuid));
                        }
                    } else if (method.equals("clearCollect")) {
                        new collectDao().clear(Integer.valueOf(userId), uuid);
                    }
                } else {
                    response.getWriter().println(-1);
                }
            }
        } else {
            response.getWriter().println(-1);
        }
    }
}
