package shop.Dao;

import shop.Dao.connectDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CookieDaoServlet")
public class CookieDaoServlet extends HttpServlet {
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

    public boolean checkLogin(HttpServletRequest request) {
        String email = getValueByKey(request, "logined_email");
        String checkStr = getValueByKey(request, "check_str");

        if (email != null && checkStr != null) {
            if (new connectDao().checkLogined(email, checkStr)) {
                return true;
            }
        }
        return false;
    }

    public void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(cookieMaxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

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
