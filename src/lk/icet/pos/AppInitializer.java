package lk.icet.pos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.icet.pos.bo.BoFactory;
import lk.icet.pos.bo.custom.UserBo;
import lk.icet.pos.enums.BOType;

import java.io.IOException;

public class AppInitializer extends Application {

    private UserBo userBo = BoFactory.getInstance().getBo(BOType.USER);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            userBo.initializeUsers();
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
