package shop;

import org.json.JSONObject;
import shop.Dao.CookieDao;
import shop.Dao.connectDao;
import shop.Dao.userDao;
import shop.utils.getPost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "updatePwdServlet")
public class updatePwdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        JSONObject pwd = new getPost().getPostJson(request);
        if (pwd.has("oldpwd") && pwd.has("newpwd") && pwd.has("confirmpwd")) {
            if (new CookieDao().checkLogined(request)) {
                String logined_email = new CookieDao().getValueByKey(request, "logined_email");
                if (new connectDao().checkLogin(logined_email, pwd.getString("oldpwd"))) {
                    if (pwd.getString("newpwd").equals(pwd.getString("confirmpwd"))) {
                        new userDao().updatePwd(logined_email, pwd.getString("newpwd"));
                        response.getWriter().print("yes");
                    } else {
                        response.getWriter().print("no");
                    }
                } else {
                    response.getWriter().print("no");
                }
            } else {
                response.getWriter().print("no");
            }
        } else {
            response.getWriter().print("no");
        }
    }
}
