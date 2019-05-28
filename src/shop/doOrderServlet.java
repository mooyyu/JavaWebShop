package shop;

import org.json.JSONObject;
import shop.Dao.*;

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
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder("");
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        JSONObject json = new JSONObject(sb.toString());
        String method = request.getParameter("method");
        if (method != null && json != null && new CookieDaoServlet().checkLogined(request)) {
            if (json.has("uuid") && method.equals("buy") || method.equals("exchange")) {
                createOrder(request, response, method, json.getString("uuid"));
            } else if (json.has("orderId")) {
                updateOrder(request, response, method, Integer.valueOf(json.getString("orderId")));
            }
        }
    }

    public void createOrder(HttpServletRequest request, HttpServletResponse response, String method, String uuid)
            throws ServletException, IOException {
        String buyerId = new CookieDaoServlet().getValueByKey(request, "userId");
        String sellerId = new BookItemDao().getUserId(uuid);
        if (!buyerId.equals(sellerId)) {
            if (method.equals("buy")) {
                noticeDao no = new noticeDao();
                userDao ud = new userDao();
                String moneyStr = new BookItemDao().getMoney(uuid);
                if (moneyStr != null && ud.consume(Integer.valueOf(buyerId), Integer.valueOf(moneyStr))) {
                    // todo: 可以添加收藏失效通知
                    new orderDao().createOrder(uuid, Integer.valueOf(sellerId), Integer.valueOf(buyerId));
                    no.createNotice(Integer.valueOf(sellerId), "您有一笔新的订单,请尽快处理.", "success");
                    no.createNotice(Integer.valueOf(buyerId), "您的订单已经生成,请等待卖方处理.", "primary");
                    response.getWriter().println("订单已经生成,请耐心等待卖方处理.");
                } else {
                    response.getWriter().println("钱不够啊(´▽｀),快去充钱吧,充钱使你变强.");
                }
            } else if (method.equals("exchange")) {
                // todo: exchange
                response.getWriter().println("exchange" + uuid);
            }
        } else {
            response.getWriter().println("你买自己的书干啥？不允许!");
        }
    }

    public void updateOrder(HttpServletRequest request, HttpServletResponse response, String method, int orderId)
            throws ServletException, IOException {
        noticeDao no = new noticeDao();
        orderDao od = new orderDao();

        String uuid = od.getBookId(orderId);
        int sellerId = od.getSellerId(orderId);
        int buyerId = od.getBuyerId(orderId);

        if (method.equals("shipping")) {
            new orderDao().updateOrder(orderId, 2);
            no.createNotice(sellerId, "您的订单正在派送.", "primary");
            no.createNotice(buyerId, "您的订单正在派送.", "primary");
        } else {
            userDao ud = new userDao();
            String moneyStr = new BookItemDao().getMoney(uuid);
            if (method.equals("reciving")) {
                new orderDao().updateOrder(orderId, 3);
                no.createNotice(sellerId, "您的订单已经完成,资金将稍后到账.", "success");
                no.createNotice(buyerId, "您的订单已经完成.", "success");
                ud.recharge(sellerId, Integer.valueOf(moneyStr));
            } else if (method.equals("cancel")) {
                new orderDao().updateOrder(orderId, 0);
                no.createNotice(sellerId, "你有一笔订单被取消.", "danger");
                no.createNotice(buyerId, "您已取消一笔订单,资金将稍后返还.", "danger");
                ud.recharge(buyerId, Integer.valueOf(moneyStr));
            }
        }
    }
}