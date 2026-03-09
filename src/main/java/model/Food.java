package model;

public class Food extends Menu {

    private int calories;

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories){
        this.calories = calories;
    }

    @Override
    public double calculateTax(){ return getPrice() * 0.1;};
}
