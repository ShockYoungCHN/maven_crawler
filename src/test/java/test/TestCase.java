package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static test.Selenium_extension.*;

public class TestCase {
    public WebDriver webDriver;

    @BeforeClass
    public void setupEnv(){
        Path p1= Paths.get("src","Drivers","chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",p1.toAbsolutePath().toString());
        //创建浏览器实例
        webDriver=new ChromeDriver();
    }

    @Test
    public void openHIT() throws InterruptedException {
        //webDriver.manage().window().maximize();
        webDriver.get("http://authserver-hitwh-edu-cn.ivpn.hitwh.edu.cn:8118/authserver/login?service=https%3A%2F%2Fivpn.hitwh.edu.cn%2Fauth%2Fcas_validate%3Fentry_id%3D1");
        webDriver.findElement(By.id("username")).sendKeys("");
        webDriver.findElement(By.id("password")).sendKeys("");
        webDriver.findElement(By.cssSelector("[class='auth_login_btn primary full_width']")).click();
        webDriver.findElement(By.id("details-button")).click();
        webDriver.findElement(By.id("proceed-link")).click();
        Thread.sleep(9000);
        //到此为止登陆完成

        webDriver.findElement(By.xpath("//*[@class=\"rs-list__item__href\" and @title=\"新教务系统\"]")).click();
        Thread.sleep(3000);
        //webDriver.findElement(By.xpath("/html/body/div/div/div[1]/a")).click();
        webDriver.get("http://172-26-64-16.ivpn.hitwh.edu.cn:8118/loginCAS");

        String currentHandle = webDriver.getWindowHandle();
        //获得所有的窗口句柄，如果不是currentHandle,则进入
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!currentHandle.equals(windowHandle) ) {
                //选择教务系统预登录窗口并关闭此窗口
                webDriver.switchTo().window(windowHandle);
                webDriver.close();
            }
        }
        webDriver.switchTo().window(currentHandle);
        //目前在教务系统内部
        webDriver.get("http://172-26-64-16.ivpn.hitwh.edu.cn:8118/cjcx/queryCjpub");
        webDriver.findElement(By.className("qmcx")).click();
        /* 获取网页源代码的方法
        webDriver.get("https://baidu.com");
        FileOutputStream fos=new FileOutputStream("src/source.html");
        fos.write(webDriver.getPageSource().getBytes(StandardCharsets.UTF_8));
        */
    }

    @Test
    public JTable getAllTables() throws IOException, InterruptedException {//这里用Jsoup和selenium都可以实现，我选择selenium；此函数的作用是向gui返回一个jtable
        setupEnv();
        openHIT();
        Vector<Vector<Object>> body=new Vector<>();//表格内容

        String s="/html/body/div[1]/div/div[5]/ul/li[";
        int i=3;
        By b=By.xpath(s+i+"]/a");//翻页元素
        Vector<Object> head=new Vector<>();//表头


        for(WebElement we:webDriver.findElements(By.xpath("/html/body/div[1]/div/div[4]/table/tbody/tr[1]/th"))){
            head.add(we.getText());
        }
        //获取全部表格内容
        while(isJudgingElement(webDriver,b)){
            webDriver.findElement(b).click();
            getEachBody(webDriver,body);

            i++;
            String s1=s+i+"]/a";
            b=By.xpath(s1);
        }

        System.out.println("共有"+body.size()+"门课程");
        return new JTable(body,head);
    }

    @AfterClass
    public void tearDownEnv(){
        //webDriver.quit();
    }

}
