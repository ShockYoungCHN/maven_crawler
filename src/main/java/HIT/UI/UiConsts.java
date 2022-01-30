package HIT.UI;

import javax.swing.*;
import java.awt.*;

public class UiConsts {
    /**
     * 软件名称,版本(待添加)
     */
    public final static String APP_NAME = "GPA Calculator";

    /**
     * 主窗口大小
     */
    public final static int MAIN_WINDOW_X = 100;
    public final static int MAIN_WINDOW_Y = 80;
    public final static int MAIN_WINDOW_WIDTH = 1300;
    public final static int MAIN_WINDOW_HEIGHT = 636;

    /**
     * 系统当前路径
     */
    public final static String CURRENT_DIR = System.getProperty("user.dir");

    /**
     * 主窗口背景色
     */
    public final static Color MAIN_BACK_COLOR = Color.WHITE;

    /**
     * 工具栏背景色
     */
    public final static Color TOOL_BAR_BACK_COLOR = new Color(37, 174, 96);
    /**
     * 表格线条背景色
     */
    public final static Color TABLE_LINE_COLOR = new Color(229, 229, 229);

    /**
     * 数据库 默认
     */
    public final static ImageIcon ICON_DATABASE = new ImageIcon("src/main/resources/IMG/database.png");
    /**
     * 数据库 激活
     */
    public final static ImageIcon ICON_DATABASE_ENABLE = new ImageIcon("src/main/resources/IMG/databaseEnable.png");

    /**
     * 设置 默认
     */
    public final static ImageIcon ICON_SETTING = new ImageIcon("src/main/resources/IMG/setting.png");
    /**
     * 设置 激活
     */
    public final static ImageIcon ICON_SETTING_ENABLE = new ImageIcon("src/main/resources/IMG/settingEnable.png");

    // 字体
    /**
     * 标题字体
     */
    public final static Font FONT_TITLE = new Font("宋体", 0, 27);
    /**
     * 普通字体
     */
    public final static Font FONT_NORMAL = new Font("宋体", 0, 13);
    /**
     * radio字体
     */
    public final static Font FONT_RADIO = new Font("宋体", 0, 15);


    // 样式布局相关
    /**
     * 主面板水平间隔
     */
    public final static int MAIN_H_GAP = 25;

    /**
     * 主窗口图标
     */
    public final static Image IMAGE_ICON = Toolkit.getDefaultToolkit()
            .getImage("src/main/resources/IMG/GPA_logo.png");
}
