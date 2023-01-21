package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField signUpFirstName;

    @FXML
    private JFXTextField singUpLastName;

    @FXML
    private JFXTextField signUpUsername;

    @FXML
    private JFXCheckBox singUpCheckBoxMale;

    @FXML
    private JFXCheckBox singUpCheckBoxFemale;

    @FXML
    private JFXPasswordField signUpPassword;

    @FXML
    private JFXTextField signUpLocation;

    @FXML
    private JFXButton signUpButton;

    @FXML
    void initialize() {


        signUpButton.setOnAction(event -> {

            createUser();


            signUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/login.fxml"));


            try {
                loader.load();


            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();



        });


    }

    private void createUser() {

        DatabaseHandler databaseHandler = new DatabaseHandler();


        String name = signUpFirstName.getText();
        String lastName = singUpLastName.getText();
        String userName = signUpUsername.getText();
        String password = signUpPassword.getText();
        String location = signUpLocation.getText();


        String gender;
        if (singUpCheckBoxFemale.isSelected()) {
            gender = "Female";
        } else gender = "Male";

        User user = new User(name, lastName, userName, password, location, gender);


        databaseHandler.signUpUser(user);


    }


}
