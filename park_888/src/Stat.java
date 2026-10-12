import java.time.LocalDateTime;

public class Stat {
    //进出管理  flow
    public String code;//车牌号
    public LocalDateTime inTime;//进入时间
    public LocalDateTime outTime;//离开时间
    public int state;//进出状态 0:出 1:进
    public int feeState;//停车费状态 0:未付 1:已付
    public static final int PRICE = 2;//停车一小时2元
    //构造方法
    public Stat(String code, LocalDateTime inTime, int state) {
        this.code = code;
        this.inTime = inTime;
        this.state = state;
        this.feeState = 0;
    }
    //停车费
    public int getFee() {
        this.outTime = LocalDateTime.now();
        int time = this.outTime.getHour() - this.inTime.getHour();
        return ((PRICE * time)+4);
    }
    //停车费状态
    public int getFeeState() {
        return this.feeState;
    }
    //停车费状态设置
    public void setFeeState(int feeState) {
        this.feeState = feeState;
    }
    //车辆进出状态
    public int getState() {
        return this.state;
    }
    //车辆进出状态设置
    public void setState(int state) {
        this.state = state;
    }

    public void setOutTime(LocalDateTime now) {
        this.outTime = now;
    }
}
