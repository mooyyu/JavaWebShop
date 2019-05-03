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
    private String image_b;
    private String image_w;
    private int catagoryId;
    private int userId;
    private catagory catagory;
    private user user;

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
                ", image_b='" + image_b + '\'' +
                ", image_w='" + image_w + '\'' +
                ", catagoryId=" + catagoryId +
                ", userId=" + userId +
                ", catagory=" + catagory.toString() +
                ", user=" + user.toString() +
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

    public String getImage_b() {
        return image_b;
    }

    public void setImage_b(String image_b) {
        this.image_b = image_b;
    }

    public String getImage_w() {
        return image_w;
    }

    public void setImage_w(String image_w) {
        this.image_w = image_w;
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
}
