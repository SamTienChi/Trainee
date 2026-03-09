package model;

public class Drink extends Menu{
    private String size;

    public void setSize(String size){ this.size = size ;}
    public String getSize(){ return size; }

    @Override
    public double calculateTax(){ return getPrice() * 0.2;}
}
