package model_v2;

import java.util.List;

public class Order {
    private List<MenuItem> items;

    public Order(List<MenuItem> items){
        if(items == null || items.isEmpty()){
            throw new IllegalArgumentException("Order is Empty");
        }
        this.items = items;
    }

    public List<MenuItem> getItems(){
        return items;
    }

    public double calculateTotal(){
        double total = 0;
        for(MenuItem item: items){
            total += item.getTotal();
        }
        return total;
    }
}
