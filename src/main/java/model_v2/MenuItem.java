package model_v2;

public abstract class MenuItem {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public MenuItem(int id, String name, double price, int quantity){
        if(price < 0){
            throw new IllegalArgumentException("Price must be positive");
        }

        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be > 0");
        }

        this.id = id;
        this.name= name;
        this.price = price;
        this.quantity = quantity;
    }

    public abstract double calculateTax();

    public double getSubTotal(){
        return quantity * price;
    }

    public double getTotal(){
        return getSubTotal() + calculateTax();
    }

    public String toTxt(){
        return id + " | " + name + " | quantity = " + quantity + " | total = " + getTotal();
    }

}
