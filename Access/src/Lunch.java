//Lunch.java

class Sushi{
    private Sushi(){}

    public static Sushi makeSushi(){
        return new Sushi();
    }

    public static Sushi getSushi(){return ps1;}

    public void sauce(){
        System.out.println("Put some soy sauce on sushi.");
    }

    private static Sushi ps1 = new Sushi();
}

public class Lunch {
    public static void main(String[] args){
        //!Sushi priv1 = new Sushi();
        Sushi priv2 = Sushi.makeSushi();
        priv2.getSushi().sauce();
    }
}
