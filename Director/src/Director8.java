//Director8.java

public class Director8 {
    int dir = 90;
    String name = new String("Delta Kilo");
    int speed = (int)(Math.random() * 150);

    Director8(String s){
        this();
        name = s;
        System.out.println("Captain "+ s +" is on board.");
    }

    Director8(){
        System.out.println("A good way to init an object.");
    }

    void print(){
        System.out.println(name +" is running at "+ dir +" as "+ speed);
    }

    void print(String target){
        System.out.println(name +" is running at "+ dir +" as "+ speed +" to "+ target);
    }

    void print(Director8 d){
        System.out.println(name +" is running at "+ dir +" as "+ speed +" to "+ d.name);
    }

    public static void main(String[] args){
        Director8 d1 = new Director8("关公");
        Director8 d2 = new Director8("秦琼");
        Director8 d3 = new Director8();
        d1.print(d2);
        d2.print("孙悟空");
        d3.print();
    }
}
