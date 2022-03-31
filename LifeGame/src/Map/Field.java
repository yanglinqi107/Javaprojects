package Map;
import Cell.Cell;
public class Field {
    private int length;
    private int width;
    private Cell[][] cellField;
    public Field(int length,int width){
        this.length=length;
        this.width=width;
        cellField=new Cell[length][width];
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                Cell cell=new Cell();
                if(Math.random()<0.1)
                    cell.reborn();
                cellField[row][col]=cell;
            }
        }
    }
    public int getLength(){return length;}
    public int getWidth(){return width;}
    public void place(int row, int col, Cell o) {
        Cell ret = cellField[row][col];
        cellField[row][col] = o;
    }
    public Cell getCell(int row,int col){return cellField[row][col];}
    public int getNumNeighboor(int row,int col){
        int number =0;
        for(int i=row-1;i<=row+1;i++){
            for(int j=col-1;j<=col+1;j++){
                if(i>=0&&j>=0&&i<length&&j<width&&!(i==row&&j==row)&&cellField[i][j].islife()){
                    number++;
                }
            }
        }
        return number;
    }
}
