//Score2.java

import java.util.Scanner;

public class Score2
{
    static int PASS = 60;
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("请输入成绩：");
        int score = scan.nextInt();
        System.out.println("你输入的成绩是"+ score);

        if(score < PASS)
        {
            System.out.println("很遗憾，这个成绩没有及格。");
        }
        else
        {
            System.out.println("祝贺你，这个成绩及格了。");
            System.out.println("再见");
        }
    }
}
