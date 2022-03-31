//Director2.java

public class Director2 {
    int dir = 90;
    String name = new String("Delta Kilo");
    int speed = (int)(Math.random() * 150);
    public static void main(String[] args){
        Director2 d = new Director2();
        System.out.println(d.name + " is running at "+ d.dir +" as "+ d.speed);
    }
}
