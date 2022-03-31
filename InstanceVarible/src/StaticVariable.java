public class StaticVariable {
    static int a;
    public static void main(String[] args){
        StaticVariable sv1 = new StaticVariable();
        sv1.a = 16;
        StaticVariable sv2 = new StaticVariable();
        sv2.a = 18;
        System.out.println("iv1.a="+ sv1.a);
        System.out.println("iv2.a="+ sv2.a);
    }
}
