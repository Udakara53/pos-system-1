package lk.icet.pos.entity;

public class OrderDetails {
    private String code;

    private String orderId;
    private double unitPrice;
    private int qty;

    public OrderDetails(String code, String orderId, double unitPrice,int qty) {
        this.code = code;
        this.orderId = orderId;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }
    public OrderDetails() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
