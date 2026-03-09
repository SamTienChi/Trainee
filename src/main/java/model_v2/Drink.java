package model_v2;

public class Drink extends MenuItem{
    private String size;


    public Drink(int id, String name, double price, int quantity, String size){
        super(id, name, price, quantity);
        if(size == null || size.isEmpty()){
            throw new IllegalArgumentException("Size cannot be empty");
        }

        this.size = size;
    }

    @Override
    public double calculateTax() {
        return getSubTotal() * 0.05;
    }

    @Override
    public String toTxt(){
        return super.toTxt() + " | size=" + size;
    }
}
