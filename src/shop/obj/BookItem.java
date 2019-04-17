package shop.obj;

public class BookItem {
    private int rownum;
    private String name;
    private String author;
    private int hownew;
    private int price;
    private String image_b;
    private String image_w;

    @Override
    public String toString() {
        return "BookItem{" +
                "rownum=" + rownum +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", hownew=" + hownew +
                ", price=" + price +
                ", image_b='" + image_b + '\'' +
                ", image_w='" + image_w + '\'' +
                '}';
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
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
}
