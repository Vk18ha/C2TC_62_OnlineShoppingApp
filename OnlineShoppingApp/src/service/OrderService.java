package service;

import model.Customer;
import model.Order;
import model.Product;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orderList = new ArrayList<>();

    public Order placeOrder(Customer customer) {
        Order order = new Order(customer);
        customer.getShoppingCart().getItems().forEach((product, quantity) -> {
            if (product.getStockQuantity() >= quantity) {
                order.addProduct(product, quantity);
                product.setStockQuantity(product.getStockQuantity() - quantity);
            }
        });
        customer.addOrder(order);
        orderList.add(order);
        customer.getShoppingCart().clearCart();
        return order;
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        for (Order o : orderList) {
            if (o.getOrderId() == orderId) {
                o.setStatus(newStatus);
                break;
            }
        }
    }

    public List<Order> getAllOrders() {
        return orderList;
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        return customer.getOrders();
    }
}
