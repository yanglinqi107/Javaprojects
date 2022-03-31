/*
* 梯形
* */
public class Trapezoid
{
    private int UpSide;             //上底
    private int DownSide;           //下底
    private int height;             //高
    private double area;            //面积

    Trapezoid()                     //构造：初始化梯形
    {
        UpSide = 0;
        DownSide = 0;
        height = 0;
    }

    public void SetTrapezoid(int u,int d,int h)     //设定梯形的上下底和高
    {
        this.UpSide = u;
        this.DownSide = d;
        this.height = h;
        System.out.println("梯形：上底=" + UpSide + " 下底=" + DownSide + " 高=" + height);
    }

    public double CalArea()                         //计算梯形面积
    {
        return area = height * (UpSide + DownSide) / 2.0;
    }
}
