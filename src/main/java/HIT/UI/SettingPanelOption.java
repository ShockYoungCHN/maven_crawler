package HIT.UI;

import HIT.Crawler;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SettingPanelOption extends JPanel {//设置下的第一个登录信息页面
    JTextField textName;
    JPasswordField textword;

    JButton loginButton;
    JCheckBox jCheckBox=new JCheckBox("记住密码");
    /**
     * 构造
     */
    public SettingPanelOption() throws IOException {
        initialize();
        addComponent();
        addListener();
    }

    /**
     * 初始化
     */
    private void initialize() throws IOException {
        this.setBackground(UiConsts.MAIN_BACK_COLOR);
        this.setLayout(new BorderLayout());

        FileInputStream fis=new FileInputStream("src/main/resources/info/login.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
        textName=new JTextField(br.readLine());
        textword=new JPasswordField(br.readLine());
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        this.add(getCenterPanel(), BorderLayout.CENTER);//目前只有一组登录控件
    }

    private JPanel getCenterPanel() {
        JPanel panelCenter =new JPanel();

        // 实例化JLabel标签对象，该对象显示"账号："
        JLabel labName = new JLabel("账号：");
        // 将labName标签添加到窗体上
        panelCenter.add(labName);

        // 实例化JTextField标签对象
        Dimension dim1 = new Dimension(200,30);
        textName.setPreferredSize(dim1);
        panelCenter.add(textName);

        //实例化JLabel标签对象，该对象显示"密码："
        JLabel labpass= new JLabel("密码：");
        //将labpass标签添加到窗体上
        panelCenter.add(labpass);


        //设置大小
        textword.setPreferredSize(dim1);//设置组件大小
        panelCenter.add(textword);
        //实例化JButton组件
        loginButton=new JButton();
        //设置按钮的显示内容
        Dimension dim2 = new Dimension(100,30);
        loginButton.setText("登录并刷新");
        //设置按钮的大小
        loginButton.setSize(dim2);
        //panelCenter.add(jCheckBox);
        panelCenter.add(loginButton);
        return panelCenter;
    }

    private void addListener(){
        loginButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Thread t=new Thread(() -> {
                    FileOutputStream fos;
                    try {
                        fos = new FileOutputStream("src/main/resources/info/login.txt");
                        PrintWriter pw=new PrintWriter(fos);
                        pw.write(textName.getText());
                        pw.write("\r\n");
                        pw.write(textword.getText());
                        pw.flush();

                        Crawler crawler = new Crawler();
                        crawler.fillTheDatabase();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                t.start();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
