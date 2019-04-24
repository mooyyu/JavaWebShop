package shop;

import org.json.JSONObject;
import shop.Dao.connectDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "getUserForHeaderServlet")
public class getUserForHeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String email = null;
        String checkStr = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("logined_email")) {
                    email = cookie.getValue();
                } else if (cookie.getName().equals("check_str")) {
                    checkStr = cookie.getValue();
                }
            }
        }

        JSONObject json = new JSONObject();
        if (email != null && checkStr != null) {
            connectDao con = new connectDao();
            if (con.checkLogined(email, checkStr)) {
                json.put("isLogin", true);
                json.put("email", email);
            }
        } else {
            json.put("isLogin", false);
        }

        response.getWriter().println(json.toString());
    }
}
