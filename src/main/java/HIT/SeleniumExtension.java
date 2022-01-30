package HIT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;

public class SeleniumExtension {
    public static boolean isElementExisted(WebDriver webDriver, By by) {//判断网页上是否存在该元素
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            System.out.println("不存在此元素"+by.toString());
            return false;
        }
    }

    public void getEachBody(WebDriver webDriver, Connection conn, Integer page) throws SQLException{
        List<WebElement> trList=new LinkedList<>(webDriver.findElements(By.xpath("/html/body/div[1]/div/div[4]/table/tbody/tr")));//表体的xpath，一个tr是一行
        trList.remove(0);
        PreparedStatement pst;
        System.out.println(page);
        int cur=0;//cur表示当前页面下第几个课程
        for(WebElement row:trList){
            List<WebElement> cols= row.findElements(By.tagName("td"));
            Vector<Object> line=new Vector<>();
            cur++;
            System.out.println(cur);
            //准备好sql语句
            String sql = "INSERT INTO Grade(序号,学年学期,开课院系,课程代码,课程名称,课程性质,课程类别,学分,是否考试课,参与学分绩,补考重修标记,总成绩,最终成绩,成绩备注,学生成绩类别,成绩提交时间) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            for(WebElement ele:cols) {
                line.add(ele.getText());
                switch (line.size()){
                    case 1:pst.setInt(1,page*20+cur);
                    case 2:pst.setString(2,ele.getText());break;
                    case 3:pst.setString(3,ele.getText());break;
                    case 4:pst.setString(4,ele.getText());break;//课程代码
                    case 5:pst.setString(5,ele.getText());break;//课程名称
                    case 6:pst.setString(6,ele.getText());break;//课程性质
                    case 7:pst.setString(7,ele.getText());break;//课程类别
                    case 8:pst.setFloat(8, Float.parseFloat(ele.getText()));break;//学分
                    case 9:pst.setString(9,ele.getText());break;
                    case 10:pst.setString(10,ele.getText());break;
                    case 11:pst.setString(11,ele.getText());break;
                    case 12:pst.setInt(12, Integer.parseInt(ele.getText()));break;
                    case 13:pst.setInt(13, Integer.parseInt(ele.getText()));break;
                    case 14:pst.setString(14,ele.getText());break;
                    case 15:pst.setString(15,ele.getText());break;
                    case 16:pst.setString(16,ele.getText());break;
                }
            }
            synchronized (this) {
                pst.executeUpdate();
                pst.close();
            }
            System.out.println("success");
        }
    }
}
