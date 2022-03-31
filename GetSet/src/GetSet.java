//GetSet.java

public class GetSet {
    String name = "";
    void f(String name){
        this.name = name;
    }

    public static void main(String[] args){
        GetSet gs = new GetSet();
        gs.f("lala");
    }
}
