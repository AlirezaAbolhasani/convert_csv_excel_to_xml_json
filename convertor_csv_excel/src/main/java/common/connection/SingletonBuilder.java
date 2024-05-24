package common.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/21/2024
 * @time: 8:10 AM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

public class SingletonBuilder {
    final static Logger logger = Logger.getLogger(SingletonBuilder.class.getName());
    private static String userName = null;
    private static String password = null;
    private static String url = null;
    private static Connection connection = null;
    static final String JDBC_DRIVER_h2 = "org.h2.Driver";
    static final String JDBC_DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    static final String JDBC_DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
    static final String JDBC_DRIVER_SQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private SingletonBuilder(){}

    /**
     *
     * @return connection
     * @throws SQLException
     * This method provide a connection by JPA technology
     */
    public static Connection getSingletonConnection() throws SQLException {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("DB");
            url = resourceBundle.getString("MYSQL_DB_URL");
            userName = resourceBundle.getString("MYSQL_USER");
            password = resourceBundle.getString("MYSQL_PASS");
            if(connection == null)
                connection = DriverManager.getConnection(url, userName, password);

            System.out.println("Connecting to database...");

        } catch (SQLException e) {
            e.printStackTrace();
            logger.warning(e.getMessage());
        }
        return  connection;
    }
}
