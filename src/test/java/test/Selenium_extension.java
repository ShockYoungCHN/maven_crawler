package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Selenium_extension {
    public static boolean isJudgingElement(WebDriver webDriver, By by) {//判断网页上是否存在该元素
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            System.out.println("不存在此元素"+by.toString());
            return false;
        }
    }
    public static void getEachBody(WebDriver webDriver,Vector<Vector<Object>> body){
        List<WebElement> trList=new LinkedList<>(webDriver.findElements(By.xpath("/html/body/div[1]/div/div[4]/table/tbody/tr")));//表体的xpath，一个tr是一行
        trList.remove(0);

        //这段可以把trList转换为vector
        for(WebElement row:trList){
            List<WebElement> cols= row.findElements(By.tagName("td"));
            System.out.println(row.getText());//测试是否get到每一行的信息
            Vector<Object> line=new Vector<>();
            for(WebElement ele:cols) {
                line.add(ele.getText());
            }
            body.add(line);
        }
    }
}
