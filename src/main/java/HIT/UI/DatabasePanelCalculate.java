package HIT.UI;

import HIT.DBUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabasePanelCalculate extends JPanel {
    static JPanel pku=new JPanel();
    static JPanel wes=new JPanel();
    static JPanel standard4=new JPanel();
    static JPanel standard4_2=new JPanel();
    static JPanel Canada=new JPanel();

    public static void initialize(){
        JTable jTable;
        Object[] head=new String[]{"成绩","GPA"};
        Object[][] body;

        pku.setLayout(new BorderLayout());
        pku.add(new JLabel("北大算法，即哈工大(威海)现行算法"),BorderLayout.NORTH);
        body= new String[][]{
                {"100～90","4.0"},
                {"89～85","3.7"},
                {"84～82","3.3"},
                {"81～78","3.0"},
                {"77～75","2.7"},
                {"74～72","2.3"},
                {"71～68","2.0"},
                {"67～64","1.5"},
                {"63～60","1.0"},
                {"59～0","0"}
        };
        jTable=new JTable(body,head);
        jTable.setShowGrid(false);
        pku.add(jTable,BorderLayout.CENTER);


        standard4.setLayout(new BorderLayout());
        standard4.add(new JLabel("标准四分算法"),BorderLayout.NORTH);
        body= new String[][]{
                {"100～90","4.0"},
                {"89～80","3.0"},
                {"79～70","2.0"},
                {"69～60","1.0"},
                {"59～0","0"}
        };
        jTable=new JTable(body,head);
        jTable.setShowGrid(false);
        standard4.add(jTable,BorderLayout.CENTER);


        wes.setLayout(new BorderLayout());
        wes.add(new JLabel("WES算法"),BorderLayout.NORTH);
        body= new String[][]{
                {"100～90","4.0"},
                {"89～80","3.0"},
                {"79～70","2.0"},
                {"69～60","1.0"},
                {"59～0","0"}
        };
        jTable=new JTable(body,head);
        jTable.setShowGrid(false);
        wes.add(jTable,BorderLayout.CENTER);


        standard4_2.setLayout(new BorderLayout());
        standard4_2.add(new JLabel("改进四分算法(2.0)"),BorderLayout.NORTH);
        body= new String[][]{
                {"100～85","4.0"},
                {"84～75","3.0"},
                {"74～60","2.0"},
                {"59～0","0"}
        };
        jTable=new JTable(body,head);
        jTable.setShowGrid(false);
        standard4_2.add(jTable,BorderLayout.CENTER);


        Canada.setLayout(new BorderLayout());
        Canada.add(new JLabel("加拿大4.3分算法"),BorderLayout.NORTH);
        body= new String[][]{
                {"100～90","4.3"},
                {"89～85","4.0"},
                {"84～80","3.7"},
                {"79～75","3.3"},
                {"74～70","3.0"},
                {"69～65","2.7"},
                {"64～60","2.3"},
                {"59～0","0"}
        };
        jTable=new JTable(body,head);
        jTable.setShowGrid(false);
        Canada.add(jTable,BorderLayout.CENTER);
    }
    public JPanel getCentralPanel(){//DataPanelDisplay中的中部Panel
        JPanel Central=new JPanel();
        GridBagLayout gridBagLayout=new GridBagLayout();
        Central.setLayout(gridBagLayout);

        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.ipady = 40;  //make this gridBagConstraintsomponent tall
        gridBagConstraints.ipadx = 10;  //make this gridBagConstraintsomponent wide 
        gridBagConstraints.anchor=GridBagConstraints.NORTH;
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        gridBagLayout.setConstraints(pku,gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=0;
        gridBagLayout.setConstraints(standard4,gridBagConstraints);

        gridBagConstraints.gridx=3;
        gridBagConstraints.gridy=0;
        gridBagLayout.setConstraints(wes,gridBagConstraints);

        gridBagConstraints.gridx=4;
        gridBagConstraints.gridy=0;
        gridBagLayout.setConstraints(standard4_2,gridBagConstraints);

        gridBagConstraints.gridx=5;
        gridBagConstraints.gridy=0;
        gridBagLayout.setConstraints(Canada,gridBagConstraints);



        return Central;
    }

    public void reSet() throws SQLException, ClassNotFoundException {
        initialize();
        DBUtils db=new DBUtils();
        Connection conn=db.getConnection();
        String sql_pku="select (" +
                "(SELECT IFNULL(SUM(学分*4.0),0) from Grade where 最终成绩>=90) +" +
                "(SELECT IFNULL(SUM(学分*3.7),0) from Grade where (最终成绩>=85 and 最终成绩<90))+" +
                "(SELECT IFNULL(SUM(学分*3.3),0) from Grade where (最终成绩>=82 and 最终成绩<85))+" +
                "(SELECT IFNULL(SUM(学分*3.0),0) from Grade where (最终成绩>=78 and 最终成绩<82))+" +
                "(SELECT IFNULL(SUM(学分*2.7),0) from Grade where (最终成绩>=75 and 最终成绩<78))+" +
                "(SELECT IFNULL(SUM(学分*2.3),0) from Grade where (最终成绩>=72 and 最终成绩<75))+" +
                "(SELECT IFNULL(SUM(学分*2.0),0) from Grade where (最终成绩>=68 and 最终成绩<72))+" +
                "(SELECT IFNULL(SUM(学分*1.5),0) from Grade where (最终成绩>=64 and 最终成绩<68))+" +
                "(SELECT IFNULL(SUM(学分*1.0),0) from Grade where (最终成绩>=60 and 最终成绩<64))" +
                ")/SUM(学分)" +
                "as gpa from Grade;";
        
        String sql_wes="select (" +
                "(SELECT IFNULL(SUM(学分*4.0),0) from Grade where 最终成绩>=85) +" +
                "(SELECT IFNULL(SUM(学分*3.0),0) from Grade where (最终成绩>=70 and 最终成绩<85))+" +
                "(SELECT IFNULL(SUM(学分*2.0),0) from Grade where (最终成绩>=60 and 最终成绩<70))" +
                ")/SUM(学分)" +
                "as gpa from Grade;";

        String sql_standard4_2="select (" +
                "(SELECT IFNULL(SUM(学分*4.0),0) from Grade where 最终成绩>=85) +" +
                "(SELECT IFNULL(SUM(学分*3.0),0) from Grade where (最终成绩>=75 and 最终成绩<85))+" +
                "(SELECT IFNULL(SUM(学分*2.0),0) from Grade where (最终成绩>=60 and 最终成绩<75))" +
                ")/SUM(学分)" +
                "as gpa from Grade;";

        String sql_standard4="select (" +
                "(SELECT IFNULL(SUM(学分*4.0),0) from Grade where 最终成绩>=90) +" +
                "(SELECT IFNULL(SUM(学分*3.0),0) from Grade where (最终成绩>=80 and 最终成绩<90))+" +
                "(SELECT IFNULL(SUM(学分*2.0),0) from Grade where (最终成绩>=70 and 最终成绩<80))+" +
                "(SELECT IFNULL(SUM(学分*1.0),0) from Grade where (最终成绩>=60 and 最终成绩<60))" +
                ")/SUM(学分)" +
                "as gpa from Grade;";

        String sql_Canada="select (" +
                "(SELECT IFNULL(SUM(学分*4.3),0) from Grade where 最终成绩>=90) +" +
                "(SELECT IFNULL(SUM(学分*4.0),0) from Grade where (最终成绩>=85 and 最终成绩<90))+" +
                "(SELECT IFNULL(SUM(学分*3.7),0) from Grade where (最终成绩>=80 and 最终成绩<85))+" +
                "(SELECT IFNULL(SUM(学分*3.3),0) from Grade where (最终成绩>=75 and 最终成绩<80))+" +
                "(SELECT IFNULL(SUM(学分*3.0),0) from Grade where (最终成绩>=70 and 最终成绩<75))+" +
                "(SELECT IFNULL(SUM(学分*2.7),0) from Grade where (最终成绩>=65 and 最终成绩<70))+" +
                "(SELECT IFNULL(SUM(学分*2.3),0) from Grade where (最终成绩>=60 and 最终成绩<65))" +
                ")/SUM(学分)" +
                "as gpa from Grade;";

        ArrayList<String> list=new ArrayList<>();
        list.add(sql_pku);
        list.add(sql_standard4);
        list.add(sql_wes);
        list.add(sql_standard4_2);
        list.add(sql_Canada);

        for(int i=1;i<=list.size();i++) {
            PreparedStatement preparedStatement = conn.prepareStatement(list.get(i-1));
            ResultSet rs = preparedStatement.executeQuery();

            switch (i){
                case 1:pku.add(new JTextField(rs.getString(1)),BorderLayout.SOUTH);break;
                case 2:standard4.add(new JTextField(rs.getString(1)),BorderLayout.SOUTH);break;
                case 3:wes.add(new JTextField(rs.getString(1)),BorderLayout.SOUTH);break;
                case 4:standard4_2.add(new JTextField(rs.getString(1)),BorderLayout.SOUTH);break;
                case 5:Canada.add(new JTextField(rs.getString(1)),BorderLayout.SOUTH);break;
            }
            rs.close();
            preparedStatement.close();
        }
        removeAll();
        JPanel centralPanel=getCentralPanel();
        centralPanel.add(pku);
        centralPanel.add(standard4);
        centralPanel.add(wes);
        centralPanel.add(standard4_2);
        centralPanel.add(Canada);
        add(centralPanel,BorderLayout.CENTER);
    }
}
