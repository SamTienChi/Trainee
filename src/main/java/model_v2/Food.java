package model_v2;

public class Food extends MenuItem{
    private int calories;

    public Food(int id, String name, double price, int quantity, int calories) {
        super(id, name, price, quantity);

        if(calories <= 0){
            throw new IllegalArgumentException("Calories must be > 0");
        }

        this.calories = calories;
    }

    @Override
    public double calculateTax(){
        return getSubTotal() * 0.10;
    };

    @Override
    public String toTxt() {
        return super.toTxt() + " | calories=" + calories;
    }
}
