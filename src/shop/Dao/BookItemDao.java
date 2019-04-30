package shop.Dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import shop.obj.BookItem;

import java.sql.SQLException;
import java.util.List;

/**
 * 操作数据库shop中的bookItem表
 */
public class BookItemDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 查找bookItem中所有数据
     * @return
     * @throws SQLException
     */
    public List<BookItem> findAll() throws SQLException {
        String sql = "select @rownum:=@rownum+1 as rownum, name, author, hownew, price, image_b, image_w from book" +
                ", (select @rownum:=0) t;";
        return qr.query(sql, new BeanListHandler<BookItem>(BookItem.class));
    }

    /**
     * 查找和搜索相关的数据，将字符串和书名，作者名匹配。
     * @param searchStr
     * @return
     * @throws SQLException
     */
    public List<BookItem> search(String searchStr) throws SQLException {
        String sql = String.format("select @rownum:=@rownum+1 as rownum, name, author, hownew, price, image_b, image_w from book" +
                ", (select @rownum:=0) t where name like '%%%s%%' or author like '%%%s%%';", searchStr, searchStr);
        return qr.query(sql, new BeanListHandler<BookItem>(BookItem.class));
    }
}
