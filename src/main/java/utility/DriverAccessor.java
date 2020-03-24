package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverAccessor {
    // mysql5.7
    //private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    // mysql8.0
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    // 自分のユーザ名
    private final static String USER_NAME = "root";
    //自分のmysqlのパスワード
    private final static String PASSWORD = "root";

    public Connection createConnection() {
        String DRIVER_URL = System.getenv("DB_PATH");
        if(DRIVER_URL != null){
            System.out.println("リモート");
            DRIVER_URL = "jdbc:mysql://localhost:3399/sample_gradle?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";
        }else{
            DRIVER_URL = "jdbc:mysql://localhost:3306/sample_gradle?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";
            System.out.println("ローカル");
        }
        try {
            Class.forName(DRIVER_NAME);
            Connection con = DriverManager.getConnection(DRIVER_URL, USER_NAME, PASSWORD);
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Can't Find JDBC Driver.\n");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
