//director3.java

public class Director3 {
    int dir = 90;
    String name = new String("Delta Kilo");
    int speed = (int)(Math.random() * 150);

    void init(){
        System.out.println("A good way to init an object.");
    }

    public static void main(String[] args){
        Director3 d = new Director3();
        d.init();
        System.out.println(d.name +" is running at "+ d.dir +" as "+ d.speed);
    }
}
