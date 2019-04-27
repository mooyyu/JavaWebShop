package shop.Dao;

import org.apache.commons.dbutils.handlers.ScalarHandler;
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

    public String getUserName(String email) {
        try {
            String sql = String.format("select name from user where email = '%s' limit 1;", email);
            return (String)qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String getUserSex(String email) {
        try {
            String sql = String.format("select sex from user where email = '%s' limit 1;", email);
            return ((Number)qr.query(sql, new ScalarHandler())).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0";
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
            qr.update(sql, md5.createMD5(newpwd), email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String name, String email, int sex, String phone, String address, String info, String pwd) {
        try {
            String sql = "insert into user (name, email, sex, status, time, password, phone, address, info) values (?, ?, ?, 1, now(), ?, ?, ?, ?);";
            qr.update(sql, name, email, sex, md5.createMD5(pwd), phone, address, info);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
