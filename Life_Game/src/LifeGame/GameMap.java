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

//����Ϸ��ͼ���
public class GameMap extends JPanel {
    private CellsLogic Cells_C;
    private int cellLength = 17; //ϸ���߳�
    private int cell_Col;        //ϸ������
    private int cell_Row;        //ϸ������

    public GameMap(CellsLogic CellsC) {
        Cells_C = CellsC;
        this.setBackground(Color.WHITE);
        cell_Row = CellsC.getCell_Row();
        cell_Col = CellsC.getCell_Col();
        //�����������
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
    
    //���߿�
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //����
        g.drawLine(0, 0, cell_Col * cellLength, 0);
        g.drawLine(0, cell_Row * cellLength, cell_Col * cellLength, cell_Row * cellLength);
        //����
        g.drawLine(0, 0, 0, cell_Row * cellLength);
        g.drawLine(cell_Col * cellLength, 0, cell_Col * cellLength, cell_Row * cellLength);

        /*//������
        for ( int row = 0; row <= Row; row++ )
        {
            g.drawLine(0, row * Length, Col * Length, row * Length);
        }
        //������
        for ( int col = 0; col <= Col; col++ )
        {
            g.drawLine(col * Length,0, col * Length, Row * Length);
        }*/
    }

    public void draw(Graphics g, int x, int y, int size, boolean alive) {
        //������ɫΪ��
        //g.setColor(Color.white);
        //�����ο�
        //g.drawRect(x, y, size, size);
        if (alive) {
            //������ɫΪ��
            g.setColor(Color.black);
            //�ھ�������Ϳɫ
            g.fillRect(x + 1, y + 1, size - 2, size - 2);
        }
    }

    public void clearMap() {
        Cells_C.clearCells();
        repaint();  //���µ�ͼ���
    }

    public void randCreate() {
        Cells_C.randCreateCells();
        repaint();  //���µ�ͼ���
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
        repaint();  //���µ�ͼ���
    }

    //ʵ��MouseLister��MouseMotionListener�Ľ�ڣ�ʵ�������Ӧ�ĺ���
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
        JFrame frame = new JFrame("������Ϸ");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.add(gameMap);
        frame.setBounds(200,200,600,600);
        //frame.pack();
        //ȥ��������
        //frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
