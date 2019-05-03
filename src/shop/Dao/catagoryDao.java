package shop.Dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import shop.obj.catagory;

import java.sql.SQLException;
import java.util.List;

public class catagoryDao {
    private QueryRunner qr = new TxQueryRunner();

    public String getName(int id) {
        String sql = String.format("select name from catagory where id = %d", id);
        try {
            return (String)qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public catagory get(int id) {
        String sql = String.format("select id, name from catagory where id = %d", id);
        try {
            return qr.query(sql, new BeanHandler<catagory>(catagory.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<catagory> getAll() {
        String sql = "select id, name from catagory;";
        try {
            return qr.query(sql, new BeanListHandler<catagory>(catagory.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
