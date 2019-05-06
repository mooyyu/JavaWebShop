package shop.Dao;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import shop.Dao.connectDao;
import shop.obj.user;
import shop.utils.md5;

import cn.itcast.jdbc.TxQueryRunner;
import cn.itcast.jdbc.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.SQLException;

/**
 * 对数据库中user表进行操作
 */
public class userDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 通过email字段获取用户对象
     * @param email
     * @return
     */
    public user getUser(String email) {
        try {
            if (new connectDao().checkEmail(email)) {
                String sql = String.format("select name, email, sex, phone, address, info from user where email = '%s' limit 1;", email);
                return qr.query(sql, new BeanHandler<user>(user.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id字段获取用户对象
     * @param id
     * @return
     */
    public user getUserById(int id) {
        try {
            String sql = String.format("select name, email, sex, phone, address, info from user where id = %d;", id);
            return qr.query(sql, new BeanHandler<user>(user.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过email字段获取用户昵称
     * @param email
     * @return
     */
    public String getUserName(String email) {
        try {
            String sql = String.format("select name from user where email = '%s' limit 1;", email);
            return (String)qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    /**
     * 通过email字段获取用户性别
     * @param email
     * @return
     */
    public String getUserSex(String email) {
        try {
            String sql = String.format("select sex from user where email = '%s';", email);
            return ((Number)qr.query(sql, new ScalarHandler())).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过email字段获取用户id
     * @param email
     * @return
     */
    public String getUserId(String email) {
        try {
            String sql = String.format("select id from user where email = '%s';", email);
            Number num = (Number)qr.query(sql, new ScalarHandler());
            if (num != null) {
                return num.toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新email字段对应记录
     * @param name
     * @param email
     * @param sex
     * @param phone
     * @param address
     * @param info
     */
    public void updateUser(String name, String email, int sex, String phone, String address, String info) {
        try {
            JdbcUtils.beginTransaction();

            String sql = "update user set name=?, sex=?, phone=?, address=?, info=? where email=?;";
            qr.update(sql, name, sex, phone, address, info, email);

            JdbcUtils.commitTransaction();
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 更新email字段对应的密码
     * @param email
     * @param newpwd 未加密的密码
     */
    public void updatePwd(String email, String newpwd) {
        try {
            JdbcUtils.beginTransaction();

            String sql = "update user set password=? where email=?;";
            qr.update(sql, md5.createMD5(newpwd), email);

            JdbcUtils.commitTransaction();
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 用户注册，增加一条用户记录
     * @param name
     * @param email
     * @param sex
     * @param phone
     * @param address
     * @param info
     * @param pwd
     * @return
     */
    public boolean registerUser(String name, String email, int sex, String phone, String address, String info, String pwd) {
        try {
            JdbcUtils.beginTransaction();

            String sql = "insert into user (name, email, sex, status, time, password, phone, address, info) values (?, ?, ?, 1, now(), ?, ?, ?, ?);";
            qr.update(sql, name, email, sex, md5.createMD5(pwd), phone, address, info);

            JdbcUtils.commitTransaction();
            return true;
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新email字段对应的用户状态
     * @param email
     */
    public void updateStatus(String email) {
        try {
            JdbcUtils.beginTransaction();

            String sql = "update user set status=2 where email=?;";
            qr.update(sql, email);

            JdbcUtils.commitTransaction();
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
