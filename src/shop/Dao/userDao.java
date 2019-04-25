package shop.Dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import shop.Dao.connectDao;
import shop.obj.user;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class userDao {
    private QueryRunner qr = new TxQueryRunner();

    public user getUser(String email) {
        user u = new user();
        try {
            if (new connectDao().checkEmail(email)) {
                String sql = String.format("select name, email, sex, phone, address, info from user where email = '%s' limit 1;", email);
                u = qr.query(sql, new BeanHandler<user>(user.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
}
