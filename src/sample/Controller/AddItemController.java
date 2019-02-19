package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label mainLabel;

    @FXML
    private Button addButton;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/addItemForm.fxml"));
            addButton.getScene().getWindow().hide();

            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        showLabel();
    }

    void showLabel(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        int number = databaseHandler.getTasksNumber(LoginController.loggedID);

        if(number == 0){
            mainLabel.setText("Nothing to do today!");
        }else{
            mainLabel.setText(""+number+" task(s) to do today!");
        }
    }
}
