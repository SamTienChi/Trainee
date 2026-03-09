package UI;

import model.Menu;
import model.Order;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import services.MenuServices;
import services.OrderServices;

public class TestUI {
    public void runUI() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        MenuServices myMenu = new MenuServices();
        OrderServices myOrder = new OrderServices();
        List<Menu> items = null;
        List<Order> order = new ArrayList<>();

        int n;

        do{
            while(flag){
                System.out.print("Please enter the file name: ");
                String fileName = scanner.next() + ".json";
                try{
                    items = myMenu.getAllItem(fileName);
                    if(!items.isEmpty()){
                        System.out.println("File loaded successfully!");
                        flag = false;
                    }
                } catch(Exception  e){
                    System.out.println("Invalid file or JSON format!");
                    System.out.println("Please try again.\n");
                }
            }
            System.out.println("------Main Menu------");
            System.out.println("0. Check the menu");
            System.out.println("1. Order");
            System.out.println("2. Display order");
            System.out.println("3. Remove order");
            System.out.println("4. Export Order");
            System.out.println("Other. Exit");
            System.out.print("+Choose your type: ");
            n = scanner.nextInt();
            scanner.nextLine();
            switch(n) {
                case 0:
                    myMenu.displayItem(items);
                    break;
                case 1:
                    myOrder.addOrder(items, order, scanner);
                    break;
                case 2:
                    myOrder.displayOrder(order);
                    break;
                case 3:
                    myOrder.removeOrder(order, scanner);
                    break;
                case 4:
                    myOrder.exportOrder(order);
                    break;
            }
        }while(n >= 0 && n < 4);
    }
}
