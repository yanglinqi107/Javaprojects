public class Comp {
    static void max(int a,int b){
        if(a > b){
            System.out.println(a);
            b += 100;
        }
        if(a < b){
            System.out.println(b);
            a += 100;
        }
    }

    public static void main(String[] args){
        int i = 10,j = 11;
        System.out.println("i="+ i +",j="+ j);
        max(i,j);
        System.out.println("i="+ i +",j="+ j);
    }
}
