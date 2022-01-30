package HIT.UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 程序入口，主窗口Frame
 */
public class App {

    public static JFrame frame;

    public static JPanel mainPanelCenter;

    public static DatabasePanel databasePanel;//GPA和成绩侧栏
    public static SettingPanel settingPanel;//设置侧栏

    /**
     * 程序入口main
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                App window = new App();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 构造，创建APP
     */
    public App() throws SQLException, ClassNotFoundException, IOException {
        initialize();
    }

    /**
     * 初始化frame内容
     */
    private void initialize() throws SQLException, ClassNotFoundException, IOException {
        // 设置系统默认样式
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 初始化主窗口
        frame = new JFrame();
        frame.setBounds(UiConsts.MAIN_WINDOW_X, UiConsts.MAIN_WINDOW_Y, UiConsts.MAIN_WINDOW_WIDTH,
                UiConsts.MAIN_WINDOW_HEIGHT);
        frame.setTitle(UiConsts.APP_NAME);
        frame.setIconImage(UiConsts.IMAGE_ICON);
        frame.setBackground(UiConsts.MAIN_BACK_COLOR);
        JPanel mainPanel = new JPanel(true);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BorderLayout());

        ToolBarPanel toolbar = new ToolBarPanel();
        databasePanel = new DatabasePanel();
        settingPanel = new SettingPanel();

        mainPanel.add(toolbar, BorderLayout.WEST);

        mainPanelCenter = new JPanel(true);
        mainPanelCenter.setLayout(new BorderLayout());

        mainPanel.add(mainPanelCenter, BorderLayout.CENTER);


        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}