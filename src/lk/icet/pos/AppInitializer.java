package lk.icet.pos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.icet.pos.dao.DataAccessCode;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            new DataAccessCode().saveUser();
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
