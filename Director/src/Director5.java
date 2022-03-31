//Director5.java

public class Director5 {
    int dir = 90;
    String name = new String("Delta Kilo");
    int speed = (int)(Math.random() * 150);

    Director5(String s){
        name = s;
        System.out.println("Captain "+ s +" is on board.");
    }

    public static void main(String[] args){
        Director5 d1 = new Director5("关公");
        Director5 d2 = new Director5("秦琼");
        System.out.println(d1.name +" is running at "+ d1.dir +" as "+ d1.speed);
        System.out.println(d2.name +" is running at "+ d2.dir +" as "+ d2.speed);
    }
}
