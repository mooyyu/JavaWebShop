package shop.obj;

public class notice {
    private int rownum;
    private String time;
    private int userId;
    private String info;
    private String type;

    @Override
    public String toString() {
        return "notice{" +
                "rownum=" + rownum +
                ", time='" + time + '\'' +
                ", userId=" + userId +
                ", info='" + info + '\'' +
                ", type='" + type + '\'' +
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
