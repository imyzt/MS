package top.imyzt.ms.core.page;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 16:25
 * @description: 数据库排序
 */
public enum Order {

    ASC("asc"), DESC("desc");

    private String des;

    Order(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
