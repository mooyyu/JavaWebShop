package shop.Dao;

import shop.Dao.connectDao;
import shop.obj.user;
import shop.utils.md5;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.SQLException;

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

    public void updateUser(String name, String email, int sex, String phone, String address, String info) {
        try {
            String sql = "update user set name=?, sex=?, phone=?, address=?, info=? where email=?;";
            qr.update(sql, name, sex, phone, address, info, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePwd(String email, String newpwd) {
        try {
            String sql = "update user set password=? where email=?;";
            qr.update(sql, new md5().createMD5(newpwd), email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
