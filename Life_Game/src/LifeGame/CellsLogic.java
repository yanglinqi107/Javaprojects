package LifeGame;

//对细胞数组的控制，对应游戏地图的格子
public class CellsLogic {
    private boolean[][] Cells; //细胞的二维数组
    private int Cell_Row;   //细胞的行数
    private int Cell_Col;   //细胞的列数

    public CellsLogic(int row, int col) {
        Cell_Row = row;
        Cell_Col = col;
        //创建对象
        Cells = new boolean[Cell_Row][Cell_Col];
        for (int i = 0; i < Cell_Row; ++i) {
            for (int j = 0; j < Cell_Col; ++j) {
                Cells[i][j] = false;
            }
        }
    }

    //获取细胞的行数
    public int getCell_Row() {
        return Cell_Row;
    }

    //获取细胞的列数
    public int getCell_Col() {
        return Cell_Col;
    }

    //获取某行列细胞的状态
    public boolean getCellAlive(int row, int col) {
        return Cells[row][col];
    }

    //改变某行列细胞的状态
    public void changeCellAlive(int row, int col) {
        Cells[row][col] = !Cells[row][col];
    }

    //设定某位置细胞的状态
    public void placeCell(int row, int col, boolean alive) {
        //Cells[row][col] = new LifeGame.Cell();
        Cells[row][col] = alive;
    }

    //清空，所有细胞死亡
    public void clearCells() {
        for (int row = 0; row < Cell_Row; ++row) {
            for (int col = 0; col < Cell_Col; ++col) {
                Cells[row][col] = false;
            }
        }
    }

    //细胞随机改变生命状态
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("PREDICTABLE_RANDOM")
    public void randCreateCells() {
        clearCells();
        for (int row = 0; row < Cell_Row; ++row) {
            for (int col = 0; col < Cell_Col; ++col) {
                if (Math.random() < 0.2) {
                    Cells[row][col] = !Cells[row][col];
                }
            }
        }
    }

    //细胞演化一次
    public void evolutionStepCells() {
        boolean[][] CellsNext = new boolean[Cell_Row][Cell_Col];
        for (int row = 0; row < Cell_Row; ++row) {
            for (int col = 0; col < Cell_Col; ++col) {
                boolean cellTemp = false;
                int count = getCellNeighborNum(row, col);
                if (count == 2) {
                    cellTemp = Cells[row][col];
                }
                if (count == 3) {
                    cellTemp = true;
                }
                CellsNext[row][col] = cellTemp;
            }
        }

        for (int row = 0; row < Cell_Row; ++row) {
            for (int col = 0; col < Cell_Col; ++col) {
                Cells[row][col] = CellsNext[row][col];
            }
        }
    }

    //计算某细胞的邻居中活细胞的数量
    public int getCellNeighborNum(int row, int col) {
        int count = 0;
        boolean tag = false;
        if (row == 0 || col == 0 || row + 1 == Cell_Row || col + 1 == Cell_Col) {
            tag = true;
        }
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                int nei_row = row + i;
                int nei_col = col + j;
                if (tag) {
                    if (nei_row < 0) {
                        nei_row += Cell_Row;
                    }
                    if (nei_col < 0) {
                        nei_col += Cell_Col;
                    }
                    nei_row %= Cell_Row;
                    nei_col %= Cell_Col;
                }
                if (Cells[nei_row][nei_col]) {
                    count++;
                }
            }
        }
        if (Cells[row][col]) {
            count--;
        }
        return count;
    }
}
