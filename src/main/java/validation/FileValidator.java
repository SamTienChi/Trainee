package validation;

import model.Menu;
import model.Drink;
import model.Food;

import java.util.List;
import java.util.*;
import java.util.ArrayList;

public class FileValidator {
    public List<String> validateMenu(List<Menu> items) {
        List<String> errors = new ArrayList<>();
        Set<Integer> ids = new HashSet<>();

        for (Menu item : items) {
            if (item == null) {
                errors.add("Item is null");
                continue;
            }

            if (!ids.add(item.getId()))
                errors.add("Duplicate id: " + item.getId());

            if (item.getId() < 0) {
                errors.add("Invalid id at item: " + item.getName());
            }
            if (item.getName() == null) {
                errors.add("Name cannot be empty: " + item.getName());
            }
            if (item.getPrice() < 10) {
                errors.add("Invalid price at item: " + item.getPrice());
            }
            if (item.getType() == null || !(item.getType().equalsIgnoreCase("food") || item.getType().equalsIgnoreCase("drink"))) {
                errors.add("Invalid type at item (only accepts food or drink) : " + item.getType());
            }
            //validate Food
            if (item instanceof Food food) {
                if (food.getCalories() <= 0) {
                    errors.add("Calories must be > 0 (food id=" + food.getId() + ")");
                }
            }

            //validate Drink
            if (item instanceof Drink drink) {
                if (!(drink.getSize().equalsIgnoreCase("S")  || drink.getSize().equalsIgnoreCase("M") || drink.getSize().equalsIgnoreCase("L"))) {
                    errors.add("Size must be : S , M or L (drink id=" + drink.getId() + ")");
                }
            }
        }
        return errors;
    }
}