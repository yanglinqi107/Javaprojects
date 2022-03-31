//Access.java

class Value {
    public int i = 0;

    public Value() {
    }

    public Value(int i) {
        this.i = i;
    }

    public int getValue() {
        return i;
    }

    public void setValue(int i){
        this.i = i;
    }
}
    public class Access{
        public static void main(String[] args){
            Value v = new Value();
            v.i = 16;
            v.setValue(v.i * 2);
            System.out.println(v.getValue());
        }
}

