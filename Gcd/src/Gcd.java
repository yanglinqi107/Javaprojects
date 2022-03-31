//Gcd.java

import java.util.Scanner;

public class Gcd
{
    public static void main(String[] args)
    {
        System.out.print("请输入两个整数：");
        Scanner scan = new Scanner(System.in);
        int n1 = scan.nextInt();
        int n2 = scan.nextInt();
        int dividend = (n1 > n2) ? n1 : n2;
        int divisor = (n1 > n2) ? n2 : n1;
        int remainder = divisor;
        while(remainder > 0)
        {
            divisor = remainder;
            remainder = dividend % divisor;
            dividend = divisor;
        }
        System.out.println(n1 +"和"+ n2 +"的最大公约数是"+ divisor);
    }
}
