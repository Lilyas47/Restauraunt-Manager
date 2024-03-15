import java.util.Objects;
import java.util.Scanner;

public class Visitor {
    private Order currentOrder;
    Scanner scanner = new Scanner(System.in);
    public Visitor() {
        this.currentOrder = new Order(); // Создаём новый заказ при создании посетителя
    }

    // Отображает меню ресторана
    public void viewMenu(Menu menu) {
        menu.showMenu();
    }
    private int safeReadInt(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next(); // Пропустить некорректный ввод
            System.out.println("Некорректный ввод. Пожалуйста, введите число.");
            System.out.println(prompt);
        }
        return scanner.nextInt();
    }
    // Добавляет блюдо к текущему заказу, если он не готов
    public void addDishToOrder(Dish dish, int quantity) {
        if (currentOrder != null && currentOrder.getStatus() != OrderStatus.READY && currentOrder.getStatus() != OrderStatus.PAID) {
            currentOrder.addDish(dish, quantity);
            System.out.println(quantity + " x " + dish.getName() + " added to your order.");
        } else {
            System.out.println("Cannot add dishes to a completed or paid order.");
        }
    }
    public void CancedOrAdd() {
        // Здесь должна быть логика обработки заказа, включая возможное изменение статуса заказа
        while(true) {
            try {
                currentOrder.setStatus(OrderStatus.PENDING);
                System.out.println("Ваш заказ находится в обработке");
                Thread.sleep(5000);
                System.out.println("Не хотите добавить что-то в заказ или отменить его?");
                System.out.println("Если хотите продолжить введите 1");
                System.out.println("Если хотите добавить что-то в заказ введите 2");
                System.out.println("Если хотите отменить заказ введите 3");

                int answer = safeReadInt("");
                if (Objects.equals(answer, 2))
                {
                    System.out.println("Enter the name of the dish you want to add: ");
                    String dishName = scanner.nextLine();
                    System.out.println("Enter the quantity: ");

                    int quantity = safeReadInt("");
                    // Предполагается, что Menu имеет метод для поиска блюда по имени.
                    Dish dish = Menu.getInstance().getDishByName(dishName);
                    if (dish != null) {
                        addDishToOrder(dish, quantity);
                        System.out.println("Dish added to your order.");
                        System.out.println("Your order is cooking...");
                    } else {
                        System.out.println("Dish not found.");
                    }
                }
                else if (answer == 3) {
                    currentOrder.setStatus(OrderStatus.CANCELLED);
                }
                else{
                    System.out.println("Your order is cooking...");
                }
                Thread.sleep(5000);
                currentOrder.setStatus(OrderStatus.IN_PROGRESS);
                System.out.println("Не хотите добавить что-то в заказ или отменить его?");
                System.out.println("Если хотите продолжить введите 1");
                System.out.println("Если хотите добавить что-то в заказ введите 2");
                System.out.println("Если хотите отменить заказ введите 3");

                answer = safeReadInt("");
                if (Objects.equals(answer, 2))
                {
                    System.out.println("Enter the name of the dish you want to add: ");
                    scanner.nextLine();
                    String dishName = scanner.nextLine();
                    System.out.println("Enter the quantity: ");

                    int quantity = safeReadInt("");
                    scanner.nextLine(); // Очистка буфера сканера после чтения числа
                    // Предполагается, что Menu имеет метод для поиска блюда по имени.
                    Dish dish = Menu.getInstance().getDishByName(dishName);
                    if (dish != null) {
                        addDishToOrder(dish, quantity);
                        System.out.println("Dish added to your order.");
                        System.out.println("Your order is cooking...");
                    } else {
                        System.out.println("Dish not found.");
                    }
                }
                else if (answer == 3) {
                    currentOrder.setStatus(OrderStatus.CANCELLED);
                    break;
                }
                else{
                    System.out.println("Your order is cooking...");
                    currentOrder.setStatus(OrderStatus.READY);
                    currentOrder.payForOrder();
                    currentOrder.setStatus(OrderStatus.PAID);
                    break;
                }



            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
    // Оплачивает заказ, если он готов, или отменяет, если он не готов
    public void payForOrCancelOrder() {
        if (currentOrder != null && currentOrder.getStatus() == OrderStatus.READY) {
            currentOrder.payForOrder();
            System.out.println("Your order has been paid for.");
        } else if (currentOrder != null && (currentOrder.getStatus() == OrderStatus.PENDING || currentOrder.getStatus() == OrderStatus.IN_PROGRESS)) {
            currentOrder.setStatus(OrderStatus.CANCELLED);
            System.out.println("Your order has been cancelled.");
        } else {
            System.out.println("No active order to pay for or cancel.");
        }
    }
}
