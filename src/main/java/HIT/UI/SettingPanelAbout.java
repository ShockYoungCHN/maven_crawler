package HIT.UI;

import javax.swing.*;
import java.awt.*;

public class SettingPanelAbout extends JPanel {
    private static final long serialVersionUID = 1L;
    /**
     * 构造
     */
    public SettingPanelAbout() {
        initialize();
        addComponent();
    }

    /**
     * 初始化
     */
    private void initialize() {
        this.setBackground(UiConsts.MAIN_BACK_COLOR);
        this.setLayout(new BorderLayout());
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        this.add(getCenterPanel(), BorderLayout.CENTER);
    }

    /**
     * 中部面板
     *
     * @return
     */
    private JPanel getCenterPanel() {
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(8,1));

        JLabel labelContract =new JLabel("使用协议与用户须知");
        labelContract.setFont(UiConsts.FONT_TITLE);
        JLabel labelWarning =new JLabel();//添加警告信息
        labelWarning.setText("<html>1，尽量不要在选课高峰期使用此软件，这可能会给其他同学和你自己带来不必要的麻烦。<br/>"+
                "2，本软件严格遵守《中华人民共和国网络安全法》，不收集、不贩卖任何个人信息，所有数据全部在用户本地保存、处理。<br/>" +
                "3，严禁使用本软件以及本软件内的相关代码，从事任何违反《网络安全法》和《刑法》以及其他中华人民共和国法律的活动。<br/>"+
                "4，由于本人精力有限，软件功能并不完备，如有合作开发意向欢迎通过下方邮箱联系我。"+
                "</html>");
        // 字体
        labelWarning.setFont(UiConsts.FONT_NORMAL);


        JTextPane contactInfo = new JTextPane();
        contactInfo.setContentType("text/html");
        contactInfo.setText("<html> <font size=\"5\" >If there are any problems, feel free to contact <font>" +
                "<font size=\"5\"  color=\"blue\"> <u>SamuraiYoung@outlook.com</u> <font>  " +
                ".</html>");
        contactInfo.setEditable(false);
        contactInfo.setBackground(Color.getColor("#F0F0F0"));//设置联系方式的背景色，和JPanel一致
        // 大小
        Dimension size = new Dimension(200, 30);
        labelWarning.setPreferredSize(size);
        panelCenter.add(labelContract);
        panelCenter.add(labelWarning);
        panelCenter.add(new JLabel());
        panelCenter.add(contactInfo);
        return panelCenter;
    }
}
