package lk.icet.pos.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.icet.pos.db.Database;
import lk.icet.pos.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.xml.crypto.Data;
import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginFormContext;
    public TextField txtUserName;
    public PasswordField pwd;

    public void initialize() {
        //Database.users.get(1);//to check password is encrypted.
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        User selectedUser = Database.users.stream().filter(user -> user.getUsername().equals(txtUserName.getText()))
                .findFirst().orElse(null);
        if (selectedUser != null) {
            if (BCrypt.checkpw(pwd.getText(), selectedUser.getPassword())) {
                //System.out.println("user logged");
               Stage stage = (Stage) loginFormContext.getScene().getWindow();
               stage.setScene(
                       new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml")))
               );
               stage.centerOnScreen();
            } else {
                System.out.println("wrong password");
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "User not found!");
        }
    }

    public void loginEnterAction(ActionEvent actionEvent) throws IOException {
        loginOnAction(actionEvent);
    }
}
