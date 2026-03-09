package model;
import model.Menu;
import java.util.List;

public class Order {
    private Menu item;
    private double totalPrice;
    private int quantity;

    public Order(Menu item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public Menu getItem(){
        return item;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public double getTotalPrice(){
        double price = item.getPrice();
        double tax = item.calculateTax();
        return (price + tax) * quantity;
    }

}
