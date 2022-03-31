package LifeGame;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//画游戏地图面板
public class GameMap extends JPanel {
    private CellsLogic Cells_C;
    private int cellLength = 17; //细胞边长
    private int cell_Col;        //细胞列数
    private int cell_Row;        //细胞行数

    public GameMap(CellsLogic CellsC) {
        Cells_C = CellsC;
        this.setBackground(Color.WHITE);
        cell_Row = CellsC.getCell_Row();
        cell_Col = CellsC.getCell_Col();
        //添加鼠标监听器
        this.addMouseListener(new Map_MouseListner());
        this.addMouseMotionListener(new Map_MouseListner());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int row = 0; row < cell_Row; row++) {
            for (int col = 0; col < cell_Col; col++) {
                boolean alive = Cells_C.getCellAlive(row, col);
                draw(g, col * cellLength, row * cellLength, cellLength, alive);
            }
        }
    }
    
    /*@Override
    public Dimension getPreferredSize() 
    {
        int row = Cells_C.getCell_Row();
        int col = Cells_C.getCell_Col();
        int length = LifeGame.Cell.getLength();
        return new Dimension(row*length,col*length);
    }*/
    
    //画边框
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //横线
        g.drawLine(0, 0, cell_Col * cellLength, 0);
        g.drawLine(0, cell_Row * cellLength, cell_Col * cellLength, cell_Row * cellLength);
        //竖线
        g.drawLine(0, 0, 0, cell_Row * cellLength);
        g.drawLine(cell_Col * cellLength, 0, cell_Col * cellLength, cell_Row * cellLength);

        /*//画横线
        for ( int row = 0; row <= Row; row++ )
        {
            g.drawLine(0, row * Length, Col * Length, row * Length);
        }
        //画竖线
        for ( int col = 0; col <= Col; col++ )
        {
            g.drawLine(col * Length,0, col * Length, Row * Length);
        }*/
    }

    public void draw(Graphics g, int x, int y, int size, boolean alive) {
        //设置颜色为白
        //g.setColor(Color.white);
        //画矩形框
        //g.drawRect(x, y, size, size);
        if (alive) {
            //设置颜色为黑
            g.setColor(Color.black);
            //在矩形区域涂色
            g.fillRect(x + 1, y + 1, size - 2, size - 2);
        }
    }

    public void clearMap() {
        Cells_C.clearCells();
        repaint();  //更新地图面板
    }

    public void randCreate() {
        Cells_C.randCreateCells();
        repaint();  //更新地图面板
        /*try {
            Thread.sleep(200);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }*/
    }

    public int getCellLength() {
        return cellLength;
    }


    public void evolutionStep() {
        Cells_C.evolutionStepCells();
        repaint();  //更新地图面板
    }

    //实现MouseLister和MouseMotionListener的借口，实现鼠标响应的函数
    public class Map_MouseListner implements MouseListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Point position = e.getPoint();
            int row = position.y / cellLength;
            int col = position.x / cellLength;
            if (row >= 0 && row < cell_Row && col >= 0 && col < cell_Col) {
                Cells_C.changeCellAlive(row, col);
                repaint();
            }
            //System.out.println(position.x+" "+position.y);
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) { mouseReleased(e); }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }


    public static void main(String[] args) {
        CellsLogic cellsLogic = new CellsLogic(30,30);
        GameMap gameMap = new GameMap(cellsLogic);
        JFrame frame = new JFrame("生命游戏");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.add(gameMap);
        frame.setBounds(200,200,600,600);
        //frame.pack();
        //去掉标题栏
        //frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
