import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends User {
    private Menu menu;

    public Admin(String username, String password) {
        super(username, password, UserType.ADMIN);
        this.menu = Menu.getInstance(); // Получаем экземпляр синглтона Menu
    }

    public void addDishToMenu(Scanner scanner) {
        System.out.println("Adding a new dish to the menu.");
        System.out.print("Enter dish name: ");
        String name = scanner.nextLine();

        // Проверка корректности ввода для цены блюда
        double price = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                System.out.print("Enter dish price: ");
                price = scanner.nextDouble();
                scanner.nextLine(); // Чтение символа новой строки после числа
                validPrice = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid price.");
                scanner.nextLine(); // Очистка буфера сканера
            }
        }

        // Проверка корректности ввода для времени приготовления блюда
        int preparationTime = 0;
        boolean validTime = false;
        while (!validTime) {
            try {
                System.out.print("Enter dish preparation time (minutes): ");
                preparationTime = scanner.nextInt();
                scanner.nextLine(); // Чтение символа новой строки после числа
                validTime = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid preparation time.");
                scanner.nextLine(); // Очистка буфера сканера
            }
        }

        Dish newDish = new Dish(name, price, preparationTime);
        menu.addDish(newDish);
        System.out.println("Dish added successfully.");
    }
    public void removeDishFromMenu(Scanner scanner) {
        System.out.println("Removing a dish from the menu.");
        System.out.print("Enter the name of the dish you want to remove: ");
        String dishName = scanner.nextLine();

        if (menu.removeDish(dishName)) {
            System.out.println("Dish removed successfully.");
        } else {
            System.out.println("Dish not found.");
        }
    }

    public void showMenu() {
        System.out.println("Current Menu:");
        menu.showMenu();
    }

    // Можно добавить методы для изменения блюд в меню и т.д.
}