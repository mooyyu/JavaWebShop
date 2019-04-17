package shop.Dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import shop.obj.BookItem;

import java.sql.SQLException;
import java.util.List;

public class BookItemDao {
    private QueryRunner qr = new TxQueryRunner();

    public List<BookItem> findAll() throws SQLException {
        String sql = "select @rownum:=@rownum+1 as rownum, name, author, hownew, price, image_b, image_w from book" +
                ", (select @rownum:=0) t";
        return qr.query(sql, new BeanListHandler<BookItem>(BookItem.class));
    }

    public List<BookItem> search(String searchStr) throws SQLException {
        String sql = String.format("select @rownum:=@rownum+1 as rownum, name, author, hownew, price, image_b, image_w from book" +
                ", (select @rownum:=0) t where name like '%%%s%%' or author like '%%%s%%'", searchStr, searchStr);
        return qr.query(sql, new BeanListHandler<BookItem>(BookItem.class));
    }
}
