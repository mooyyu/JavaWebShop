package shop;

import cn.itcast.commons.CommonUtils;
import shop.Dao.BookItemDao;
import shop.Dao.CookieDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@MultipartConfig(location="/Users/mooyyu/IdeaProjects/Shop/book_img/")
@WebServlet(name = "bookIMGServlet")
public class bookIMGServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if (new CookieDao().checkLogined(request) && method != null) {
            if (method.equals("update")) {
                update(request, response);
            } else if (method.equals("create")) {
                create(request, response);
            }
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String referer = request.getHeader("referer");
        if (referer.startsWith("http://localhost:8080/shop/editBookItemServlet?uuid=") && referer.split("=").length == 2 && referer.split("=")[1].length() == 32) {
            String uuid = referer.split("=")[1];
            String imgUrl = new BookItemDao().getIMGUrl(uuid);
            if (imgUrl != null) {
                File oldIMG = new File("/Users/mooyyu/IdeaProjects/Shop/book_img/" + imgUrl);
                if (oldIMG.exists() && oldIMG.delete()) {
                    String newUrl = CommonUtils.uuid() + ".jpg";
                    request.getPart("img").write(newUrl);
                    new BookItemDao().updateIMGUrl(uuid, newUrl);
                }
            }
        }
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newUrl = CommonUtils.uuid() + ".jpg";
        File oldIMG = new File("/Users/mooyyu/IdeaProjects/Shop/book_img/" + newUrl);
        if (!oldIMG.exists()) {
            request.getPart("img").write(newUrl);
            response.getWriter().print(newUrl);
        }
    }
}
