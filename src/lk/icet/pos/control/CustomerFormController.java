package lk.icet.pos.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.icet.pos.db.Database;
import lk.icet.pos.entity.Customer;
import lk.icet.pos.view.tm.CustomerTM;

import java.io.IOException;

public class CustomerFormController {
    public AnchorPane customerFormContext;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public TableView<CustomerTM> tbl;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }


    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) customerFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }

    public void saveCustomer(ActionEvent actionEvent) {
        Customer c1 = new Customer(
                txtId.getText(),txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText())
        );
        Database.customers.add(c1);
        new Alert(Alert.AlertType.INFORMATION,"Customer Saved!").show();
        loadAll("");
        clearData();
    }

    private void clearData() {
        txtAddress.clear();
        txtId.clear();
        txtName.clear();
        txtSalary.clear();
    }
    private void loadAll(String searchText){
        ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
        Button btn =new Button("Delete");
        for (Customer c: Database.customers){
            CustomerTM tm = new CustomerTM(c.getId(), c.getName(), c.getAddress(), c.getSalary(), btn);
            tmList.add(tm);
        }
        tbl.setItems(tmList);

    }
}
