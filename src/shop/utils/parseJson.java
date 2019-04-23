package shop.utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class parseJson {
    static public Map<String, String> parseJsonForLogin(String jsonStr) {
        JSONObject json = new JSONObject(jsonStr);
        Map<String, String> ans = new HashMap<String, String>();
        ans.put("email", json.getString("email"));
        ans.put("password", json.getString("password"));
        return ans;
    }
}
