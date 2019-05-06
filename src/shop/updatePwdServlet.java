package shop;

import org.json.JSONObject;
import shop.Dao.CookieDaoServlet;
import shop.Dao.connectDao;
import shop.Dao.userDao;

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

        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder("");

        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();

        JSONObject pwd = new JSONObject(sb.toString());
        if (pwd.has("oldpwd") && pwd.has("newpwd") && pwd.has("confirmpwd")) {
            if (new CookieDaoServlet().checkLogined(request)) {
                String logined_email = new CookieDaoServlet().getValueByKey(request, "logined_email");
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
