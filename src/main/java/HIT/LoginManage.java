package HIT;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginManage {
    public static String username() throws IOException {
        FileInputStream fis=new FileInputStream("src/main/resources/info/login.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
        return br.readLine();

    }
    public static String password() throws IOException {
        FileInputStream fis=new FileInputStream("src/main/resources/info/login.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
        br.readLine();
        return br.readLine();
    }
}
