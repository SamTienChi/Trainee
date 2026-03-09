package utils;

import com.fasterxml.jackson.databind.JsonNode;
import model_v2.*;

public class jsonUtils_v2 {
    public static MenuItem create(JsonNode node){
        String type = node.get("type").asText();

        int id = node.path("id").asInt();
        String name = node.path("name").asText();
        double price = node.path("price").asDouble();
        int quantity = node.path("quantity").asInt();

        switch(type.toLowerCase()){
            case "food":
                int calories = node.path("calories").asInt();
                return new Food(id, name, price, quantity, calories);
            case "drink":
                String size = node.path("size").asText();
                return new Drink(id, name, price, quantity, size);
            default:
                throw new IllegalArgumentException("Unknow type: " + type);
        }
    }
}
