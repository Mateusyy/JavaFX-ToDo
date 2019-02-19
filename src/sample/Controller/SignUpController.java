package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Database.DatabaseHandler;
import sample.Model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpButton;

    @FXML
    private CheckBox signUpFemale;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLocation;

    @FXML
    private CheckBox signUpMale;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private TextField signUpSecondName;

    @FXML
    private TextField signUpUsername;

    @FXML
    void initialize(){
        signUpButton.setOnAction(event -> {
            createUser();
        });
    }

    void createUser(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = signUpFirstName.getText();
        String secondName = signUpSecondName.getText();
        String userName = signUpUsername.getText();
        String password = signUpPassword.getText();
        String location = signUpLocation.getText();
        String gender = "";

        if(signUpFemale.isSelected()){
            gender = "Female";
        }else
            gender = "Male";

        User user = new User(firstName, secondName, userName, password, location, gender);

        databaseHandler.signUpUser(user);
    }
}
