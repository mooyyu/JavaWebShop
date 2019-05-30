package shop.Dao;

import shop.Dao.connectDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对于Cookie的操作池
 */
@WebServlet(name = "CookieDao")
public class CookieDao extends HttpServlet {
    /**
     * 通过key返回cookie的value
     * @param request
     * @param key
     * @return
     */
    public String getValueByKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 通过cookie中保存的邮件地址和加密密码和userId验证用户是否已经登录。
     * @param request
     * @return
     */
    public boolean checkLogined(HttpServletRequest request) {
        String isLogin = getValueByKey(request, "isLogin");
        String email = getValueByKey(request, "logined_email");
        String checkStr = getValueByKey(request, "check_str");
        String userId = getValueByKey(request, "userId");

        if (isLogin != null && email != null && checkStr != null && userId != null) {
            if (isLogin.equals("true") && new connectDao().checkLogined(email, checkStr) && userId.equals(new userDao().getUserId(email))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在当前request中添加cookie
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxAge
     */
    public void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(cookieMaxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 清空当前request的所有cookie
     * @param request
     * @param response
     */
    public void clearCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    /**
     * 更新cookie中保存的用户名和性别
     * @param response
     * @param name
     * @param sex
     */
    public void updateNameAndSex(HttpServletResponse response, String name, String sex) {
        Cookie cookie = new Cookie("userName", name);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        cookie = new Cookie("userSex", sex);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
