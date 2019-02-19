package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.Model.Task;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AddItemFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField descriptionText;

    @FXML
    private TextField taskText;


    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            createTask();
            backScene();
        });
    }

    private void createTask() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String taskTitle = taskText.getText();
        String description = descriptionText.getText();


        Task task = new Task(description, taskTitle);
        databaseHandler.addTask(task);

    }

    private void backScene() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/View/addItem.fxml"));
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
    }
}
