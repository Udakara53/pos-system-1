package lk.icet.pos.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.icet.pos.db.Database;
import lk.icet.pos.entity.Order;

import java.text.SimpleDateFormat;
import java.util.Optional;

public class OrderDetailsFormController {

    public TextField txtCost;
    public TextField txtId;
    public TextField txtName;
    public TextField txtDate;
    public TableView<?> tblOrderDetail;
    public TableColumn<?, ?> colItem;
    public TableColumn<?, ?> colDescription;
    public TableColumn<?, ?> colQty;
    public TableColumn<?, ?> colUnitPrice;


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
    }


}
