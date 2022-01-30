package HIT;

import HIT.UI.App;
import com.google.common.collect.Maps;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.sqlite.SQLiteException;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Crawler {
    public ChromeDriver webDriver;
    public MessageDialog messageDialog=new MessageDialog(App.frame,"提示信息");
    public void setupEnv() {
        Path p1 = Paths.get("src", "Drivers", "chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", p1.toAbsolutePath().toString());
        //创建浏览器实例
        String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36";
        ChromeOptions option = new ChromeOptions();

        option.addArguments("disable-infobars");
        option.addArguments("--headless");
        option.addArguments("ignore-certificate-errors");
        option.addArguments("--window-size=1920,1050");
        option.addArguments("--no-sandbox");
        option.addArguments("--disable-dev-shm-usage");
        option.addArguments("user-agent=" + USER_AGENT);
        //设置开发者模式启动
        option.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        webDriver = new ChromeDriver(option);

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("source", "Object.defineProperty(navigator, 'webdriver', {get: () => undefined }); ");
        webDriver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", map);
    }

    public void VPNLogin() {
        try {
            webDriver.get("http://authserver-hitwh-edu-cn.ivpn.hitwh.edu.cn:8118/authserver/login?service=https%3A%2F%2Fivpn.hitwh.edu.cn%2Fauth%2Fcas_validate%3Fentry_id%3D1");
            webDriver.findElement(By.id("username")).sendKeys(LoginManage.username());
            webDriver.findElement(By.id("password")).sendKeys(LoginManage.password());//LoginManage.password()
            webDriver.findElement(By.cssSelector("[class='auth_login_btn primary full_width']")).click();
        }
        catch (Exception ex){
            ex.printStackTrace();
            messageDialog.dispose();
            JOptionPane.showMessageDialog(null,"VPN服务器出现波动或网址变更，请稍后再试!");

            tearDownEnv();
            Thread.currentThread().stop();
        }
        if (SeleniumExtension.isElementExisted(webDriver, By.id("msg"))) {
            messageDialog.dispose();
            JOptionPane.showMessageDialog(null, "账户或密码错误!");

            tearDownEnv();
            Thread.currentThread().stop();
        }
         else {
            messageDialog.showMessage("登录成功，正在进入教务系统...");
        }
    }

    public void getIntoSys(){
        //目前在教务系统内部
        try {
            webDriver.get("http://172-26-64-16.ivpn.hitwh.edu.cn:8118/loginCAS");
            webDriver.get("http://172-26-64-16.ivpn.hitwh.edu.cn:8118/cjcx/queryCjpub");
            Thread.sleep(2000);
            messageDialog.showMessage("成功进入教务系统，正在获取成绩页面...");
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(null,"未能进入教务系统，请在”设置->关于“中联系开发者!");

            tearDownEnv();
            Thread.currentThread().stop();
        }
    }

    public void getGradePage(){
        try {
            webDriver.findElement(By.className("qmcx")).click();
            messageDialog.showMessage("成功获取成绩页面，正在读取成绩...");
        }
        catch (Exception ex){
            messageDialog.dispose();
            JOptionPane.showMessageDialog(null,"未能获取成绩页面，请在”设置->关于“中联系开发者!");

            tearDownEnv();
            Thread.currentThread().stop();
        }
    }


    public void openHIT() throws InterruptedException {
        VPNLogin();
        Thread.sleep(3000);
        getIntoSys();
        getGradePage();
        /* 获取网页源代码的方法
        webDriver.get("https://baidu.com");
        FileOutputStream fos=new FileOutputStream("src/source.html");
        fos.write(webDriver.getPageSource().getBytes(StandardCharsets.UTF_8));
        */
    }

    public void fillTheDatabase() throws InterruptedException, SQLException, IOException {//这里用Jsoup和selenium都可以实现，我选择selenium；此函数的作用是向gui返回一个jtable
        messageDialog.showMessage("请稍等...");
        setupEnv();
        openHIT();

        String s = "/html/body/div[1]/div/div[5]/ul/li[";
        int i = 3;
        By b = By.xpath(s + i + "]/a");//翻页元素
        /* 表头不再获取,在建表时提前确定，但是我仍然保留源代码供参考
        Vector<Object> head=new Vector<>();//表头
        //获取表头
        for(WebElement we:webDriver.findElements(By.xpath("/html/body/div[1]/div/div[4]/table/tbody/tr[1]/th"))){
            head.add(we.getText());
        }
        head.remove(16);
*/

        //数据库操作
        DBUtils db=new DBUtils();
        Connection conn=null;
        try{
            conn = db.getConnection();

            Statement deleteAll=conn.createStatement();
            deleteAll.executeUpdate("Delete from Grade;");
            deleteAll.close();
            messageDialog.showMessage("正在写入本地数据库...");

        } catch (SQLiteException e){
            messageDialog.dispose();
            JOptionPane.showMessageDialog(null,"本地数据库连接失败，请尝试重新安装此软件，或在”设置->关于“中联系开发者!");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        while(SeleniumExtension.isElementExisted(webDriver,b)){
            webDriver.findElement(b).click();
            SeleniumExtension seleniumExtension=new SeleniumExtension();
            seleniumExtension.getEachBody(webDriver,conn,i-3);//i=3为第一页

            i++;
            String s1=s+i+"]/a";
            b=By.xpath(s1);
        }

        conn.close();
        System.out.println("1111");
        messageDialog.dispose();
        JOptionPane.showMessageDialog(null,"数据读取成功！");
        //System.out.println("Connection Closed");
        tearDownEnv();
    }

    public void tearDownEnv(){
        webDriver.quit();
    }
}
