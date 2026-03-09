package model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Food.class, name = "food"),
        @JsonSubTypes.Type(value = Drink.class, name = "drink"),
    }
)
public abstract class Menu {
    private int id;
    private String name;
    private double price;
    private String type;

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getType() { return type; }

    public abstract double calculateTax();
}
