package prepst;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);



        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try {
            Driver driver= new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(URL,LOGIN,PASSWORD);

            System.out.println("Set 'ins' to insert, 'del' to delete, 'show' to show all, 'exit' to finish");
            boolean f=true;
            while (f)
            {
                System.out.println("Set command: ");
                String command=sc.next();


                if(command.equals("ins"))
                {
                    //INSERT
                    System.out.print("Set ID:");
                    int sid = sc.nextInt();

                    System.out.print("Set un:");
                    String unn = sc.next();

                    System.out.print("Set password:");
                    String pw = sc.next();
                    preparedStatement=connection.prepareStatement(INSERT_NEW);


                    preparedStatement.setInt(1,sid);
                    preparedStatement.setString(2,unn);
                    preparedStatement.setString(3,pw);

                    preparedStatement.executeUpdate();


                }
                if(command.equals("del"))
                {
                    //delete
                    System.out.print("Set ID to delete:");
                    int sid = sc.nextInt();
                    preparedStatement=connection.prepareStatement(DELETE);
                    preparedStatement.setInt(1,sid);
                    preparedStatement.executeUpdate();


                }
                if(command.equals("show"))
                {
                    preparedStatement=connection.prepareStatement(GET_ALL);
                    ResultSet res =preparedStatement.executeQuery();


                    while (res.next())
                    {
                        int id=res.getInt("id");
                        String username=res.getString("username");
                        String password=res.getString("password");

                        System.out.println("{Id: "+id+", username: "+username+", password: "+password+"}");
                    }

                }
                if(command.equals("exit"))
                {
                    System.out.println("bye");
                    f=false;

                }
            }
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
