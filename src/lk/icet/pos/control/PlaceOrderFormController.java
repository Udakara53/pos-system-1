package lk.icet.pos.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.icet.pos.db.Database;
import lk.icet.pos.entity.Customer;
import lk.icet.pos.entity.Item;
import lk.icet.pos.view.tm.CartTM;

public class PlaceOrderFormController {
    public ComboBox cmbCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtSalary;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public ComboBox<String> cmbItemCode;
    public TextField txtRequestQty;
    public TableView tblCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colOption;

    public void initialize(){
        loadCustomerIds();
        loadItemCodes();
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setCustomerData((String) newValue);
            }
        });
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setItemData((String) newValue);
            }
        });

    }

    private void setItemData(String code) {
        Item item = Database.items.stream().filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
        if (item!=null){
            txtDescription.setText(item.getDescription());
            txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        }else{
            new Alert(Alert.AlertType.WARNING,"Not Found").show();
        }
    }

    private void loadItemCodes() {
        for (Item data:Database.items){
            cmbItemCode.getItems().add(data.getCode());
        }
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

    public void addToCartOnAction(ActionEvent actionEvent) {
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtRequestQty.getText());
        double total = unitPrice*qty;

        Button btn = new Button();
        CartTM tm = new CartTM(cmbItemCode.getValue(),txtDescription.getText(),unitPrice,qty,total,btn);

        ObservableList<CartTM> tmList = FXCollections.observableArrayList();
        tmList.add(tm);
        tblCart.setItems(tmList);
    }
}
