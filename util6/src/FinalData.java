//FinalData.java

class Value{
    int i = 1;
}
public class FinalData {
    //编译时确定的常数
    final int i1 = 9;
    static final int i2 = 99;
    public static final int i3 = 99;
    //运行时确定的不可修改的变量
    final int i4 = (int)(Math.random() * 20);
    static final int i5 = (int)(Math.random() * 20);
    //未做初始化的final变量
    final int i6;
    //final的对象变量
    final Value v2 = new Value();
    static Value v3 = new Value();
    //未做定义初始化的final变量
    final Value v4;
    //final数组
    final int[] a = {1,2,3,4,5,6};

    FinalData(){
        i6 = 16;
        v4 = new Value();
    }

    public void print(String id){
        System.out.println(id + ":" + "i4 = " + i4 + ",i5 = " + i5);
        for(int i : a){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        FinalData fd1 = new FinalData();
        //！fd1.i1++;            //错误
        fd1.v2.i++;
        for(int i = 0;i < fd1.a.length; ++i){
            fd1.a[i]++;         //数组本身并不是fianl的
        }
        //!fd1.v2 = new Value();        //错误
        //!fd1.v3 = new Value();        //错误
        //!fd1.a = new int[3];          //错误
        FinalData fd2 = new FinalData();
        fd1.print("fd1");
        fd2.print("fd2");
    }
}
