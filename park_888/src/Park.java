import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//进出管理  收入统计  Stat
//空余车位显示
//应该用两个集合框架来描述Stat对象
//使用lang类型计时，用System中的时间戳函数
//功能方法尽量细分  20,20,50原则  类中不应超20功能方法 方法中不要超20行 包中不要超50个类
public class Park {
    public static int stallCount = 100;//车位总数(最大100)

    HashMap<String, Stat> stats=new HashMap<>();//车辆信息
    List<Fee> income=new ArrayList<>();//收入信息

    //车辆进入登记 输入：车牌 输出： 1成功 -1失败
    public int enter(String code) {
        if(!stats.containsKey(code)||stats.get(code).getState()==0){
            stats.put(code, new Stat(code, LocalDateTime.now(), 1));
            stallCount--;
            return 1;
        }
        System.out.println("车辆已登记");
        return -1;
    }
    //车辆驶出结算 输入：车牌 输出： 1成功 -1失败
    public int exit(String code) {
        if(stats.containsKey(code)){
            Stat stat = stats.get(code);
            System.out.println("停车费为：" + stat.getFee());//输出后等待用户输入正确数字模拟支付
            Scanner scanner = new Scanner(System.in);
            int mark = scanner.nextInt();
            if (mark == stat.getFee()) {
                stat.setFeeState(1);
                System.out.println("支付成功");
                income.add(new Fee(code, stat.getFee()));
                stat.setState(0);
                stallCount++;
                return 1;
            } else {
                System.out.println("支付失败");
                return -1;
            }
        }
        System.out.println("车辆未登记");
        return -1;
    }
    //当前车位查询 输入：无 输出：车位信息
    public void query() {
        if(0<=stallCount&&stallCount<=100){
            System.out.println("当前车位数为：" + stallCount);
            return;
        }
        System.out.println("系统错误");
    }
    //收入统计 输入：无 输出：收入信息
    public void sumFee() {
        int totalFee = 0;
        for (Fee fee : income) {
            totalFee += fee.getFee();
        }
        System.out.println("总收入为：" + totalFee);
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Park park = new Park();
        while (true) {
            System.out.println("=========================================");
            System.out.println("===============停车场管理系统===============");
            System.out.println("=========================================");
            System.out.println("1. 进入登记 2.驶出结算 3.当前车位查询 4.收入统计 5.退出系统");
            int mark = scanner.nextInt();
            switch (mark) {
                case 1:{
                    System.out.println("请输入车牌号：");
                    String code = scanner.next();
                    int result= park.enter(code);
                    if(result==1){
                        System.out.println("进入登记成功");
                    }else if(result==-1){
                        System.out.println("进入登记失败");
                    }else{
                        System.out.println("系统错误");
                    }
                    break;
                }
                case 2: {
                    System.out.println("请输入车牌号：");
                    String code = scanner.next();
                    int result= park.exit(code);
                    if(result==1){
                        System.out.println("驶出结算成功");
                    }else if(result==-1){
                        System.out.println("驶出结算失败");
                    }else{
                        System.out.println("系统错误");
                    }
                    break;
                }
                case 3:{
                    park.query();
                    break;
                }
                case 4:{
                    park.sumFee();
                    break;
                }
                case 5:{
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("输入错误");
                    break;
                }
            }


        }

    }
}
