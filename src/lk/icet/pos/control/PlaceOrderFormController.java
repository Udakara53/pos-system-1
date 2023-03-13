package lk.icet.pos.control;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.icet.pos.db.Database;
import lk.icet.pos.entity.Customer;

public class PlaceOrderFormController {
    public ComboBox cmbCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtSalary;

    public void initialize(){
        loadCustomerIds();
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setCustomerData((String) newValue);
            }
        });

    }
    private void setCustomerData(String id){
        Customer customer = Database.customers.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        if (customer!=null){
            txtCustomerName.setText(customer.getName());
            txtCustomerAddress.setText(customer.getAddress());
            txtSalary.setText(String.valueOf(customer.getSalary()));
        }else{
            new Alert(Alert.AlertType.WARNING,"Not Found").show();
        }
    }

    private void loadCustomerIds() {
        for (Customer data: Database.customers){
            cmbCustomerId.getItems().add(data.getId());
        }
    }
}
