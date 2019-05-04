package shop.Dao;

import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import shop.obj.collect;

import java.sql.SQLException;

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
}
