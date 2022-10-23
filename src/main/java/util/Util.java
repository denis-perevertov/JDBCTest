package util;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/avada_lesson";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "PrO100hacker";
    public Logger logger = LogManager.getLogger();;

    public Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            logger.info("Connection is OK with " + this.getClass().getName());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

}
