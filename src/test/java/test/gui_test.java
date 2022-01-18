package test;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.IOException;

public class gui_test {
    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame jf=new JFrame();
        jf.setSize(1250,700);
        JPanel jp=new JPanel();
        jp.setPreferredSize(new Dimension(1250,700));


        Object[][] a=new Object[1110][1];
        Object[] b= {0};
        for(int i=0;i<1110;i++){
            for(int j=0;j<1;j++){
                a[i][j]=0;
            }
        }
        JTable jtable=new JTable(a,b);

        for(int i=0;i<jtable.getColumnCount();i++) {
            TableColumn column = jtable.getColumnModel().getColumn(i);
            column.setMaxWidth(100);
            column.setMinWidth(70);
        }
        JScrollPane jsp = new JScrollPane(jtable);
        jsp.setPreferredSize(new Dimension(1200,500));
        //jtable.setPreferredSize(new Dimension(1200,500));
        jp.add(jsp);
        jf.add(jp);

        jf.setVisible(true);
    }
}
