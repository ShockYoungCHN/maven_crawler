package test;


import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.IOException;


public class GUI {
    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame jf=new JFrame();
        jf.setSize(1250,700);
        JPanel jp=new JPanel();
        jp.setPreferredSize(new Dimension(1250,700));

        TestCase t=new TestCase();
        JTable jtable=t.getAllTables();
        for(int i=0;i<jtable.getColumnCount();i++) {
            TableColumn column = jtable.getColumnModel().getColumn(i);
            column.setMaxWidth(100);
            column.setMinWidth(70);
        }
        JScrollPane jsp = new JScrollPane(jtable);
        jsp.setPreferredSize(new Dimension(1200,500));
        //jtable.setPreferredSize(new Dimension(1200,500));若此处限制jtable的尺寸，会导致显示不完全
        jp.add(jsp);
        jf.add(jp);

        jf.setVisible(true);
    }
}
