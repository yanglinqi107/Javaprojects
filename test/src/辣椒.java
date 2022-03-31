
import java.util.*;
import java.io.*;

enum season {spring,summer,fall,winter}
class Char{
    char ch;
}
public class 辣椒 {
    static final int e = 2;
    float f= 56.1234f;
    int q=123;
    辣椒(){
        this(3);
        System.out.println(q);
    }
    辣椒(int i){
        q=i;
        System.out.println(q);
    }
    void chao1(){

    }
    void chao(){
        q=123;
        this.chao1();
        System.out.println(q);
    }
    public static void main(String[] args){
        辣椒 lajiao = new 辣椒();
        season sw = season.spring;
        System.out.println(sw.ordinal());
        System.out.println(sw.name());
        String s = ""+10+2;
        String a = s;
        a = "103";
        System.out.println(s);
        System.out.println(a);
        boolean b = a == "1";
        char q = '中';
        q='1';
        Char cha = new Char();
        cha.ch= 'i';
        System.out.println(cha.ch);
        int[] array = {1,2,3,4,5};
        array[0]=array.length;
        char[] m = new char[13];
        int[] n = array;
        n[4]=123;
        for(int v:array)
            System.out.print(v);
        for(int v:n)
            System.out.print(v);
        n[1]=6;
        int 八进制 = 11;
        Scanner scan = new Scanner(System.in);
        int count;
        count = scan.nextInt();
        count = (count > 20)?八进制-10:八进制+10;
        System.out.println(count);
        System.out.println(八进制);
        System.out.println("Hello World!");
        switch(q){
            case '1':System.out.println(1);break;
            case '2':System.out.println(2);break;
            case '3':System.out.println(3);break;
            default:System.out.println("kajfda");
        }
    }
}

interface shape {
    int a = 7;
    void draw();
}

abstract class circle extends Object implements shape{
    public void draw(){};
    abstract void erase();
}
