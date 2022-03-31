//Query.java

import java.util.Scanner;

public class Query {
    //返回a,b中较大的那个
    int max(int a,int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    //返回a,b中较小的那个
    int min(int a,int b){
        int result = a;
        if(a > b){
            result = b;
        }
        return result;
    }

    //计算a,b的最大公约数
    int gcd(int a,int b){
        int dividend = max(a,b);    //被除数，两者大者
        int divisor = min(a,b);     //除数，两者小者
        int remainder = divisor;    //余数
        while(remainder > 0){
            divisor = remainder;
            remainder = dividend % divisor;
            dividend = divisor;
        }
        return divisor;
    }

    public static void main(String[] args){
        System.out.print("输入两个整数");
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        Query q = new Query();
        System.out.println(a +"和"+ b +"的最大公约数是"+ q.gcd(a,b));
    }
}
