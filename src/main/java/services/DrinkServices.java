package services;
import com.fasterxml.jackson.core.type.TypeReference;

import model.Drink;
import utils.jsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class DrinkServices {
    private final File file = new File("Menu.json");
    MenuServices menu = new MenuServices();

    //List item drink
    public List<Drink> getListDrink() throws IOException {
        List<Drink> drinks = jsonUtils.mapper.readValue(file, new TypeReference<List<Drink>>() {});
        return drinks.stream()
                .filter(item -> item.getType() == "drink")
                .toList();
    }

    //Update item drink
    public void updateItem(int id, Drink newData)throws IOException {
        List<Drink> drinks = getListDrink();
        Optional<Drink> drinkOpt = drinks.stream()
                .filter(drink -> drink.getId() == id)
                .findFirst();

        if(drinkOpt.isPresent()){
            Drink drink = drinkOpt.get();
            drink.setName(newData.getName());
            drink.setSize(newData.getSize());
            drink.setType(newData.getType());
            drink.setPrice(newData.getPrice());
            jsonUtils.mapper.writeValue(file,drink);
            System.out.println("Updated food success for: " + newData.getName());
        }else{
            System.out.println("Cannot found this item" + newData.getName());
        }
    }
}
