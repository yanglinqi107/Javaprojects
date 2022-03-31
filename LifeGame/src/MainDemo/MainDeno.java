package MainDemo;
import Cell.Cell;
import Map.Field;
import Map.View;

import javax.swing.*;

public class MainDeno {
    public static void main(String[] args){
        Field cellfield = new Field(30,30);

        View view =new View(cellfield);

        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Cells");
        frame.add(view);
        frame.setSize(31*16,33*16-8);
        //frame.pack();
        frame.setVisible(true);
        for(int i=1;i<=1000;i++){
            for(int row=0;row<cellfield.getWidth();row++){
                for(int col=0;col<cellfield.getLength();col++){
                    Cell cell=cellfield.getCell(row,col);
                    int number= cellfield.getNumNeighboor(row,col);
                    if(cell.islife()) {
                        if (number < 2 || number > 3) {
                            cell.die();
                        }
                    }else if (number==3){
                        cell.reborn();
                    }
                }
            }
            frame.repaint();
            System.out.println("update:"+i+"\n");
            try{
                Thread.sleep(200);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
