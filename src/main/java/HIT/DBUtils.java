package HIT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String db_path = "src/main/resources/info/test.db";
        return DriverManager.getConnection("jdbc:sqlite:" + db_path);
    }
}
