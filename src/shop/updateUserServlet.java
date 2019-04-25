package shop;

import shop.Dao.userDao;
import shop.Dao.connectDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "updateUserServlet")
public class updateUserServlet extends HttpServlet {
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

        JSONObject user = new JSONObject(sb.toString());
        if (new connectDao().checkLogined(user.getString("email"), user.getString("check_str"))) {
            new userDao().updateUser(
                    user.getString("name"),
                    user.getString("email"),
                    Integer.valueOf(user.getString("sex")),
                    user.getString("phone"),
                    user.getString("address"),
                    user.getString("info"));

            response.getWriter().print("yes");
        } else {
            response.getWriter().print("no");
        }
    }
}
