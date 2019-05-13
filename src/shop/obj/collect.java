package shop.obj;

public class collect {
    private int userId;
    private String bookId;
    private BookItem book;

    @Override
    public String toString() {
        return "collect{" +
                "userId=" + userId +
                ", bookId='" + bookId + '\'' +
                ", book=" + book.toString() +
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

    public BookItem getBook() {
        return book;
    }

    public void setBook(BookItem book) {
        this.book = book;
    }
}
