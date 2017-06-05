package dbe;

import java.sql.*;

/**
 * Created by Сергей on 05.06.2017.
 */
public class DBConnector {
    private static final String URL="jdbc:mysql://localhost:3306/mydb_test?autoReconnect=true&useSSL=false";
    private static final String LOGIN="root";
    private static final String PASSWORD="root";
    private Connection connection;



    public  DBConnector()
    {
        try {
            connection= DriverManager.getConnection(URL,LOGIN,PASSWORD);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
        public Connection getConnection() {
            return connection;
        }
}
