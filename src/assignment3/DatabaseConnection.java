package assignment3;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER =
            "postgres.axcgckktfembduyzkvrs";

    private static final String PASSWORD = loadPassword();

    private DatabaseConnection() {}

    private static String loadPassword() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            return props.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException("Cannot read config.properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

