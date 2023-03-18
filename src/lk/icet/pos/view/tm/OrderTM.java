package lk.icet.pos.view.tm;

import java.util.Date;

public class OrderTM {
   private String id;
   private String name;
   private double cost;
   private Date date;

    public OrderTM(String id, String name, double cost, Date date) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.date = date;
    }

    public OrderTM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
