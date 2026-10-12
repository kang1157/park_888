public class Fee {
    public String code;//车牌号
    public int fee;//停车费
    //构造函数
    public Fee(String code, int fee) {
        this.code = code;
        this.fee = fee;
    }

    public String getCode() {
        return code;
    }

    public int getFee() {
        return fee;
    }

}
