package shop;

import org.json.JSONObject;
import shop.Dao.BookItemDao;
import shop.Dao.CookieDaoServlet;
import shop.Dao.noticeDao;
import shop.Dao.orderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "doOrderServlet")
public class doOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder("");
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        JSONObject json = new JSONObject(sb.toString());

        if (method != null && json != null && json.has("uuid") && new CookieDaoServlet().checkLogined(request)) {
            String uuid = json.getString("uuid");
            String buyerId = new CookieDaoServlet().getValueByKey(request, "userId");
            String sellerId = new BookItemDao().getUserId(uuid);
            if (!buyerId.equals(sellerId)) {
                if (method.equals("buy")) {
                    noticeDao no = new noticeDao();
                    // todo: 增加账户余额部分
                    no.createNotice(Integer.valueOf(sellerId), "您有一笔新的订单,请尽快处理.");
                    no.createNotice(Integer.valueOf(buyerId), "您的订单已经生成,请等待卖方处理.");
                    new orderDao().createOrder(uuid, Integer.valueOf(sellerId), Integer.valueOf(buyerId));
                    response.getWriter().println("订单已经生成,请耐心等待卖方处理,若超过72小时未处理,系统将自动取消订单.");
                } else if (method.equals("exchange")) {
                    // exchange
                    response.getWriter().println("exchange" + uuid);
                }
            } else {
                response.getWriter().println("你买自己的书干啥？不允许!");
            }
        }
    }
}
