package shop.utils;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class getPost {
    public JSONObject getPostJson(HttpServletRequest request)
            throws ServletException, IOException {
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder("");
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        return new JSONObject(sb.toString());
    }
}
