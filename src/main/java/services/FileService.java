package services;

import model_v2.MenuItem;
import model_v2.Order;

import java.io.FileWriter;
import java.io.IOException;

public class FileService {
    public void export(Order order, String path) throws IOException{
        try(FileWriter writer = new FileWriter(path, false)){
            for(MenuItem item: order.getItems()){
                writer.write(item.toTxt());
                writer.write("\n");
            }

            writer.write("-------------------\n");
            writer.write("Total = " + order.calculateTotal());
        }
    }
}
