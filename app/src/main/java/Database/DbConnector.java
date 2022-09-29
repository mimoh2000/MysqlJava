package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DbConnector {

    public static Connection getConnection() {

        //Read the application's properties file
        File propertiesFile = new File(Objects.requireNonNull(DbConnector.class.getClassLoader()
                .getResource("application.properties")).getFile());

        Connection conn = null;
        try (FileInputStream file = new FileInputStream(propertiesFile)) {
            Properties props = new Properties();
            props.load(file);

            String driver = props.getProperty("jdbc.driver");
            String url = props.getProperty("jdbc.url");
            String userName = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }
}
