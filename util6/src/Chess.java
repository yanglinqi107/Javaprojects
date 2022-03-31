//Chess.java

class Game{
    Game(int i){
        System.out.println("构造Gmae" + i);
    }
}

class BoardGame extends Game{
    BoardGame(int i) {
        super(i);
        System.out.println("构造BoardGame" + i);
    }
}

public class Chess extends BoardGame{
    Chess(){
        super(44);
        System.out.println("构造Chess");
    }
    public static void main(String[] args){
        Chess x = new Chess();
    }
}



