package shop;

import shop.utils.parseJson;
import shop.Dao.connectDao;
import shop.utils.md5;
import shop.Dao.CookieDaoServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "checkLoginServlet")
public class checkLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method.equals("register")) {

        } else if (method.equals("login")) {
            checkLogin(request, response);
        }
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder("");

        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        Map<String, String> loginInfo = parseJson.parseJsonForLogin(sb.toString());
        connectDao con = new connectDao();
        if (con.checkLogin(loginInfo.get("email"), loginInfo.get("password"))) {
            CookieDaoServlet cookieDao = new CookieDaoServlet();

            cookieDao.addCookie(response, "logined_email", loginInfo.get("email"), -1);
            cookieDao.addCookie(response, "check_str", md5.createMD5(loginInfo.get("password")), -1);
            cookieDao.addCookie(response, "isLogin", "true", -1);
            cookieDao.addCookie(response, "userName", con.getUserName(loginInfo.get("email")), -1);

            writer.print("yes");
        } else {
            writer.print("no");
        }
    }
}
