/*
Bilal Ahmad
March 23,2020
Purpose: Main form that loads the GUI
 */
package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("TravelExperts Agents");
        primaryStage.setScene(new Scene(root, 246, 364));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
