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
import lk.icet.pos.bo.BoFactory;
import lk.icet.pos.bo.custom.CustomerBo;
import lk.icet.pos.dto.CustomerDto;
import lk.icet.pos.enums.BOType;
import lk.icet.pos.view.tm.CustomerTM;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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
    public Button btn;

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BOType.CUSTOMER);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData(newValue);
            }
        });
        loadAll("");
    }

    private void setData(CustomerTM newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
        btn.setText("Update Customer");
    }


    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) customerFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }

    public void saveCustomer(ActionEvent actionEvent) {
        CustomerDto c1 = new CustomerDto(
                txtId.getText(),txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText())
        );
        if (btn.getText().equals("Save Customer")){
            try {
                if (customerBo.saveCustomer(c1)){
                    new Alert(Alert.AlertType.INFORMATION,"Customer Saved!").show();
                    loadAll("");
                }else{
                    new Alert(Alert.AlertType.WARNING,"Something went wrong").show();
                }
            } catch (ClassNotFoundException |SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                if (customerBo.updateCustomer(c1)){
                    new Alert(Alert.AlertType.INFORMATION,"Customer Updated!").show();
                    loadAll("");
                }else{
                    new Alert(Alert.AlertType.WARNING,"Something went wrong").show();
                }
            }catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
                new Alert(Alert.AlertType.WARNING,"Something went wrong").show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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
        try {
            for (CustomerDto c : customerBo.findAllCustomers()) {
                Button btn = new Button("Delete");
                CustomerTM tm = new CustomerTM(c.getId(), c.getName(), c.getAddress(), c.getSalary(), btn);

                btn.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> type = alert.showAndWait();
                    if (type.get() == ButtonType.YES) {
                        try{
                            if(customerBo.deleteCustomer(c.getId())){
                                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                                loadAll("");
                            }else{
                                new Alert(Alert.AlertType.WARNING,"Something went wrong").show();
                            }
                        }catch(SQLException | ClassNotFoundException ex){
                            ex.printStackTrace();
                            new Alert(Alert.AlertType.WARNING,ex.getMessage()).show();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                tmList.add(tm);
            }
            tbl.setItems(tmList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void newCustomerOnAction(ActionEvent actionEvent) {
        clearData();
        btn.setText("Save Customer");
    }
}
