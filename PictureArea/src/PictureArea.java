import java.util.Scanner;

public class PictureArea
{
    public static void main(String[] args)
    {
        int r,u,d,h;
        Trapezoid trapezoid = new Trapezoid();
        Circle circle = new Circle();
        Scanner input = new Scanner(System.in);

        System.out.println("圆：");
        do {
            System.out.print("请输入半径：");
            r = input.nextInt();
        }while(r <= 0);
        circle.SetCircle(0,0,r);
        System.out.println("面积:" + circle.CalArea() );

        System.out.println("\n梯形:");
        do{
            System.out.print("请输入上底:");
            u = input.nextInt();
            System.out.print("请输入下底：");
            d = input.nextInt();
            System.out.print("请输入高：");
            h = input.nextInt();
        }while(u <= 0 || d <= 0 || h <= 0);
        trapezoid.SetTrapezoid(u,d,h);
        System.out.println("面积：" + trapezoid.CalArea());
    }
}
