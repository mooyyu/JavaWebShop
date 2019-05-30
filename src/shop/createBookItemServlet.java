package shop;

import cn.itcast.commons.CommonUtils;
import org.json.JSONObject;
import shop.Dao.BookItemDao;
import shop.Dao.CookieDao;
import shop.Dao.catagoryDao;
import shop.utils.getPost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createBookItemServlet")
public class createBookItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (new CookieDao().checkLogined(request)) {
            JSONObject json = new getPost().getPostJson(request);
            if (json.has("image") && json.has("name") && json.has("info") && json.has("author") && json.has("price") && json.has("catagoryId") && json.has("hownew")) {
                String uuid = CommonUtils.uuid();
                String image = json.getString("image");
                String name = json.getString("name");
                String info = json.getString("info");
                String author = json.getString("author");
                String price = json.getString("price");
                String catagoryId = json.getString("catagoryId");
                String hownew = json.getString("hownew");
                String userId = new CookieDao().getValueByKey(request, "userId");
                if (!image.equals("") && !name.equals("") && !info.equals("") && !author.equals("") && !price.equals("") && !catagoryId.equals("") && !hownew.equals("")) {
                    new BookItemDao().createItem(uuid, Integer.valueOf(catagoryId), name, author, Integer.valueOf(hownew), Integer.valueOf(price), info, Integer.valueOf(userId), image);
                    response.getWriter().print(uuid);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (new CookieDao().checkLogined(request)) {
            request.setAttribute("cataList", new catagoryDao().getAll());
            request.getRequestDispatcher("/WEB-INF/shop/createBookItem.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }
}
