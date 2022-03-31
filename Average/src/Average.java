//Average.java

import java.util.Scanner;

public class Average
{
    public static void main(String[] args)
    {
        int sum = 0;
        int count = 0;
        int value = 0;
        Scanner scan = new Scanner(System.in);

        do{
            value = scan.nextInt();
            if(value != -1)
            {
                sum += value;
                count++;
            }
        }while(value != -1);

        if(count != 0)
        {
            double average = (double)sum / count;
            System.out.println("平均成绩："+ average);
        }
        else
        {
            System.out.println("没有输入成绩");
        }
    }
}
