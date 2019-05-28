package shop.obj;

/**
 * 映射到数据库book表的记录
 */
public class BookItem {
    private int rownum;
    private String uuid;
    private String name;
    private String author;
    private String info;
    private int hownew;
    private int price;
    private String image;
    private int catagoryId;
    private int userId;
    private catagory catagory;
    private user user;
    private int status; //0:下架,1:正常待售,2:订单已生成,3:销售成功
    private int orderStatus;    //0:订单被取消,1:订单生成,2:运输中,3:完成交易
    private int orderId;

    @Override
    public String toString() {
        return "BookItem{" +
                "rownum=" + rownum +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", info='" + info + '\'' +
                ", hownew=" + hownew +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", catagoryId=" + catagoryId +
                ", userId=" + userId +
                ", catagory=" + catagory.toString() +
                ", user=" + user.toString() +
                ", status=" + status +
                ", orderStatus=" + orderStatus +
                ", orderId=" + orderId +
                '}';
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getHownew() {
        return hownew;
    }

    public void setHownew(int hownew) {
        this.hownew = hownew;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(int catagoryId) {
        this.catagoryId = catagoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public shop.obj.catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(shop.obj.catagory catagory) {
        this.catagory = catagory;
    }

    public shop.obj.user getUser() {
        return user;
    }

    public void setUser(shop.obj.user user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
