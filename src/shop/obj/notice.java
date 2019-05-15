package shop.obj;

public class notice {
    private int rownum;
    private String time;
    private int userId;
    private String info;

    @Override
    public String toString() {
        return "notice{" +
                "rownum=" + rownum +
                ", time='" + time + '\'' +
                ", userId=" + userId +
                ", info='" + info + '\'' +
                '}';
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
