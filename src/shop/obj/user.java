package shop.obj;

/**
 * 映射到数据库user表的记录
 */
public class user {
    private String name;
    private String email;
    private int sex;
    private String phone;
    private String address;
    private String info;
    private int balance;

    @Override
    public String toString() {
        return "user{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", info='" + info + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
