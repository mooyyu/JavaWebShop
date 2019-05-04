package shop.obj;

public class collect {
    private int userId;
    private String bookId;

    @Override
    public String toString() {
        return "collect{" +
                "userId=" + userId +
                ", bookId='" + bookId + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
