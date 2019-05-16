package shop.Dao;

import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import shop.obj.BookItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<BookItem> getSold(int sellerId) {
        List<BookItem> result = new ArrayList<BookItem>();
        List<BookItem> curStatusResult = null;
        String sql = "select @rownum:=@rownum+1 as rownum, ? as orderStatus, uuid, name, image from book" +
                ", (select @rownum:=0) t where uuid in (select bookId from orders where sellerId=? and status=?);";
        try {
            for (int i : new int[]{1, 2, 3, 0}) {
                curStatusResult = qr.query(sql, new BeanListHandler<BookItem>(BookItem.class), i, sellerId, i);
                if (curStatusResult != null) {
                    result.addAll(curStatusResult);
                    curStatusResult = null;
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<BookItem> getBought(int buyerId) {
        List<BookItem> result = new ArrayList<BookItem>();
        List<BookItem> curStatusResult = null;
        String sql = "select @rownum:=@rownum+1 as rownum, ? as orderStatus, uuid, name, image from book" +
                ", (select @rownum:=0) t where uuid in (select bookId from orders where buyerId=? and status=?);";
        try {
            for (int i : new int[]{1, 2, 3, 0}) {
                curStatusResult = qr.query(sql, new BeanListHandler<BookItem>(BookItem.class), i, buyerId, i);
                if (curStatusResult != null) {
                    result.addAll(curStatusResult);
                    curStatusResult = null;
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
