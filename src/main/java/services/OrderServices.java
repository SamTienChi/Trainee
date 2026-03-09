package services;

import model.Order;
import model.Menu;
import model.Food;
import model.Drink;

import validation.InputValidator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;

public class OrderServices {
    InputValidator inputUtil = new InputValidator();

    // Find items.
    public Menu findItem(List<Menu> menu, String name){
        for(Menu items: menu){
            if(items.getName().equalsIgnoreCase(name)){
                System.out.println("Item was found");
                return items;
            }
        }
        System.out.println("Item Not found in findITem");
        return null;
    }

    // Add order
    public void addOrder(List<Menu> menu, List<Order> order, Scanner sc){
        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        Menu item = findItem(menu, name);
        if(item == null){
            System.out.println("Item not found");
            sc.nextLine();
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = inputUtil.getPositiveInt(sc, "Enter quantity: ");

        // Check item exist in order!
        for(Order o : order){
            if(o.getItem().getName().equalsIgnoreCase(name)){
                o.setQuantity(o.getQuantity() + quantity);
                System.out.println("Quantity updated!");
                return;
            }
        }

        order.add(new Order(item, quantity));
        System.out.println("Added to order!");
    }

    //Remove Order
    public void removeOrder(List<Order> order, Scanner sc){
        System.out.print("Enter item name to remove: ");
        String name = sc.nextLine();

        Iterator<Order> it = order.iterator();

        while(it.hasNext()){
            Order oi = it.next();

            if(oi.getItem().getName().equalsIgnoreCase(name)){
                it.remove();
                System.out.println("Item removed!");
                return;
            }
        }

        System.out.println("Item not found in order.");
    }

    //Display order
    public void displayOrder(List<Order> order){

        if(order.isEmpty()){
            System.out.println("Order is empty");
            return;
        }

        double total = 0;

        System.out.println("===== YOUR ORDER =====");

        for(Order oi : order){

            Menu item = oi.getItem();

            double sub = oi.getTotalPrice();

            System.out.printf("%-15s x%-3d $%-8.0f%n",
                    item.getName(),
                    oi.getQuantity(),
                    sub);

            total += sub;
        }

        System.out.println("-----------------------");
        System.out.println("Total: $" + total);
    }

    //Export order
    public void exportOrder(List<Order> orders) {
        double total = 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter("order.txt"))) {

            writer.println("------ ORDER BILL ------");
            writer.println();

            for (Order o : orders) {

                Menu item = o.getItem();
                int quantity = o.getQuantity();

                double price = item.getPrice();
                double tax = item.calculateTax();
                double subTotal = (price + tax) * quantity;

                if(item instanceof Food){
                    Food f = (Food) item;
                    writer.println("Food: " + item.getName());
                    writer.println("Price: " + price);
                    writer.println("Quantity: " + quantity);
                    writer.println("Tax: " + tax);
                    writer.println("Subtotal: " + subTotal);
                    writer.println("Calories" + f.getCalories());
                    writer.println("----------------------");
                }

                else if(item instanceof Drink){
                    Drink d = (Drink) item;

                    writer.println("Drink: " + d.getName());
                    writer.println("Price: " + price);
                    writer.println("Quantity: " + quantity);
                    writer.println("Size: " + d.getSize());
                    writer.println("Tax: " + tax);
                    writer.println("Subtotal: " + subTotal);
                    writer.println("----------------------");
                }

                total += subTotal;
            }

            writer.println("TOTAL BILL: " + total);

            System.out.println("Order exported successfully!");

        } catch (IOException e) {
            System.out.println("Error writing file!");
        }
    }
}
