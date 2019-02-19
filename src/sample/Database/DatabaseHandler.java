package sample.Database;

import sample.Controller.LoginController;
import sample.Model.Task;
import sample.Model.User;

import java.sql.*;

public class DatabaseHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    //write
    public void signUpUser(User user){

        String insert = "INSERT INTO "+Const.USERS_TABLE+" ("+
                Const.USER_FIRSTNAME+","+
                Const.USER_SECONDNAME+","+
                Const.USER_USERNAME+","+
                Const.USER_PASSWORD+","+
                Const.USER_LOCATION+","+
                Const.USER_GENDER+") VALUES(?,?,?,?,?,?)";

        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;
        if(!user.getUserName().equals("") || !user.getPassword().equals("")){
            String query = "SELECT * FROM "+Const.USERS_TABLE+" WHERE "+Const.USER_USERNAME+"=? AND "+Const.USER_PASSWORD+"=?";

            try{
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());

                resultSet = preparedStatement.executeQuery();

            }catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("Print your credentials!");
        }

        return resultSet;
    }

    public void addTask(Task task){
        String insert = "INSERT INTO "+Const.TASKS_TABLE+" ("+
                Const.TASK_USER_ID+", "+
                Const.TASK_TASK+", "+
                Const.TASK_DESCRIPT+") VALUES(?,?,?)";

        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setInt(1, LoginController.loggedID);
            preparedStatement.setString(2, task.getTask());
            preparedStatement.setString(3, task.getDescription());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public int getTasksNumber(int id){
        ResultSet resultSet = null;
        int count = 0;
        String query = "SELECT * FROM "+Const.TASKS_TABLE+" WHERE "+Const.TASK_USER_ID+"=?";

        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                do{
                    count++;
                }while(resultSet.next());

                System.out.println(count);
            }else{
                System.out.println("No tasks!");
            }


        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
