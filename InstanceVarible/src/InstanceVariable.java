//InstanceVarible.java

public class InstanceVariable {
    int a;
    public static void main(String[] args){
        InstanceVariable iv1 = new InstanceVariable();
        iv1.a = 16;
        InstanceVariable iv2 = new InstanceVariable();
        iv2.a = 18;
        System.out.println("iv1.a="+ iv1.a);
        System.out.println("iv2.a="+ iv2.a);
    }
}
