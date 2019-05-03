package shop;

import org.json.JSONObject;
import shop.Dao.connectDao;
import shop.Dao.userDao;
import shop.utils.md5;
import shop.utils.sendEmail;
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

@WebServlet(name = "checkLoginServlet")
public class checkLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method.equals("register")) {
            register(request, response);
        } else if (method.equals("login")) {
            login(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder("");

        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        JSONObject registerInfo = new JSONObject(sb.toString());

        String name = registerInfo.getString("name");
        String email = registerInfo.getString("email");
        String phone = registerInfo.getString("phone");
        String sex = registerInfo.getString("sex");
        String address = registerInfo.getString("address");
        String info = registerInfo.getString("info");
        String pwd = registerInfo.getString("pwd");
        String confirmPwd = registerInfo.getString("confirmPwd");

        if (name != null && email != null && phone != null && sex != null && address != null && info != null && pwd != null && confirmPwd != null) {
            if (name != "" && email != "" && sex != "" && pwd != "" && confirmPwd != "") {
                if (pwd.equals(confirmPwd)) {
                    connectDao con = new connectDao();
                    if (con.checkEmail(email)) {
                        if (con.checkStatus(email)) {
                            writer.println("此邮箱已注册成功.请直接登录!");
                        } else {
                            writer.println("此邮箱地址已注册,请检查邮件以完成注册!");
                        }
                    } else {
                        if (new userDao().registerUser(name, email, Integer.valueOf(sex), phone, address, info, pwd)) {
                            if (sendEmail.checkRegister(email, md5.createMD5(pwd))) {
                                writer.println("已发送验证邮件至注册邮箱,请检查邮件以完成注册.");
                            } else {
                                writer.println("发送验证邮件失败,请联系管理员.");
                            }
                        } else {
                            writer.println("注册失败.");
                        }
                    }
                } else {
                    writer.println("两次密码不一致!");
                }
            } else {
                writer.println("请将必要信息填写完整!");
            }
        } else {
            writer.println("Error!");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
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

        JSONObject loginInfo = new JSONObject(sb.toString());
        userDao con = new userDao();
        if (loginInfo.getString("email") != "" && loginInfo.getString("password") != "" && new connectDao().checkLogin(loginInfo.getString("email"), loginInfo.getString("password"))) {
            CookieDaoServlet cookieDao = new CookieDaoServlet();

            cookieDao.addCookie(response, "logined_email", loginInfo.getString("email"), -1);
            cookieDao.addCookie(response, "check_str", md5.createMD5(loginInfo.getString("password")), -1);
            cookieDao.addCookie(response, "isLogin", "true", -1);
            cookieDao.addCookie(response, "userName", con.getUserName(loginInfo.getString("email")), -1);
            cookieDao.addCookie(response, "userSex", con.getUserSex(loginInfo.getString("email")), -1);

            writer.print("yes");
        } else {
            writer.print("no");
        }
    }
}
