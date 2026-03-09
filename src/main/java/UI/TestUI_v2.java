package UI;
import model_v2.Order;
import services.FileService;
import services.OrderServices_v2;

import java.util.Scanner;

public class TestUI_v2 {
    public void runUI(){
        Scanner scanner = new Scanner(System.in);
        OrderServices_v2 orderServices = new OrderServices_v2();
        FileService fileService = new FileService();

        try{
            System.out.print("Enter Json file path: ");
            String path = "data/" + scanner.nextLine() +".json";
            Order order = orderServices.loadOrder(path);
            fileService.export(order, "order.txt");
            System.out.println("Order exported to order.txt successfully");
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
