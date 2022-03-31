//Replace.java

class Char{
    char ch;
}

public class Replace {
    static void f(Char c){
        if(c.ch == 'a'){
            c.ch = 'z';
        }
    }

    public static void main(String[] args){
        Char ci = new Char();
        ci.ch = 'a';
        System.out.println(ci.ch);
        f(ci);
        System.out.println(ci.ch);
    }
}
