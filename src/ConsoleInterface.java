import java.util.Scanner;

public class ConsoleInterface {
    private Scanner scanner;
    private Admin admin;

    private Visitor visitor;

    public ConsoleInterface() {
        this.scanner = new Scanner(System.in);
        this.admin = new Admin("admin", "afasfasf");
    }
    private int readInt(String prompt, int min, int max) {
        int input = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    isValid = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
            }
            scanner.nextLine(); // Очистка буфера сканера
        }
        return input;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to the Restaurant Management System!");
            System.out.println("1. Admin");
            System.out.println("2. Visitor");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = readInt("", 1, 3);
            switch (choice) {
                case 1:
                    adminInterface();
                    break;
                case 2:
                    visitorInterface();
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void adminInterface() {
        boolean back = false;
        while (!back) {
            System.out.println("\nAdmin Dashboard");
            System.out.println("1. Add Dish to Menu");
            System.out.println("2. Remove Dish from Menu");
            System.out.println("3. Show Menu");
            System.out.println("4. Back");
            System.out.print("Select an option: ");
            int adminChoice = readInt("Select an option: ", 1, 4);

            switch (adminChoice) {
                case 1:
                    admin.addDishToMenu(scanner);
                    break;
                case 2:
                    admin.removeDishFromMenu(scanner);
                    break;
                case 3:
                    admin.showMenu();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    private void visitorInterface() {
        Visitor visitor = new Visitor();
        boolean back = false;
        while (!back) {
            System.out.println("\nVisitor Dashboard");
            System.out.println("1. View Menu");
            System.out.println("2. Add Dish to Order");
            System.out.println("3. Pay for or Cancel Order");
            System.out.println("4. Back");
            System.out.print("Select an option: ");
            int visitorChoice = readInt("", 1, 4);
            switch (visitorChoice) {
                case 1:
                    Menu.getInstance().showMenu();
                    break;
                case 2:
                    addDishToOrder(visitor);
                    break;
                case 3:
                    visitor.payForOrCancelOrder();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void addDishToOrder(Visitor visitor) {
        System.out.println("Enter the name of the dish you want to add: ");
        String dishName = scanner.nextLine();
        System.out.println("Enter the quantity: ");
        int quantity = readInt("", 1, 4);
        scanner.nextLine(); // Очистка буфера сканера после чтения числа

        // Предполагается, что Menu имеет метод для поиска блюда по имени.
        Dish dish = Menu.getInstance().getDishByName(dishName);
        if (dish != null) {
            visitor.addDishToOrder(dish, quantity);
            System.out.println("Dish added to your order.");
            visitor.CancedOrAdd();
        } else {
            System.out.println("Dish not found.");
        }
    }
}

