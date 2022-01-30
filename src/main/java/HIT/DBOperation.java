package HIT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBOperation {//数据操作类
    private Connection conn=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;

    /**
     *获取部分表内容(1,2,3,4,5,6,7,8,9,10,11,12)，
     *获取全部表头(1,2,3,4,5,6,7,8,9,10,11,12
     */
    public void queryAllLessons(List<Lesson> list, List<String> head) throws SQLException {
        String sql = "select * from Grade";
        DBUtils db = new DBUtils();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            for (int i = 1; i < 17; i++) {
                if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12)
                    head.add(rs.getMetaData().getColumnName(i));
            }
            while (rs.next()) {
                Lesson Lesson = new Lesson();
                Lesson.setNo(rs.getShort(1));
                Lesson.setTerm(rs.getString(2));
                Lesson.setCollege(rs.getString(3));
                Lesson.setId(rs.getString(4));
                Lesson.setName(rs.getString(5));
                Lesson.setCourseNature(rs.getString(6));
                Lesson.setCourseSort(rs.getString(7));
                Lesson.setCredit(rs.getFloat(8));
                Lesson.setIsTestCourse(rs.getString(9));
                Lesson.setIsAve(rs.getString(10));
                Lesson.setRelearnMark(rs.getString(11));
                Lesson.setFinalGrade(rs.getInt(12));

                list.add(Lesson);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        rs.close();
        ps.close();
        conn.close();
        System.out.println("success");
    }
}