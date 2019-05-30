package shop;

import shop.Dao.CookieDao;
import shop.Dao.noticeDao;
import shop.Dao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "rechargeServlet")
public class rechargeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (new CookieDao().checkLogined(request)) {
            String money = request.getParameter("money");
            if (money != null) {
                int userId = Integer.valueOf(new CookieDao().getValueByKey(request, "userId"));
                new userDao().recharge(userId, Integer.valueOf(money));
                new noticeDao().createNotice(userId, "您已成功充值" + money + "RMB", "info");
            }
        }
    }
}
