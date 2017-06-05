package dbe;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Сергей on 05.06.2017.
 */
public class Main {


    public static void main(String[] args)
    {
      DBConnector worker = new DBConnector();

      String query="SELECT * from users";

        try {
            Statement statement = worker.getConnection().createStatement();
            ResultSet resultset = statement.executeQuery(query);

            while (resultset.next())
            {
                User user = new User();
                user.setId(resultset.getInt("id"));
                user.setUsername(resultset.getString("username"));
                user.setPassword(resultset.getString("password"));
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
