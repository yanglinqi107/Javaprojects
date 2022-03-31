//Seasons.java

enum Season {spring,summer,fall,winter}

public class Seasons{
    public static void main(String[] args){
        Season s1 = Season.spring;
        System.out.println(s1.ordinal());
        System.out.println(s1.name());
    }
}
