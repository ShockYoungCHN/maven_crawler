package HIT.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;


/**
 * 数据库设置面板
 */
public class DatabasePanel extends JPanel {
    public static JPanel panelDisplay;//指总成绩一栏按钮
    public static JPanel PanelCal;//指GPA一栏按钮
    public static JPanel databasePanel;//实时显示内容的Panel;
    private static DatabasePanelDisplay databasePanelDisplay = new DatabasePanelDisplay();//指总成绩一栏中的内容
    private static DatabasePanelCalculate databasePanelCalculate = new DatabasePanelCalculate();;//指GPA一栏中的内容

    /**
     * 构造
     */
    public DatabasePanel() throws SQLException, ClassNotFoundException {
        synchronized (this) {
            initialize();
        }
        addComponent();
        addListener();
    }

    /**
     * 初始化面板
     */
    private void initialize() throws SQLException, ClassNotFoundException {
        this.setBackground(UiConsts.MAIN_BACK_COLOR);
        this.setLayout(new BorderLayout());
        databasePanel=new JPanel();
        databasePanelDisplay.setPreferredSize(new Dimension(1000,700));
        databasePanelDisplay.reSet();
        databasePanelCalculate.reSet();
    }

    /**
     * 为面板添加组件
     */
    private void addComponent() throws SQLException {

        this.add(getUpPanel(), BorderLayout.NORTH);
        this.add(getCenterPanel(), BorderLayout.CENTER);

    }

    /**
     * 面板上部
     *
     * @return
     */
    private JPanel getUpPanel() {
        JPanel panelUp = new JPanel();
        panelUp.setBackground(UiConsts.MAIN_BACK_COLOR);
        panelUp.setLayout(new FlowLayout(FlowLayout.LEFT, UiConsts.MAIN_H_GAP, 5));

        JLabel labelTitle = new JLabel("成绩和GPA");
        labelTitle.setFont(UiConsts.FONT_TITLE);
        labelTitle.setForeground(UiConsts.TOOL_BAR_BACK_COLOR);
        panelUp.add(labelTitle);

        return panelUp;
    }

    /**
     * 面板中部
     *
     * @return
     */
    private JPanel getCenterPanel() throws SQLException {//中间面板包含两个按钮
        // 中间面板
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(UiConsts.MAIN_BACK_COLOR);
        panelCenter.setLayout(new BorderLayout());

        // 数据库列表Panel
        JPanel panelList = new JPanel();
        Dimension preferredSize = new Dimension(245, UiConsts.MAIN_WINDOW_HEIGHT);
        panelList.setPreferredSize(preferredSize);
        panelList.setBackground(new Color(62, 62, 62));
        panelList.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        panelDisplay = new JPanel();
        panelDisplay.setBackground(new Color(69, 186, 121));
        panelDisplay.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        Dimension preferredSizeListItem = new Dimension(245, 48);
        panelDisplay.setPreferredSize(preferredSizeListItem);
        PanelCal = new JPanel();
        PanelCal.setBackground(UiConsts.TOOL_BAR_BACK_COLOR);
        PanelCal.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 13));
        PanelCal.setPreferredSize(preferredSizeListItem);

        JLabel labelDisplay = new JLabel("全部成绩");
        JLabel labelCal = new JLabel("GPA计算");
        Font fontListItem = new Font("宋体", 0, 15);
        labelDisplay.setFont(fontListItem);
        labelCal.setFont(fontListItem);
        labelDisplay.setForeground(Color.white);
        labelCal.setForeground(Color.white);
        panelDisplay.add(labelDisplay);
        PanelCal.add(labelCal);

        panelList.add(panelDisplay);
        panelList.add(PanelCal);

        //成绩信息的Panel
        databasePanelDisplay.setBackground(UiConsts.MAIN_BACK_COLOR);
        databasePanelDisplay.setLayout(new BorderLayout());

        panelCenter.add(panelList, BorderLayout.WEST);
        panelCenter.add(databasePanel, BorderLayout.CENTER);//添加databasePanel,databasePanel默认内容为databasePanelDisplay

        return panelCenter;
    }

    /**
     * 添加相关组件的事件监听
     */
    private void addListener() {
        panelDisplay.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                panelDisplay.setBackground(new Color(69, 186, 121));
                PanelCal.setBackground(UiConsts.TOOL_BAR_BACK_COLOR);

                DatabasePanel.databasePanel.removeAll();

                try {
                    databasePanelDisplay.reSet();
                    databasePanel.add(databasePanelDisplay);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                databasePanel.updateUI();
            }
        });

        PanelCal.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                PanelCal.setBackground(new Color(69, 186, 121));
                panelDisplay.setBackground(UiConsts.TOOL_BAR_BACK_COLOR);

                DatabasePanel.databasePanel.removeAll();
                databasePanel.updateUI();
                try {
                    databasePanelCalculate.reSet();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                databasePanel.add(databasePanelCalculate);
                databasePanelCalculate.updateUI();
            }
        });

    }
}