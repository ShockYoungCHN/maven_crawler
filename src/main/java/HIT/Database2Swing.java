package HIT;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database2Swing {
    //生成表格数据
    /**
     * 返回生成JTable所需的两组数组
     * @return
     */
    public DefaultTableModel queryData() throws SQLException {
        List<Lesson> list=new ArrayList<>();
        List<String> headGet=new ArrayList<>();
        DBOperation dbOperation=new DBOperation();

        dbOperation.queryAllLessons(list, headGet);
        Object[][] data=new Object[list.size()][headGet.size()];
        Object[] head=new Object[headGet.size()];
        for(int j=0;j<headGet.size();j++){
            head[j]=headGet.get(j);
        }
        for(int i=0;i<list.size();i++){
            for(int j=0;j<headGet.size();j++){
                data[i][0]=list.get(i).getNo();
                data[i][1]=list.get(i).getTerm();
                data[i][2]=list.get(i).getCollege();
                data[i][3]=list.get(i).getId();
                data[i][4]=list.get(i).getName();
                data[i][5]=list.get(i).getCourseNature();
                data[i][6]=list.get(i).getCourseSort();
                data[i][7]=list.get(i).getCredit();
                data[i][8]=list.get(i).getIsTestCourse();
                data[i][9]=list.get(i).getIsAve();
                data[i][10]=list.get(i).getRelearnMark();
                data[i][11]=list.get(i).getFinalGrade();
            }
        }
        return new DefaultTableModel(data,head);
    }

    public JTable Database2JTbale() throws SQLException {
        return new JTable(queryData());
    }
}
