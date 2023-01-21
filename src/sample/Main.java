package sample;

import com.sun.deploy.util.SessionState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;

import java.awt.*;
import java.sql.ResultSet;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/login.fxml"));
         //Parent root = FXMLLoader.load(getClass().getResource("/sample/view/list.fxml"));
        primaryStage.setTitle("2DO");
        primaryStage.setScene(new Scene(root, 700, 400));

        primaryStage.show();


    }


}
