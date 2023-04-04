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
import lk.icet.pos.entity.Item;
import lk.icet.pos.entity.Order;
import lk.icet.pos.entity.OrderDetails;
import lk.icet.pos.view.tm.CartTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

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
    public Label lblTotal;
    public AnchorPane context;
    public Label lblOrderId;

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomerIds();
        loadItemCodes();
        loadOrderIds();
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

    private void loadOrderIds() {
        if (Database.orders.size()>0){
            Order order = Database.orders.get(Database.orders.size() - 1);
            String selectedOrderId= order.getOrderId();
            String splitedId = selectedOrderId.split("[A-Z]")[1];
            int i = Integer.parseInt(splitedId);
            i++;
            lblOrderId.setText(" D"+i);
        }else{
            lblOrderId.setText(" D1");
        }

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
    ObservableList<CartTM> tmList = FXCollections.observableArrayList();
    public void addToCartOnAction(ActionEvent actionEvent) {

        if(isStockExists()){
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int qty = Integer.parseInt(txtRequestQty.getText());
            double total = unitPrice*qty;


            if(isExists(cmbItemCode.getValue())){
                for (CartTM t:tmList
                ) {
                    if (t.getCode().equals(cmbItemCode.getValue())){
                        t.setQty(t.getQty()+qty);
                        t.setTotal(t.getTotal()+total);
                        manageQty(t.getCode(),qty);
                        tblCart.refresh();
                    }
                }
            }else{
                Button btn = new Button("Delete");
                CartTM tm = new CartTM(cmbItemCode.getValue(),txtDescription.getText(),unitPrice,qty,total,btn);
                btn.setOnAction(e->{
                    returnItemToStock(tm.getCode(),tm.getQty());
                    tmList.remove(tm);

                    calculateTotal();

                    tblCart.refresh();
                });
                tmList.add(tm);
                manageQty(tm.getCode(),tm.getQty());
            }
            clear();
            tblCart.setItems(tmList);
            calculateTotal();
        }else{
            new Alert(Alert.AlertType.WARNING,String.format("Sorry %s %s is out of stock!",cmbItemCode.getValue(),txtDescription.getText())).show();
        }
    }

    private void returnItemToStock(String code, int qty) {
        for (Item i:Database.items){
            if (i.getCode().equals(code)){
                i.setQtyOnHand(i.getQtyOnHand()+qty);
                return;
            }
        }
    }


    private boolean isStockExists() {
        return Double.parseDouble(txtQtyOnHand.getText())>=Double.parseDouble(txtRequestQty.getText());
    }

    private void calculateTotal() {
        double total=0;
        for (CartTM tm:tmList){
            total+=tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    private boolean isExists(String code){
        Optional<CartTM> selectedCartTm = tmList.stream().filter(e -> e.getCode().equals(code)).findFirst();
        return selectedCartTm.isPresent();
    }
    private void clear(){
        cmbItemCode.setValue(null);
        txtDescription.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtRequestQty.clear();
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
    }

    public void saveOrder(ActionEvent actionEvent) {
         ArrayList<OrderDetails> products = new ArrayList<>();
            for (CartTM tm:tmList){
                products.add(new OrderDetails(tm.getCode(),"",tm.getUnitPrice(),tm.getQty()));
                //manageQty(tm.getCode(),tm.getQty());
            }
            Order order = new Order(lblOrderId.getText(), (String) cmbCustomerId.getValue(),new Date(),
                    Double.parseDouble(lblTotal.getText()));
            Database.orders.add(order);
            new Alert(Alert.AlertType.INFORMATION,"Order Completed!").show();
            tmList.clear();
            tblCart.refresh();
            lblTotal.setText(String.valueOf(0));
            loadOrderIds();
    }

    private void manageQty(String code,int qty) {
        for (Item i:Database.items){
            if (i.getCode().equals(code)){
                i.setQtyOnHand(i.getQtyOnHand()-qty);
                return;
            }
        }
    }
}
