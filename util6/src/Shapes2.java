//Shapes2.java

abstract class Shape2{
    abstract void draw();
    abstract void erase();
}

class Circle2 extends Shape2{
    void draw(){
        System.out.println("Circle2.draw()");
    }
    void erase(){
        System.out.println("Circle2.erase()");
    }
}

class Square2 extends Shape2{
    void draw(){
        System.out.println("Square2.draw()");
    }
    void erase(){
        System.out.println("Square2.erase()");
    }
}

class Triangle2 extends Shape2{
    void draw(){
        System.out.println("Triangle2.draw()");
    }
    void erase(){
        System.out.println("Triangle2.erase()");
    }
}

public class Shapes2 {
    public static Shape2 randShape(){
        switch((int)(Math.random() * 3)){
            default:
            case 0:return new Circle2();
            case 1:return new Square2();
            case 2:return new Triangle2();
        }
    }

    public static void main(String[] args){
        Shape2[] s = new Shape2[9];
        for(int i = 0;i < s.length; ++i){
            s[i] = randShape();
        }
        for(int i = 0;i < s.length; ++i){
            s[i].draw();
        }
    }
}
