package prepst;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Сергей on 05.06.2017.
 */
public class Main {
    private static final String URL="jdbc:mysql://localhost:3306/mydb_test?autoReconnect=true&useSSL=false";
    private static final String LOGIN="root";
    private static final String PASSWORD="root";
    private static final String INSERT_NEW="INSERT INTO users VALUES(?,?,?)";
    private static final String GET_ALL="SELECT * from users";
    private static final String DELETE="DELETE from users WHERE id=?";

    public static void main(String[] args)
    {
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try {
            Driver driver= new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(URL,LOGIN,PASSWORD);

            //INSERT
            /* preparedStatement=connection.prepareStatement(INSERT_NEW);
            preparedStatement.setInt(1,3);
            preparedStatement.setString(2,"Maestro");
            preparedStatement.setString(3,"mate");

            preparedStatement.execute();*/

            //SELECT
            preparedStatement=connection.prepareStatement(GET_ALL);
            ResultSet res =preparedStatement.executeQuery();

            while (res.next())
            {
                int id=res.getInt("id");
                String username=res.getString("username");
                String password=res.getString("password");

                System.out.println("{Id: "+id+", username: "+username+", password: "+password+"}");
            }

            //DELETE
            /*preparedStatement=connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,3);
            preparedStatement.executeUpdate();*/


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
