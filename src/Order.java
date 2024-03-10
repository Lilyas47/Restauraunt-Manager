import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<Dish, Integer> dishes = new HashMap<>();
    private OrderStatus status;
    private double totalPrice = 0.0;

    public Order() {
        this.status = OrderStatus.PENDING; // Инициализация заказа со статусом "в ожидании"
    }

    public void addDish(Dish dish, int quantity) {
        // Если блюдо уже есть в заказе, увеличиваем его количество
        if (dishes.containsKey(dish)) {
            dishes.put(dish, dishes.get(dish) + quantity);
        } else {
            dishes.put(dish, quantity);
        }
        totalPrice += dish.getPrice() * quantity;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void payForOrder() {
        // Проверяем, готов ли заказ к оплате
        if (status == OrderStatus.READY) {
            status = OrderStatus.PAID;
            System.out.println("Order has been paid successfully. Total price: $" + getTotalPrice());
        } else {
            System.out.println("Order is not ready to be paid. Current status: " + status);
        }

    }

    // Метод для вывода информации о заказе (опционально)
    public void printOrderDetails() {
        System.out.println("Order Details:");
        dishes.forEach((dish, quantity) -> {
            System.out.println(dish.getName() + " x " + quantity + " = $" + (dish.getPrice() * quantity));
        });
        System.out.println("Total Price: $" + getTotalPrice());
        System.out.println("Status: " + getStatus());
    }
}
