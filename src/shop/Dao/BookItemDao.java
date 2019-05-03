package shop.Dao;

import cn.itcast.jdbc.TxQueryRunner;
import cn.itcast.jdbc.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import shop.obj.BookItem;
import shop.Dao.userDao;
import shop.Dao.catagoryDao;
import java.sql.SQLException;
import java.util.List;

/**
 * 操作数据库shop中的bookItem表
 */
public class BookItemDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 查找bookItem最新添加的若干条数据的简单信息
     * @return
     */
    public List<BookItem> findNew(int limit) {
        String sql = String.format("select @rownum:=@rownum+1 as rownum, uuid, name, author, hownew, price, image_b, image_w from book" +
                ", (select @rownum:=0) t order by time desc limit 0, %d;", limit);
        try {
            return qr.query(sql, new BeanListHandler<BookItem>(BookItem.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查找bookItem中给定分类下的所有数据的简单信息
     * @param catagoryId
     * @return
     */
    public List<BookItem> findAllinCatagory(int catagoryId) {
        String sql = String.format("select @rownum:=@rownum+1 as rownum, uuid, name, author, hownew, price, image_b, image_w from book" +
                ", (select @rownum:=0) t where catagoryId=%d;", catagoryId);
        try {
            return qr.query(sql, new BeanListHandler<BookItem>(BookItem.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查找和搜索相关的数据，将字符串和书名，作者名匹配。
     * @param searchStr
     * @return
     */
    public List<BookItem> search(String searchStr) {
        String sql = String.format("select @rownum:=@rownum+1 as rownum, name, author, hownew, price, image_b, image_w from book" +
                ", (select @rownum:=0) t where name like '%%%s%%' or author like '%%%s%%';", searchStr, searchStr);
        try {
            return qr.query(sql, new BeanListHandler<BookItem>(BookItem.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据uuid查找对应的bookitem的基本信息
     * @param uuid
     * @return
     */
    public BookItem showItem(String uuid) {
        String sql = String.format("select name, author, hownew, price, userId, catagoryId, image_w, info from book where uuid = '%s';", uuid);
        try {
            BookItem book = qr.query(sql, new BeanHandler<BookItem>(BookItem.class));
            book.setUser(new userDao().getUserById(book.getUserId()));
            book.setCatagory(new catagoryDao().get(book.getCatagoryId()));
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
