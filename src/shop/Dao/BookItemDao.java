package shop.Dao;

import cn.itcast.jdbc.TxQueryRunner;
import cn.itcast.jdbc.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
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
        String sql = String.format("select @rownum:=@rownum+1 as rownum, uuid, name, author, hownew, price, image from book" +
                ", (select @rownum:=0) t where status=1 order by time desc limit 0, %d;", limit);
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
        String sql = String.format("select @rownum:=@rownum+1 as rownum, uuid, name, author, hownew, price, image from book" +
                ", (select @rownum:=0) t where status=1 and catagoryId=%d;", catagoryId);
        try {
            return qr.query(sql, new BeanListHandler<BookItem>(BookItem.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回该用户发布的所有书目
     * @param userId
     * @return
     */
    public List<BookItem> findAllfromUserId(int userId) {
        String sql = String.format("select @rownum:=@rownum+1 as rownum, uuid, name, price, image from book" +
                ", (select @rownum:=0) t where status=1 and userId=%d;", userId);
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
        String sql = String.format("select @rownum:=@rownum+1 as rownum, uuid, name, author, hownew, price, image from book" +
                ", (select @rownum:=0) t where status=1 and (name like '%%%s%%' or author like '%%%s%%');", searchStr, searchStr);
        try {
            return qr.query(sql, new BeanListHandler<BookItem>(BookItem.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkUuid(String uuid) {
        String sql = String.format("select count(1) from book where uuid='%s' and status=1;", uuid);
        try {
            return ((Number)qr.query(sql, new ScalarHandler())).intValue() == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据uuid查找对应的bookitem的基本信息
     * @param uuid
     * @return
     */
    public BookItem showItem(String uuid) {
        String sql = String.format("select name, author, hownew, price, userId, catagoryId, image, info from book where status=1 and uuid = '%s';", uuid);
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

    public void createItem(String uuid, int catagoryId, String name, String author, int hownew, int price, String info, int userId, String image) {
        if (new catagoryDao().hasId(catagoryId)) {
            String sql = "insert into book(uuid, time, userId, name, author, hownew, price, catagoryId, image, info) values(?, now(), ?, ?, ?, ?, ?, ?, ?, ?);";
            try {
                JdbcUtils.beginTransaction();

                qr.update(sql, uuid, userId, name, author, hownew, price, catagoryId, image, info);

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
    }

    public void updateItem(String uuid, int catagoryId, String name, String author, int hownew, int price, String info) {
        if (new catagoryDao().hasId(catagoryId)) {
            String sql = String.format("update book set name='%s', author='%s', catagoryId=%d, hownew=%d, price=%d, info='%s' where uuid='%s' and status=1;"
                    , name, author, catagoryId, hownew, price, info, uuid);
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
    }

    public String getIMGUrl(String uuid) {
        String sql = String.format("select image from book where uuid='%s';", uuid);
        try {
            return (String)qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateIMGUrl(String uuid, String newUrl) {
        String sql = String.format("update book set image='%s' where uuid='%s';", newUrl, uuid);
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

    public String getUserId(String uuid) {
        String sql = String.format("select userId from book where uuid='%s';", uuid);
        try {
            return ((Number)qr.query(sql, new ScalarHandler())).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMoney(String uuid) {
        String sql = "select price from book where uuid=?;";
        try {
            Number num =  (Number)qr.query(sql, new ScalarHandler(), uuid);
            if (num != null) {
                return num.toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStatus(String uuid, int status) {
        String sql = "update book set status=? where uuid=?;";
        try {
            JdbcUtils.beginTransaction();

            qr.update(sql, status, uuid);

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
}
