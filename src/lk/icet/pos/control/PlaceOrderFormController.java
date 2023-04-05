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
import lk.icet.pos.bo.custom.ItemBo;
import lk.icet.pos.bo.custom.OrderBo;
import lk.icet.pos.db.Database;
import lk.icet.pos.dto.CustomerDto;
import lk.icet.pos.dto.ItemDto;
import lk.icet.pos.dto.OrderDetailsDto;
import lk.icet.pos.dto.OrderDto;
import lk.icet.pos.entity.Customer;
import lk.icet.pos.entity.Item;
import lk.icet.pos.entity.Order;
import lk.icet.pos.entity.OrderDetails;
import lk.icet.pos.enums.BOType;
import lk.icet.pos.view.tm.CartTM;

import java.io.IOException;
import java.sql.SQLException;
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

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BOType.CUSTOMER);

    private ItemBo itemBo = BoFactory.getInstance().getBo(BOType.ITEM);

    private OrderBo orderBo = BoFactory.getInstance().getBo(BOType.ORDER);

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
        try {
            lblOrderId.setText(
                    orderBo.generateOrderId()
            );
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemData(String code) {
        try {
            ItemDto item = itemBo.findItem(code);
            if (item != null) {
                txtDescription.setText(item.getDescription());
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Not Found").show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void loadItemCodes() {
        try{
            ObservableList<String> obList = FXCollections.observableArrayList(
                    itemBo.loadItemCodes()
            );
            cmbItemCode.setItems(obList);
        }catch(SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private void setCustomerData(String id){
        try {
            CustomerDto customer = customerBo.findCustomer(id);
            if (customer != null) {
                txtCustomerName.setText(customer.getName());
                txtCustomerAddress.setText(customer.getAddress());
                txtSalary.setText(String.valueOf(customer.getSalary()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Not Found").show();
            }
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
    private void loadCustomerIds() {
       try{
           ObservableList<String> obList = FXCollections.observableArrayList(
                   customerBo.loadCustomerIds()
           );
           cmbCustomerId.setItems(obList);
       }catch(SQLException|ClassNotFoundException e){
           e.printStackTrace();
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
         ArrayList<OrderDetailsDto> products = new ArrayList<>();
            for (CartTM tm:tmList){
                products.add(new OrderDetailsDto(tm.getCode(),lblOrderId.getText(),tm.getUnitPrice(),tm.getQty()) );
                manageQty(tm.getCode(),tm.getQty());
            }
            OrderDto orderDto = new OrderDto(lblOrderId.getText(), (String) cmbCustomerId.getValue(),new Date(),
                    Double.parseDouble(lblTotal.getText()));
            try{
                boolean isSaved = orderBo.saveOrder(orderDto, products);
                if (isSaved){
                    new Alert(Alert.AlertType.INFORMATION,"Order Completed!").show();
                    tmList.clear();
                    tblCart.refresh();
                    lblTotal.setText(String.valueOf(0));
                    loadOrderIds();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Try Again").show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
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
