package LifeGame;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
    // 主面板
    private JPanel panel;
    //菜单栏
    //private JMenuBar mBar;
    //开始按钮
    private JButton JButton_START;
    //停止按钮
    private JButton JButton_END;
    //清空按钮
    private JButton JButton_CLEAR;
    //随机生成按钮
    private JButton JButton_RANDCREAT;
    //单步演化
    private JButton JButton_STEP;
    //游戏地图，子面板
    private GameMap gameMap;
    //行、列格子数
    //private final int Size=80;
    private int Rows = 55;
    private int Cols = 90;
    //判断条件，控制演化进行和终止
    boolean IsEvolution = false;
    //每个格子的长度
    //private final int Length=30;
    public GameGUI() {
        super("生命游戏");
        super.doLayout();
        //关闭界面，程序结束
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗体的icon
        //ImageIcon icon = new ImageIcon("images\\dms.png");
        //this.setIconImage(icon.getImage());

        /*//面板挂菜单栏
        mBar = new JButtonBar();
        //setJButtonBar(mBar);
        mBar.setVisible(true);
        mBar.setSize(Size*Length,30);
        //mBar.setBackground(Color.WHITE);
        panel.add(mBar);*/

        JButton_START = new JButton();
        JButton_START.setText("开始演化");
        // 注册监听器，监听开始演化按钮
        JButton_START.addActionListener(new StartListener());
        //JButton_START.setSize(20,10);
        JButton_END = new JButton("结束演化");
        // 注册监听器，监听结束演化按钮
        JButton_END.addActionListener(new EndListener());
        //JButton_END.setText("结束演化");
        //JButton_END.setSize(20,10);
        JButton_CLEAR = new JButton("清空");
        // 注册监听器，监听清空按钮
        JButton_CLEAR.addActionListener(new ClearListener());
        //JButton_CLEAR.setText("清空");
        //JButton_CLEAR.setPreferredSize(new Dimension(20,10));
        JButton_RANDCREAT = new JButton();
        JButton_RANDCREAT.setText("随机生成");
        // 注册监听器，监听随机生成按钮
        JButton_RANDCREAT.addActionListener(new RandCreatListener());
        //JButton_RANDCREAT.setSize(20,10);
        JButton_STEP = new JButton();
        JButton_STEP.setText("单步演化");
        // 注册监听器，监听单步演化按钮
        JButton_STEP.addActionListener(new StepListener());
        //JButton_STEP.setSize(20,10);

        //创建游戏地图面板，设置大小
        CellsLogic cellsLogic = new CellsLogic(Rows, Cols);
        gameMap = new GameMap(cellsLogic);
        //gameMap.setBackground(Color.WHITE);
        gameMap.setBounds(0, 50, Cols * gameMap.getCellLength() + 1, Rows * gameMap.getCellLength() + 1);

        //创建子面板p1，添加按钮，设置子面板大小
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
        //取消主面板对加入控件的默认布局
        panel.setLayout(null);
        //设置主面板的背景颜色,白色
        panel.setBackground(Color.WHITE);
        //panel.setLayout(new GridLayout(20, 20));
        this.add(panel);
        //将子面板添加进主面板
        panel.add(p1);
        //将游戏地图面板添加到主面板
        panel.add(gameMap);
        //设置窗体大小
        this.setSize(Cols * gameMap.getCellLength() + 20, Rows * gameMap.getCellLength() + 100);
        //设置窗体位置
        //this.setLocation(600,400);
        // JFrame在屏幕居中
        this.setLocationRelativeTo(null);
        //设置窗体不可改变大小
        this.setResizable(false);
        // 设置窗体初始可见
        this.setVisible(true);
    }

    //监听类，负责处理清空按钮
    class ClearListener implements ActionListener {
        // 重写actionPerFormed()方法，事件处理方法
        public void actionPerformed(ActionEvent e) {
            IsEvolution = false;
            gameMap.clearMap();
        }
    }

    //监听类，负责处理随机生成按钮
    class RandCreatListener implements ActionListener {
        // 重写actionPerFormed()方法，事件处理方法
        public void actionPerformed(ActionEvent e) {
            IsEvolution = false;
            gameMap.randCreate();
        }
    }

    //监听类，负责处理开始演化按钮
    class StartListener implements ActionListener {
        // 重写actionPerFormed()方法，事件处理方法
        public void actionPerformed(ActionEvent e) {
            if (IsEvolution) {
                return;
            }
            IsEvolution = true;
            //开始不断演化的线程
            EvolutionThread evolutionThread = new EvolutionThread();
            evolutionThread.start();
        }
    }

    //监听类，负责处理单步演化按钮
    class StepListener implements ActionListener {
        // 重写actionPerFormed()方法，事件处理方法
        public void actionPerformed(ActionEvent e) {
            IsEvolution = false;
            gameMap.evolutionStep();
            //repaint(10,0,50,Cols*LifeGame.Cell.getLength() + 1,Rows*LifeGame.Cell.getLength() + 1);
        }
    }

    //监听类，负责处理结束演化按钮
    class EndListener implements ActionListener {
        // 重写actionPerFormed()方法，事件处理方法
        public void actionPerformed(ActionEvent e) {
            IsEvolution = false;
        }
    }

    //线程类，用于不断演化
    public class EvolutionThread extends Thread {
        //重写run()方法
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

    //主函数，程序入口
    public static void main(String[] args) {
        new GameGUI();
    }
}




