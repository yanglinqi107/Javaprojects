//Director4.java

public class Director4 {
    int dir = 90;
    String name = new String("Delta Kilo");
    int speed = (int)(Math.random() * 150);

    Director4(){
        System.out.println("A good way to init an object.");
    }

    public static void main(String[] args){
        Director4 d = new Director4();
        System.out.println(d.name +" is running at "+ d.dir +" as "+d.speed);
    }
}
