package lk.icet.pos.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.icet.pos.db.Database;
import lk.icet.pos.entity.Order;
import lk.icet.pos.entity.OrderDetails;
import lk.icet.pos.view.tm.OrderDetailsTM;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

public class OrderDetailsFormController {

    public TextField txtCost;
    public TextField txtId;
    public TextField txtName;
    public TextField txtDate;
    public TableView<OrderDetailsTM> tblOrderDetail;
    public TableColumn<OrderDetailsTM, String> colItem;
    public TableColumn<OrderDetailsTM, String> colDescription;
    public TableColumn<OrderDetailsTM, Integer> colQty;
    public TableColumn<OrderDetailsTM, Double> colUnitPrice;

    public void initialize(){
        colItem.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));


    }


    public void setOrder(String orderId){
        Optional<Order> order = Database.orders.stream().filter(e -> e.getOrderId().equals(orderId)).findFirst();
        if (!order.isPresent()){
            new Alert(Alert.AlertType.WARNING,"Not Found!").show();
            return;
        }
        txtId.setText(order.get().getOrderId());
        txtName.setText(Database.customers.stream()
                .filter(e->e.getId().equals(order.get().getCustomer()))
                .findFirst().get().getName());
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(order.get().getDate()));
        txtCost.setText(String.valueOf(order.get().getTotal()));
        loadTable(orderId);
    }

    private void loadTable(String orderId) {
        //get the order
        //extract items
        //load table-===> item code description
        Optional<Order> selectOrder = Database.orders.stream().filter(e -> e.getOrderId().equals(orderId)).findFirst();
        if (!selectOrder.isPresent()){
            new Alert(Alert.AlertType.WARNING,"Not Found").show();
            return;
        }
        ArrayList<OrderDetails> items = null;
        ObservableList<OrderDetailsTM> tmList = FXCollections.observableArrayList();
        for (OrderDetails d: items
             ) {
            tmList.add(
                    new OrderDetailsTM(
                            d.getCode(),
                            Database.items.stream().filter(e->e.getCode().equals(d.getCode())).findFirst().get().getDescription(),
                            d.getQty(),
                            d.getUnitPrice())
            );
        }
        tblOrderDetail.setItems(tmList);
    }


}
