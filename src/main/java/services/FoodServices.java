package services;

import com.fasterxml.jackson.core.type.TypeReference;

import model.Food;
import utils.jsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class FoodServices {
    private final File file = new File("Menu.json");

    //List item
    public List<Food> getListFood() throws IOException {
        try{
            if(!file.exists()){
                return new ArrayList<>();
            }
            List<Food> items = jsonUtils.mapper.readValue(file, new TypeReference<List<Food>>(){});
            return items.stream()
                    .filter(item -> "food".equalsIgnoreCase(item.getType()))
                    .toList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    //Add food
    public void addItem(Food food) throws IOException {
        try{
            if(!file.exists()){
                System.out.println("File does not exist!");
            }else{
                List<Food> foods = getListFood();

                int newId = foods.stream()
                        .mapToInt(Food::getId)
                        .max()
                        .orElse(0) + 1;
                food.setId(newId);

                foods.add(food);
                jsonUtils.mapper.writeValue(file, foods);
                System.out.println("Added: " + food.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Delete
    public void removeItem(int id) throws IOException {
        try{
            List<Food> foods = getListFood();
            boolean removed = foods.removeIf(food -> food.getId() == id);
            if(removed){
                jsonUtils.mapper.writeValue(file, foods);
                System.out.println("Success Delete food: ");
            }else{
                System.out.println("Food not found: ");
            }
        }catch(Exception e){
            e.printStackTrace();
        };
    }

    //Update
    public void updateItem(int id, Food newData) throws IOException {
        try{
            List<Food> foods = getListFood();
            Optional<Food> foodOpt = foods.stream()
                    .filter(f -> f.getId() == id)
                    .findFirst();

            if(foodOpt.isPresent()){
                Food food = foodOpt.get();
                food.setName(newData.getName());
                food.setPrice(newData.getPrice());
                food.setCalories(newData.getCalories());
                jsonUtils.mapper.writeValue(file,foods);
                System.out.println("Updated food success: " + newData.getName() + "- $Price: " + newData.getPrice() + "- Calories:" + newData.getCalories());
            }else {
                System.out.println("Food not found: " + newData.getName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Find item
    public Optional<Integer> findIdByName(String name) throws IOException {
        List<Food> foods = getListFood();
        return foods.stream()
                .filter(f -> name.equalsIgnoreCase(f.getName()))
                .map(Food::getId)
                .findFirst();
    }

}
