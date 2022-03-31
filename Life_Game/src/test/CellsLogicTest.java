package test;

import LifeGame.CellsLogic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellsLogicTest {
    private CellsLogic cellsLogic =new CellsLogic(10,10);
    @Test
    void getCell_Row(){
        assertEquals(10, cellsLogic.getCell_Row());
    }
    @Test
    void getCell_col(){
        assertEquals(10, cellsLogic.getCell_Col());
    }


    @Test
    void changeCellAlive() {
        boolean first= cellsLogic.getCellAlive(3,3);
        cellsLogic.changeCellAlive(3,3);
        assertEquals(!first, cellsLogic.getCellAlive(3,3));
    }

    @Test
    void placeCell(){
        cellsLogic.placeCell(4,4,true);
        assertEquals(true, cellsLogic.getCellAlive(4,4));
        cellsLogic.placeCell(4,4,false);
        assertEquals(false, cellsLogic.getCellAlive(4,4));
    }

    @Test
    void clearCells() {
        for(int row = 0; row< cellsLogic.getCell_Row(); row++){
            for(int col = 0; col< cellsLogic.getCell_Col(); col++){
                cellsLogic.placeCell(row,col,true);
            }
        }
        cellsLogic.clearCells();
        for(int row = 0; row< cellsLogic.getCell_Row(); row++){
            for(int col = 0; col< cellsLogic.getCell_Col(); col++){
                assertEquals(false, cellsLogic.getCellAlive(row,col));
            }
        }
    }

    @Test
    void randCreateCells() {
       // CellsControl mockedcellsControl=mock(CellsControl.class);
    }

    @Test
    void evolutionStepCells() {
        cellsLogic.clearCells();
        cellsLogic.evolutionStepCells();
        assertEquals(false, cellsLogic.getCellAlive(5,5));
       //三个邻居为生，cell为死
        cellsLogic.placeCell(4,4,true);
        cellsLogic.placeCell(5,6,true);
        cellsLogic.placeCell(6,4,true);
        cellsLogic.evolutionStepCells();
        assertEquals(true, cellsLogic.getCellAlive(5,5));
      //三个邻居为生，cell为生
        cellsLogic.placeCell(5,5,true);
        cellsLogic.placeCell(4,4,true);
        cellsLogic.placeCell(5,6,true);
        cellsLogic.placeCell(6,4,true);
        cellsLogic.evolutionStepCells();
        assertEquals(true, cellsLogic.getCellAlive(5,5));
        //两个邻居为生，cell为死
        cellsLogic.clearCells();
        cellsLogic.placeCell(4,4,true);
        cellsLogic.placeCell(5,6,true);
        cellsLogic.evolutionStepCells();
        assertEquals(false, cellsLogic.getCellAlive(5,5));
        //两个邻居为生，cell为生
        cellsLogic.clearCells();
        cellsLogic.placeCell(5,5,true);
        cellsLogic.placeCell(4,4,true);
        cellsLogic.placeCell(5,6,true);
        cellsLogic.evolutionStepCells();
        assertEquals(true, cellsLogic.getCellAlive(5,5));
    }
    @Test
    void getCellNeighborNum(){
        cellsLogic.clearCells();
        //测试有0个cell的细胞
        assertEquals(0, cellsLogic.getCellNeighborNum(5,5));
        cellsLogic.placeCell(5,5,true);
        cellsLogic.placeCell(4,4,true);
        cellsLogic.placeCell(5,6,true);
        cellsLogic.placeCell(5,4,true);
        cellsLogic.placeCell(6,5,true);
        //测试有4个邻居的cell
        assertEquals(4, cellsLogic.getCellNeighborNum(5,5));
        cellsLogic.placeCell(4,5,true);
        cellsLogic.placeCell(4,6,true);
        cellsLogic.placeCell(6,4,true);
        cellsLogic.placeCell(6,6,true);
        //测试有8个邻居的cell
        assertEquals(8, cellsLogic.getCellNeighborNum(5,5));
        //测试左上角的细胞
        assertEquals(0, cellsLogic.getCellNeighborNum(0,0));
        cellsLogic.placeCell(0,1,true);
        cellsLogic.placeCell(1,0,true);
        assertEquals(2, cellsLogic.getCellNeighborNum(0,0));
        //测试右下脚的细胞
        assertEquals(0, cellsLogic.getCellNeighborNum(9,9));
        cellsLogic.placeCell(8,9,true);
        cellsLogic.placeCell(8,8,true);
        assertEquals(2, cellsLogic.getCellNeighborNum(9,9));
    }
}