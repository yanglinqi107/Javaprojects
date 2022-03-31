package LifeGame;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
    // �����
    private JPanel panel;
    //�˵���
    //private JMenuBar mBar;
    //��ʼ��ť
    private JButton JButton_START;
    //ֹͣ��ť
    private JButton JButton_END;
    //��հ�ť
    private JButton JButton_CLEAR;
    //������ɰ�ť
    private JButton JButton_RANDCREAT;
    //�����ݻ�
    private JButton JButton_STEP;
    //��Ϸ��ͼ�������
    private GameMap gameMap;
    //�С��и�����
    //private final int Size=80;
    private int Rows = 55;
    private int Cols = 90;
    //�ж������������ݻ����к���ֹ
    boolean IsEvolution = false;
    //ÿ�����ӵĳ���
    //private final int Length=30;
    public GameGUI() {
        super("������Ϸ");
        super.doLayout();
        //�رս��棬�������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ���ô����icon
        //ImageIcon icon = new ImageIcon("images\\dms.png");
        //this.setIconImage(icon.getImage());

        /*//���Ҳ˵���
        mBar = new JButtonBar();
        //setJButtonBar(mBar);
        mBar.setVisible(true);
        mBar.setSize(Size*Length,30);
        //mBar.setBackground(Color.WHITE);
        panel.add(mBar);*/

        JButton_START = new JButton();
        JButton_START.setText("��ʼ�ݻ�");
        // ע���������������ʼ�ݻ���ť
        JButton_START.addActionListener(new StartListener());
        //JButton_START.setSize(20,10);
        JButton_END = new JButton("�����ݻ�");
        // ע������������������ݻ���ť
        JButton_END.addActionListener(new EndListener());
        //JButton_END.setText("�����ݻ�");
        //JButton_END.setSize(20,10);
        JButton_CLEAR = new JButton("���");
        // ע���������������հ�ť
        JButton_CLEAR.addActionListener(new ClearListener());
        //JButton_CLEAR.setText("���");
        //JButton_CLEAR.setPreferredSize(new Dimension(20,10));
        JButton_RANDCREAT = new JButton();
        JButton_RANDCREAT.setText("�������");
        // ע�������������������ɰ�ť
        JButton_RANDCREAT.addActionListener(new RandCreatListener());
        //JButton_RANDCREAT.setSize(20,10);
        JButton_STEP = new JButton();
        JButton_STEP.setText("�����ݻ�");
        // ע������������������ݻ���ť
        JButton_STEP.addActionListener(new StepListener());
        //JButton_STEP.setSize(20,10);

        //������Ϸ��ͼ��壬���ô�С
        CellsLogic cellsLogic = new CellsLogic(Rows, Cols);
        gameMap = new GameMap(cellsLogic);
        //gameMap.setBackground(Color.WHITE);
        gameMap.setBounds(0, 50, Cols * gameMap.getCellLength() + 1, Rows * gameMap.getCellLength() + 1);

        //���������p1����Ӱ�ť������������С
        JPanel p1 = new JPanel(new GridLayout(1, 5));
        p1.setBounds(0, 0, Cols * gameMap.getCellLength(), 30);
        p1.add(JButton_CLEAR);
        p1.add(JButton_RANDCREAT);
        p1.add(JButton_START);
        p1.add(JButton_STEP);
        p1.add(JButton_END);

        /*mBar.add(JButton_CLEAR);
        mBar.add(JButton_START);
        mBar.add(JButton_STEP);
        mBar.add(JButton_END);
        mBar.add(JButton_RANDCREAT);*/

        panel = new JPanel();
        //panel.setBorder(BorderFactory.createEmptyBorder(10,10,100,100));
        panel.setSize(Cols * gameMap.getCellLength(), Rows * gameMap.getCellLength());
        //ȡ�������Լ���ؼ���Ĭ�ϲ���
        panel.setLayout(null);
        //���������ı�����ɫ,��ɫ
        panel.setBackground(Color.WHITE);
        //panel.setLayout(new GridLayout(20, 20));
        this.add(panel);
        //���������ӽ������
        panel.add(p1);
        //����Ϸ��ͼ�����ӵ������
        panel.add(gameMap);
        //���ô����С
        this.setSize(Cols * gameMap.getCellLength() + 20, Rows * gameMap.getCellLength() + 100);
        //���ô���λ��
        //this.setLocation(600,400);
        // JFrame����Ļ����
        this.setLocationRelativeTo(null);
        //���ô��岻�ɸı��С
        this.setResizable(false);
        // ���ô����ʼ�ɼ�
        this.setVisible(true);
    }

    //�����࣬��������հ�ť
    class ClearListener implements ActionListener {
        // ��дactionPerFormed()�������¼�������
        public void actionPerformed(ActionEvent e) {
            IsEvolution = false;
            gameMap.clearMap();
        }
    }

    //�����࣬������������ɰ�ť
    class RandCreatListener implements ActionListener {
        // ��дactionPerFormed()�������¼�������
        public void actionPerformed(ActionEvent e) {
            IsEvolution = false;
            gameMap.randCreate();
        }
    }

    //�����࣬������ʼ�ݻ���ť
    class StartListener implements ActionListener {
        // ��дactionPerFormed()�������¼�������
        public void actionPerformed(ActionEvent e) {
            if (IsEvolution) {
                return;
            }
            IsEvolution = true;
            //��ʼ�����ݻ����߳�
            EvolutionThread evolutionThread = new EvolutionThread();
            evolutionThread.start();
        }
    }

    //�����࣬���������ݻ���ť
    class StepListener implements ActionListener {
        // ��дactionPerFormed()�������¼�������
        public void actionPerformed(ActionEvent e) {
            IsEvolution = false;
            gameMap.evolutionStep();
            //repaint(10,0,50,Cols*LifeGame.Cell.getLength() + 1,Rows*LifeGame.Cell.getLength() + 1);
        }
    }

    //�����࣬����������ݻ���ť
    class EndListener implements ActionListener {
        // ��дactionPerFormed()�������¼�������
        public void actionPerformed(ActionEvent e) {
            IsEvolution = false;
        }
    }

    //�߳��࣬���ڲ����ݻ�
    public class EvolutionThread extends Thread {
        //��дrun()����
        public void run() {
            while (IsEvolution) {
                gameMap.evolutionStep();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }

    //���������������
    public static void main(String[] args) {
        new GameGUI();
    }
}




