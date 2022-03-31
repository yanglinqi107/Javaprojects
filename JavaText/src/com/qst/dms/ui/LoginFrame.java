package com.qst.dms.ui;

import com.qst.dms.entity.User;
import com.qst.dms.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

public class LoginFrame extends JFrame {
    // 主面板
    private JPanel p;
    // 标签
    private JLabel lblName, lblPwd;
    // 用户名，文本框
    private JTextField txtName;
    // 密码和密码框
    private JPasswordField txtPwd;
    // 确认和取消和注册，按钮
    private JButton btnOk, btnCancle,btnRegist;
    // 登录的用户
    private static User user;
    // 用户业务类
    private UserService userService;
    public LoginFrame() {
        super("用户登录");

        // 实例化用户业务类对象
        userService = new UserService();

        // 设置窗体的icon
        ImageIcon icon = new ImageIcon("images\\dms.png");
        this.setIconImage(icon.getImage());

        // 设置面板布局，网格布局
        p = new JPanel(new GridLayout(3, 1));
        // 实例化组件
        lblName = new JLabel("用  户  名：");
        lblPwd = new JLabel("密        码：");
        txtName = new JTextField(16);
        txtPwd = new JPasswordField(16);

        btnOk = new JButton("确定");
        // 注册监听器，监听确定按钮
        btnOk.addActionListener(new SureListener());
        btnCancle = new JButton("重置");
        // 注册监听器，监听重置按钮
        btnCancle.addActionListener(new ResetListener());
        btnRegist = new JButton("注册");
        // 注册监听器，监听注册按钮
        btnRegist.addActionListener(new RegistListener());
        // 将组件分组放入面板，然后将小面板放入主面板
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(lblName);
        p1.add(txtName);
        p.add(p1);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(lblPwd);
        p2.add(txtPwd);
        p.add(p2);
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p3.add(btnOk);
        p3.add(btnCancle);
        p3.add(btnRegist);
        p.add(p3);
        // 主面板放入窗体中
        this.add(p);
        // 设置窗体大小和位置
        this.setSize(350, 200);
        this.setLocation(400, 400);
        // 设置窗体不可改变大小
        this.setResizable(false);

        // 设置窗体初始可见
        this.setVisible(true);
    }

    // 监听类，负责处理确认按钮的业务逻辑
    class SureListener implements ActionListener{
        // 重写actionPerFormed()方法，事件处理方法
        public void actionPerformed(ActionEvent e){
            // 获取用户输入的数据
            String userName = txtName.getText().trim();
            String password = new String(txtPwd.getPassword());
            //根据用户名查找用户
            user = userService.findUserByName(userName);
            try {
                password = userService.EncoderByMd5(password);
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                unsupportedEncodingException.printStackTrace();
            }
            if(user == null){
                //输出提示信息
                JOptionPane.showMessageDialog(null,"找不到用户！","错误提示",JOptionPane.ERROR_MESSAGE);
            }
            else if(password.equals(user.getPassword())){
                //输出提示信息
                JOptionPane.showMessageDialog(null,"登录成功！","成功提示",JOptionPane.PLAIN_MESSAGE);
                try {
                    new MainFrametest2();
                } catch (UnknownHostException unknownHostException) {
                    unknownHostException.printStackTrace();
                }
                setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(null,"密码错误！","错误提示",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //监听类，负责处理注册按钮
    class RegistListener implements ActionListener{
        // 重写actionPerFormed()方法，事件处理方法
        public void actionPerformed(ActionEvent e){
            new RegistFrame();
        }
    }

    // 监听类，负责处理重置按钮
    public class ResetListener implements ActionListener {
        // 重写actionPerFormed()方法，重置组件内容事件处理方法
        public void actionPerformed(ActionEvent e) {
            // 清空姓名、密码、确认密码内容
            txtName.setText("");
            txtPwd.setText("");
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
