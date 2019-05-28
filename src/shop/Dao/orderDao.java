package shop.Dao;

import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import shop.obj.BookItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderDao {
    private QueryRunner qr = new TxQueryRunner();

    public String getBookId(int id) {
        try {
            return (String)qr.query("select bookId from orders where id=?;", new ScalarHandler(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getSellerId(int id) {
        try {
            return ((Number)qr.query("select sellerId from orders where id=?;", new ScalarHandler(), id)).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getBuyerId(int id) {
        try {
            return ((Number)qr.query("select buyerId from orders where id=?;", new ScalarHandler(), id)).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void createOrder(String bookId, int sellerId, int buyerId) {
        String sql = "insert into orders(time, bookId, sellerId, buyerId) values(now(), ?, ?, ?);";
        try {
            JdbcUtils.beginTransaction();

            qr.update("update book set status=? where uuid=?;", 2, bookId);
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

    public void updateOrder(int id, int status) {
        String sql = "update orders set status=? where id=?;";
        try {
            JdbcUtils.beginTransaction();

            if (status == 0) {
                qr.update("update book set status=? where uuid in (select bookId from orders where id=?);", 1, id);
            } else if (status == 3) {
                qr.update("update book set status=? where uuid in (select bookId from orders where id=?);", 3, id);
            }
            qr.update(sql, status, id);

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

    public List<BookItem> getSold(int sellerId) {
        List<BookItem> result = new ArrayList<BookItem>();
        List<BookItem> curStatusResult = null;
        String sql = "select @rownum:=@rownum+1 as rownum, ? as orderStatus, orders.id as orderId, uuid, name, image from book join orders on uuid=orders.bookId" +
                ", (select @rownum:=0) t where sellerId=? and orders.status=?;";
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
        String sql = "select @rownum:=@rownum+1 as rownum, ? as orderStatus, orders.id as orderId, uuid, name, image from book join orders on uuid=orders.bookId" +
                ", (select @rownum:=0) t where buyerId=? and orders.status=?;";
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
