package shop.Dao;

import shop.utils.md5;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class connectDao {
    private QueryRunner qr = new TxQueryRunner();

    public boolean checkStatus(String email) {
        try {
            String sql = String.format("select status from user where email = '%s' limit 1;", email);
            Number num = (Number)qr.query(sql, new ScalarHandler());
            if (num.intValue() == 2) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkEmail(String email) {
        try {
            String sql = String.format("select count(1) from user where email = '%s';", email);
            Number num = (Number)qr.query(sql, new ScalarHandler());
            if (num.intValue() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkLogin(String email, String password) {
        try {
            if (checkStatus(email)) {
                String sql = String.format("select password from user where email = '%s' limit 1;", email);
                String pwd = (String)qr.query(sql, new ScalarHandler());
                if (pwd.equals(md5.createMD5(password))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkLogined(String email, String checkStr) {
        try {
            String sql = String.format("select password from user where email = '%s' limit 1;", email);
            String pwd = (String)qr.query(sql, new ScalarHandler());
            if (pwd.equals(checkStr)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
