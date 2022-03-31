//Diretor6.java

public class Director6 {
    int dir = 90;
    String name = new String("Delta Kilo");
    int speed = (int)(Math.random() * 150);

    Director6(String s){
        name = s;
        System.out.println("Captain "+ s +" is on board.");
    }

    Director6(){
        System.out.println("A good way to init an object.");
    }

    public static void main(String[] args){
        Director6 d1 = new Director6("关公");
        Director6 d2 = new Director6("秦琼");
        Director6 d3 = new Director6();
        System.out.println(d1.name +" is running at "+ d1.dir +" as "+ d1.speed);
        System.out.println(d2.name +" is running at "+ d2.dir +" as "+ d2.speed);
        System.out.println(d3.name +" is running at "+ d3.dir +" as "+ d3.speed);
    }
}
