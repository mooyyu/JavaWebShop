package shop.Dao;

import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import shop.obj.notice;

import java.sql.SQLException;
import java.util.List;

public class noticeDao {
    private QueryRunner qr = new TxQueryRunner();

    public void createNotice(int userId, String info, String type) {
        String sql = "insert into notices(time, userId, info, type) values(now(), ?, ?, ?);";
        try {
            JdbcUtils.beginTransaction();

            qr.update(sql, userId, info, type);

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

    public List<notice> getNotices(int userId) {
        String sql = "select @rownum:=@rownum+1 as rownum, time, info, type from notices, (select @rownum:=0) t where userid=? order by time desc";
        try {
            return qr.query(sql, new BeanListHandler<notice>(notice.class), userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
