package shop.api;

import org.json.JSONObject;
import shop.Dao.collectDao;
import shop.Dao.connectDao;
import shop.Dao.userDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "doCollectServlet")
public class doCollectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder("");

        String method = request.getParameter("method");

        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();

        response.setContentType("text/html");

        JSONObject json = new JSONObject(sb.toString());

        if (json.has("logined_email") && json.has("check_str") && json.has("userId") && (json.has("uuid") || method.equals("clearAll"))) {
            if (method != null && new connectDao().checkLogined(json.getString("logined_email"), json.getString("check_str")) && json.getString("userId").equals(new userDao().getUserId(json.getString("logined_email")))) {
                if (method.equals("toggleCollect")) {
                    response.getWriter().println(new collectDao().toggleStatus(Integer.valueOf(json.getString("userId")), json.getString("uuid")));
                } else if (method.equals("clearCollect")) {
                    new collectDao().clear(Integer.valueOf(json.getString("userId")), json.getString("uuid"));
                } else if (method.equals("clearAll")) {
                    new collectDao().clearAll(Integer.valueOf(json.getString("userId")));
                } else {
                    response.getWriter().println(-1);
                }
            } else {
                response.getWriter().println(-1);
            }
        } else {
            response.getWriter().println("Error.");
        }
    }
}
