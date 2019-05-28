package shop.obj;

import java.awt.print.Book;

public class order {
    private int id;
    private String bookId;
    private int status; //0:订单被取消,1:订单生成,2:运输中,3:完成交易
    private int sellerId;
    private int buyerId;
    private user seller;
    private user buyer;
    private BookItem book;

    @Override
    public String toString() {
        return "order{" +
                "id=" + id +
                ", bookId='" + bookId + '\'' +
                ", status=" + status +
                ", sellerId=" + sellerId +
                ", buyerId=" + buyerId +
                ", seller=" + seller.toString() +
                ", buyer=" + buyer.toString() +
                ", book=" + book.toString() +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public user getSeller() {
        return seller;
    }

    public void setSeller(user seller) {
        this.seller = seller;
    }

    public user getBuyer() {
        return buyer;
    }

    public void setBuyer(user buyer) {
        this.buyer = buyer;
    }

    public BookItem getBook() {
        return book;
    }

    public void setBook(BookItem book) {
        this.book = book;
    }
}
