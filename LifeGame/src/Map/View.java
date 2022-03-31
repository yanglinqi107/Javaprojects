package Map;
import Cell.Cell;

import javax.swing.*;
import java.awt.*;
public class View extends JPanel {
    //private static final long serialVersionUID=-5258995676212660595L;
    private static final int GRID_SIZE=16;
    private Field cellField;

    public View(Field field){
        cellField=field;
    }
    public void paint(Graphics g){
        super.paint(g);
        for(int row=0;row<cellField.getLength();row++){
            for(int col=0;col< cellField.getWidth();col++){
                Cell cell=cellField.getCell(row,col);
                if(cell!=null){
                    cell.drawCell(g,col*GRID_SIZE,row*GRID_SIZE,GRID_SIZE);
                }
            }
        }
    }
}
