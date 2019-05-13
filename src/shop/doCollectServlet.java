package shop;

import org.json.JSONObject;
import shop.Dao.BookItemDao;
import shop.Dao.CookieDaoServlet;
import shop.Dao.collectDao;
import shop.Dao.userDao;
import shop.obj.BookItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "doCollectServlet")
public class doCollectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder("");

        String method = request.getParameter("method");

        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();

        response.setContentType("text/html");

        if (new CookieDaoServlet().checkLogined(request)) {
            String userId = new CookieDaoServlet().getValueByKey(request, "userId");

            if (method.equals("clearAll")) {
                new collectDao().clearAll(Integer.valueOf(userId));
            } else {
                JSONObject json = new JSONObject(sb.toString());

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
