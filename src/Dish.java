public class Dish {
    private String name;
    private double price;
    private int preparationTime;

    public Dish(String name, double price, int preparationTime) {
        this.name = name;
        this.price = price;
        this.preparationTime = preparationTime;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double preparationTime() {
        return preparationTime;
    }

    // Дополнительные геттеры для preparationTime и complexity по необходимости
}
