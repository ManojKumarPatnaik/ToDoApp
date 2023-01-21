package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class UpdateTaskController {

    @FXML
    private JFXTextField updateTaskField;

    @FXML
    private JFXTextField updateDescriptionField;

    @FXML
    public JFXButton updateTaskButton;


    @FXML
    void initialize() {



    }

    public void setTaskField(String task) {
        this.updateTaskField.setText(task);
    }

    public String getTask() {
        return this.updateTaskField.getText().trim();
    }

    public void setUpdateDescriptionField(String description) {
        this.updateDescriptionField.setText(description);
    }

    public String getDescription() {
        return this.updateDescriptionField.getText().trim();
    }

//    public void refreshList() throws SQLException {
//
//        System.out.println("Calling refresh list");
//
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/sample/view/list.fxml"));
//
//        try {
//            loader.load();
//
//            ListController listController = loader.getController();
//            listController.refreshList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
