package Cell;

import java.awt.Graphics;

public class Cell {
    private boolean life ;
    public Cell(){
        this.life=false;
    }
    public void die() {
        this.life = false;
    }
    public void reborn(){
        this.life=true;
    }
    public boolean islife(){
        return life;
    }

    public void drawCell(Graphics g, int x, int y, int size){
        g.drawRect(x,y,size,size);
        if(life){
            g.fillRect(x,y,size,size);
        }
    }

}
