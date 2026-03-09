package services;

import model_v2.MenuItem;
import model_v2.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderServices_v2 {
    private JsonReaderServices reader = new JsonReaderServices();
    public Order loadOrder(String jsonPath) throws Exception{
        List<MenuItem> items = reader.read(jsonPath);
        return new Order(items);
    }
}
