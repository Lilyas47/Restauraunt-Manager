import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu {
    private static Menu instance;
    private List<Dish> dishes = new ArrayList<>();

    private Menu() {
        dishes.add(new Dish("sBurger", 300, 10));

    }
    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
            // Предполагаемая загрузка меню из файла может быть здесь
            // instance.loadMenuFromFile("path/to/menu.csv");
        }
        return instance;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public boolean removeDish(String dishName) {
        Optional<Dish> dishToRemove = dishes.stream()
                .filter(d -> d.getName().equalsIgnoreCase(dishName))
                .findFirst();
        return dishToRemove.map(dish -> dishes.remove(dish)).orElse(false);
    }

    // Методы для получения списка блюд и т.д.
    public void showMenu() {
        if (dishes.isEmpty()) {
            System.out.println("The menu is currently empty.");
        } else {
            System.out.println("Menu:");
            dishes.forEach(dish -> System.out.println(dish.getName() + " - $" + dish.getPrice()));
        }
    }
    public List<Dish> getDishes() {
        return new ArrayList<>(dishes);
    }

    public Dish getDishByName(String name) {
        for (Dish dish : dishes) {
            if (dish.getName().equalsIgnoreCase(name)) {
                return dish;
            }
        }
        return null;
    }
}
