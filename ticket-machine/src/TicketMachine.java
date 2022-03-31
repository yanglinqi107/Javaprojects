

public class TicketMachine {
    int price;                      //票价
    int balance;                    //余额
    int total;                      //总消费

    void setPrice(int price) {      //确定票价
        this.price = price;
    }

    void insertMoney(int amount) {  //充值
        balance += amount;
    }

    int getBalance() {              //返回余额
        return balance;
    }

    void print() {                  //打印车票
        System.out.println("==============================");
        System.out.println("This is a ticket");
        System.out.println("price : " + price);
        System.out.println("==============================");
        balance -= price;
        total += price;
    }

    int refund() {                //找钱
        int result = balance;
        balance = 0;
        return result;
    }

    int getTotal() {               //获取总收入
        return total;
    }

    void reset() {                  //重置
        balance = 0;
        total = 0;
    }
}
