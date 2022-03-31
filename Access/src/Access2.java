//Access2.java

class Value2 {
    private int i = 0;

    public Value2() {
    }

    public Value2(int i) {
        this.i = i;
    }

    public int getValue() {
        return i;
    }

    public void setValue(int i){
        this.i = i;
    }
}
public class Access2{
    public static void main(String[] args){
        Value2 v = new Value2();
        v.setValue(16);
        v.setValue(v.getValue() * 2);
        System.out.println(v.getValue());
    }
}