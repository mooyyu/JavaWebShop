package shop.Dao;

import shop.utils.md5;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class connectDao {
    private QueryRunner qr = new TxQueryRunner();

    public boolean checkLogin(String email, String password) {
        try {
            String sql = String.format("select count(1) from user where email = '%s'", email);
            Number num = (Number)qr.query(sql, new ScalarHandler());
            if (num.intValue() == 1) {
                sql = String.format("select password from user where email = '%s'", email);
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
}
