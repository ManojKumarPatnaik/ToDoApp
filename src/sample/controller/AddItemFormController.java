package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class AddItemFormController {


    private int userId;

    private DatabaseHandler databaseHandler;


    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private Label successLabel;

    @FXML
    private JFXButton todosButton;


    @FXML
    void initialize() {


        databaseHandler = new DatabaseHandler();
        Task task = new Task();


        saveTaskButton.setOnAction(event -> {

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskText = taskField.getText().trim();
            String taskDescription = descriptionField.getText().trim();

            if (!taskText.equals("") || !taskDescription.equals("")) {

                System.out.println("User Id: " + AddItemController.userId);

                task.setUserId(AddItemController.userId);
                task.setDatecreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskText);

                databaseHandler.insertTask(task);

                successLabel.setVisible(true);

                todosButton.setVisible(true);
                int taskNumber = 0;
                try {
                    taskNumber = databaseHandler.getAllTasks(AddItemController.userId);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
               todosButton.setText("My 2Do's: " + "(" + taskNumber + ")");


                taskField.setText("");
                descriptionField.setText("");

                todosButton.setOnAction(event1 -> {
                    //send users to the list screen

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/view/list.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();


                });

                // System.out.println("Task Added Successfully!");


            } else {
                System.out.println("Nothing added!");

            }

        });


    }

    public int getUserId() {
        System.out.println("from getUserId() " + userId);

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("From setUserId " + this.userId);
    }
}
