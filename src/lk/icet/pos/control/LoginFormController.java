package lk.icet.pos.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.icet.pos.bo.BoFactory;
import lk.icet.pos.bo.custom.UserBo;
import lk.icet.pos.dto.UserDto;
import lk.icet.pos.enums.BOType;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane loginFormContext;
    public TextField txtUserName;
    public PasswordField pwd;

    private UserBo bo = BoFactory.getInstance().getBo(BOType.USER);

    public void initialize() {
        //Database.users.get(1);//to check password is encrypted.
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        try {
            UserDto selectedUser =bo.findUser(txtUserName.getText());
            if (selectedUser != null) {
                if (BCrypt.checkpw(pwd.getText(), selectedUser.getPassword())) {
                    //System.out.println("user logged");
                    Stage stage = (Stage) loginFormContext.getScene().getWindow();
                    stage.setScene(
                            new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml")))
                    );
                    stage.centerOnScreen();
                } else {
                    new Alert(Alert.AlertType.WARNING,"wrong password").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "User not found!").show();
            }
        }catch(SQLException|ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void loginEnterAction(ActionEvent actionEvent) throws IOException {
        loginOnAction(actionEvent);
    }
}
