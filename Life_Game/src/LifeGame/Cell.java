package LifeGame;

//ϸ����
public class Cell {
    private boolean alive = false;  //ϸ��״̬
    private static int cellLength = 17; //ϸ���߳�

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
