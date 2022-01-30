package HIT.UI;

import HIT.Database2Swing;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.SQLException;

public class DatabasePanelDisplay extends JPanel{//此处仍是gui部分
    JPanel tablePanel;
    public void reSet() throws SQLException {
        removeAll();
        tablePanel=getCenterPanel();
        add(tablePanel,BorderLayout.NORTH);
    }
    static JPanel getCenterPanel() throws SQLException {
        // 中间面板
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(UiConsts.MAIN_BACK_COLOR);
        GridBagLayout gbl=new GridBagLayout();
        panelCenter.setLayout(gbl);

        Database2Swing database2Swing=new Database2Swing();
        JTable jTable=database2Swing.Database2JTbale();

        TableColumn column = jTable.getColumnModel().getColumn(0);
        column.setMaxWidth(50);
        column.setMinWidth(50);

        column = jTable.getColumnModel().getColumn(1);//学年学期
        column.setMaxWidth(60);
        column.setMinWidth(60);

        column = jTable.getColumnModel().getColumn(2);
        column.setMaxWidth(100);
        column.setMinWidth(100);

        column = jTable.getColumnModel().getColumn(3);//课程资源
        column.setMaxWidth(60);
        column.setMinWidth(60);

        column = jTable.getColumnModel().getColumn(4);
        column.setMaxWidth(140);
        column.setMinWidth(140);

        column = jTable.getColumnModel().getColumn(5);//课程性质
        column.setMaxWidth(60);
        column.setMinWidth(60);

        column = jTable.getColumnModel().getColumn(6);//课程类别
        column.setMaxWidth(160);
        column.setMinWidth(160);

        column = jTable.getColumnModel().getColumn(7);//学分
        column.setMaxWidth(40);
        column.setMinWidth(40);

        column = jTable.getColumnModel().getColumn(8);//是否考试课
        column.setMaxWidth(70);
        column.setMinWidth(70);

        column = jTable.getColumnModel().getColumn(9);//参与学分绩
        column.setMaxWidth(70);
        column.setMinWidth(70);

        column = jTable.getColumnModel().getColumn(10);//补考重修标记
        column.setMaxWidth(90);
        column.setMinWidth(90);

        column = jTable.getColumnModel().getColumn(11);//总成绩
        column.setMaxWidth(70);
        column.setMinWidth(70);


        JScrollPane jsp = new JScrollPane(jTable);
        //jsp.setPreferredSize(new Dimension(800,800));有了GridBagConstraints就不需要设置JScrollPane的大小了


        GridBagConstraints gbc=new GridBagConstraints();//实例化这个对象用来对组件进行管理
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx=1;//横向填满整个区域，重要！
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.fill=GridBagConstraints.BOTH;
        gbl.setConstraints(jsp,gbc);

        panelCenter.add(jsp);

        return panelCenter;
    }
}
