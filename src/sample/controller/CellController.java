package sample.controller;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class CellController extends JFXListCell<Task> {


    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView iconImageView;

    @FXML
    private Label taskLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private ImageView deleteButton;

    @FXML
    public ImageView listUpdateButton;

    private FXMLLoader fxmlLoader;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {


    }

    @Override
    public void updateItem(Task myTask, boolean empty) {

        databaseHandler = new DatabaseHandler(); //main change

        super.updateItem(myTask, empty);

        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        }else {

             if (fxmlLoader == null ) {
                  fxmlLoader = new FXMLLoader(getClass()
                          .getResource("/sample/view/cell.fxml"));
                  fxmlLoader.setController(this);

                 try {
                     fxmlLoader.load();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }

             taskLabel.setText(myTask.getTask());
             dateLabel.setText(myTask.getDatecreated().toString());
             descriptionLabel.setText(myTask.getDescription());

             int taskId = myTask.getTaskId();


             listUpdateButton.setOnMouseClicked(event -> {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/updateTaskForm.fxml"));


                 try {
                     loader.load();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

                 Parent root = loader.getRoot();
                 Stage stage = new Stage();
                 stage.setScene(new Scene(root));

                 UpdateTaskController updateTaskController = loader.getController();
                 updateTaskController.setTaskField(myTask.getTask());
                 updateTaskController.setUpdateDescriptionField(myTask.getDescription());

                 updateTaskController.updateTaskButton.setOnAction(event1 -> {

                     Calendar calendar = Calendar.getInstance();

                     java.sql.Timestamp timestamp =
                             new java.sql.Timestamp(calendar.getTimeInMillis());

                     try {

                         System.out.println("taskid " + myTask.getTaskId());

                         databaseHandler.updateTask(timestamp, updateTaskController.getDescription(),
                                 updateTaskController.getTask(), myTask.getTaskId());

                         //update our listController
                        // updateTaskController.refreshList();
                     } catch (SQLException e) {
                         e.printStackTrace();
                     } catch (ClassNotFoundException e) {
                         e.printStackTrace();
                     }

                 });

                 stage.show();


             });


             deleteButton.setOnMouseClicked(event -> {

                 try {

                     databaseHandler.deleteTask(AddItemController.userId,taskId);

                 } catch (SQLException e) {
                     e.printStackTrace();
                 } catch (ClassNotFoundException e) {
                     e.printStackTrace();
                 }
                 getListView().getItems().remove(getItem());

             });

             setText(null);
             setGraphic(rootAnchorPane);


        }
    }

}
