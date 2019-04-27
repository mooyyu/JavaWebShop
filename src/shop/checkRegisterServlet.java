package shop;

import shop.Dao.connectDao;
import shop.Dao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "checkRegisterServlet")
public class checkRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String check_str = request.getParameter("check_str");

        if (email != null && check_str != null) {
            connectDao con = new connectDao();
            if (con.checkEmail(email)) {
                if (con.checkStatus(email)) {
                    request.setAttribute("ans", 0);
                } else {
                    if (con.checkLogined(email, check_str)) {
                        new userDao().updateStatus(email);
                        request.setAttribute("ans", 1);
                    } else {
                        request.setAttribute("ans", -1);
                    }
                }
            } else {
                request.setAttribute("ans", -1);
            }
        } else {
            request.setAttribute("ans", -1);
        }

        request.getRequestDispatcher("/WEB-INF/shop/checkRegister.jsp").forward(request, response);
    }
}
