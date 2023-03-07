package lk.icet.pos.control;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.icet.pos.db.Database;
import lk.icet.pos.entity.User;

public class LoginFormController {
    public AnchorPane loginFormContext;
    public TextField txtUserName;
    public PasswordField pwd;

    public void initialize(){
        Database.users.get(1);//to check password is encrypted.
    }

    public void loginOnAction(ActionEvent actionEvent) {

    }
}
