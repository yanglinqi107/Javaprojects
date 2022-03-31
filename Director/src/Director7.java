//Director7.java

public class Director7 {
    int dir = 90;
    String name = new String("Delta Kilo");
    int speed = (int)(Math.random() * 150);

    Director7(String s){
        name = s;
        System.out.println("Captain "+ s +" is on board.");
    }

    Director7(){
        System.out.println("A good way to init an object.");
    }

    void print(){
        System.out.println(name +" is running at "+ dir +" as "+ speed);
    }

    void print(String target){
        System.out.println(name +" is running at "+ dir +" as "+ speed +" to "+ target);
    }

    void print(Director7 d){
        System.out.println(name +" is running at "+ dir +" as "+ speed +" to "+ d.name);
    }

    public static void main(String[] args){
        Director7 d1 = new Director7("关公");
        Director7 d2 = new Director7("秦琼");
        Director7 d3 = new Director7();
        d1.print(d2);
        d2.print("孙悟空");
        d3.print();
    }
}
