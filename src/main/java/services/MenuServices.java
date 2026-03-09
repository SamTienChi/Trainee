package services;


import com.fasterxml.jackson.core.type.TypeReference;

import model.Food;
import model.Drink;
import model.Menu;
import utils.jsonUtils;
import validation.FileValidator;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Optional;


public class MenuServices {
    private final File file = new File("data/Menu.json");

    //List item
    public List<Menu> getAllItem(String name) throws IOException {
        File fileName = new File("data/" + name);
        if(!fileName.exists()){
            System.out.println("No file");
            return new ArrayList<>();
        }
        List<Menu> items = jsonUtils.mapper.readValue(fileName, new TypeReference<List<Menu>>() {});
        FileValidator validator = new FileValidator();
        List<String> errors = validator.validateMenu(items);
        if(!errors.isEmpty()){
            System.out.println("JSON Validation Errors:");
            errors.forEach(System.out::println);
            throw new RuntimeException("Invalid JSON data");
        }
        return items;
    }

    //Add item
    public void addItem(Menu item) throws IOException {
        List<Menu> items = getAllItem("Menu.json");
        int newId = items.stream()
                .mapToInt(Menu::getId)
                .max()
                .orElse(0) + 1;
        item.setId(newId);

        // write into file json
        items.add(item);
        jsonUtils.mapper.writeValue(file, items);
        System.out.println("Added: " + item.getName());
    }

    //Delete item
    public void deleteItem(int id) throws IOException {
        List<Menu> items = getAllItem("Menu.json");
        boolean deletedId = items.removeIf(item -> item.getId() == id);
        if(deletedId){
            jsonUtils.mapper.writeValue(file, items);
            System.out.println("Deleted food success");
        }else{
            System.out.println("Cannot delete");
        }
    }

    //Update
    public void updateItem(int id, Menu newData) throws IOException {
        List<Menu> items = getAllItem("Menu.json");
        Optional<Menu> itemOpt = items.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
        if(itemOpt.isPresent()){
            Menu myMenu = itemOpt.get();
            myMenu.setName(newData.getName());
            myMenu.setPrice(newData.getPrice());
            System.out.println("Updated food success for: " + newData.getName());
        }else{
            System.out.println("Updated fail");
        }
    }

    //Find item by name
    public Optional<Integer> findIdByName(String name) throws IOException {
        List<Menu> items = getAllItem("Menu.json");
        return items.stream()
                .filter(item -> name.equalsIgnoreCase(item.getName()))
                .map(Menu::getId)
                .findFirst();
    }

    //Display items
    public void displayItem(List<Menu> items){
        if(items.isEmpty()){
            System.out.println("Empty Menu");
            return;
        }

        List<Menu> foods = items.stream()
                .filter(i -> i instanceof Food)
                .toList();

        List<Menu> drinks = items.stream()
                .filter(i -> i instanceof Drink)
                .toList();

        System.out.println("===== FOOD MENU =====");

        for(Menu item : foods){
            Food food = (Food) item;

            System.out.println(
                    food.getName() + " - $" +
                    food.getPrice() +
                    " - " + food.getCalories() + " cal");
        }

        System.out.println("\n===== DRINK MENU =====");

        for(Menu item : drinks){
            Drink drink = (Drink) item;

            System.out.println(
                    drink.getName() + " - $" +
                    drink.getPrice() +
                    " - Size " + drink.getSize());
        }
    }
}
