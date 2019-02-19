package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.Model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {

    static public int loggedID;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginSignInButton;

    @FXML
    private TextField loginUsername;

    @FXML
    private Label loginErrorLabel;


    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        loginButton.setOnAction(event -> {
            String usernameText = loginUsername.getText().trim();
            String passwordText = loginPassword.getText().trim();

            User user = new User();
            user.setUserName(usernameText);
            user.setPassword(passwordText);

            ResultSet userRow = databaseHandler.getUser(user);

            int counter = 0;
            try{
                while (userRow.next()){
                    counter++;
                    String name = userRow.getString("firstname");
                    int id = userRow.getInt("iduser");
                    loggedID = id;
                    System.out.println("Welcome "+id+" "+name);
                }

                if(counter == 1){
                    loginErrorLabel.setText("");
                    showAddItem();
                }else{
                    loginErrorLabel.setText("Something is wrong. Try Again!");
                    loginUsername.clear();
                    loginPassword.clear();
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        });

        loginSignInButton.setOnAction(event -> {
            loginSignInButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/signUp.fxml"));

            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void showAddItem(){
        loginSignInButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/View/addItem.fxml"));

        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
