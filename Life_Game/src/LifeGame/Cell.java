package LifeGame;

//Ï¸°ûÀà
public class Cell {
    private boolean alive = false;  //Ï¸°û×´Ì¬
    private static int cellLength = 17; //Ï¸°û±ß³¤

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void changeAlive() {
        alive = !alive;
    }

    public boolean getAlive() {
        return alive;
    }
}
