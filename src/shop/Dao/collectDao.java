package shop.Dao;

import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import shop.obj.BookItem;
import shop.obj.collect;

import java.sql.SQLException;
import java.util.List;

public class collectDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 获取收藏状态
     * @param userId
     * @param bookId
     * @return
     */
    public boolean getStatus(int userId, String bookId) {
        String sql = String.format("select count(1) from collect where userId=%d and bookId='%s';", userId, bookId);
        try {
            return ((Number)qr.query(sql, new ScalarHandler())).intValue() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 转换收藏状态
     * @param userId
     * @param bookId
     * @return -1:操作失败;0:取消收藏成功;1:收藏成功
     */
    public int toggleStatus(int userId, String bookId) {
        String sql = null;
        int result = -1;
        if (getStatus(userId, bookId)) {
            sql = String.format("delete from collect where userId=%d and bookId='%s';", userId, bookId);
            result = 0;
        } else {
            sql = String.format("insert into collect (userId, bookId) values (%d, '%s');", userId, bookId);
            result = 1;
        }
        try {
            JdbcUtils.beginTransaction();

            qr.update(sql);

            JdbcUtils.commitTransaction();
            return result;
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 删除一条收藏记录
     * @param userId
     * @param bookId
     */
    public void clear(int userId, String bookId) {
        String sql = String.format("delete from collect where userId=%d and bookId='%s';", userId, bookId);
        try {
            JdbcUtils.beginTransaction();

            qr.update(sql);

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
     * 删除给定userId的所有收藏记录
     * @param userId
     */
    public void clearAll(int userId) {
        String sql = String.format("delete from collect where userId=%d;", userId);
        try {
            JdbcUtils.beginTransaction();

            qr.update(sql);

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
     * 返回该用户收藏夹内所有书目的简单信息
     * @param userId
     * @return
     */
    public List<BookItem> getList(int userId) {
        String sql = String.format("select @rownum:=@rownum+1 as rownum, uuid, name, price, image from book" +
                ", (select @rownum:=0) t where uuid in (select bookId from collect where userId=%d);", userId);
        try {
            return qr.query(sql, new BeanListHandler<BookItem>(BookItem.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
