/*
*1、编写一个应用程序计算梯形和圆形的面积。
*提示：在程序中可定义2个类来分别描述梯形、圆形的属性和求面积的方法，然后通过定义主类，
*使用梯形类和圆形类创建对象，计算它们的面积。
* */
public class Circle
{
    private int radius;                 //半径
    private int xPosition;              //圆心x坐标
    private int yPosition;              //圆心y坐标
    private double area;

    Circle()                            //初始化圆
    {
        radius = 0;
        xPosition = 0;
        yPosition = 0;
    }

    public void SetCircle(int x,int y,int radius)      //设定圆的圆心和半径
    {
        this.radius = radius;
        this.xPosition = x;
        this.yPosition = y;
        System.out.println("圆心坐标（" + xPosition + "," + yPosition + "),半径:" + radius  );
    }

    public double CalArea()                     //计算圆面积
    {
        return area = 3.14 * radius * radius;
    }
}
