package shop.Dao;

import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class orderDao {
    private QueryRunner qr = new TxQueryRunner();

    public void createOrder(String bookId, int sellerId, int buyerId) {
        String sql = "insert into orders(time, bookId, sellerId, buyerId) values(now(), ?, ?, ?);";
        try {
            JdbcUtils.beginTransaction();

            qr.update(sql, bookId, sellerId, buyerId);

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

    public void cancelOrder(String bookId, int sellerId, int buyerId) {

    }

    public void completeOrder(String bookId, int sellerId, int buyerId) {

    }
}
