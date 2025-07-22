package main;

import model.*;
import service.*;
import util.InputUtil;

import java.util.Map;

public class MainApp {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();
        AdminService adminService = new AdminService();
        OrderService orderService = new OrderService();

        while (true) {
            System.out.println("\n1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = InputUtil.scanner.nextInt();

            switch (choice) {
                case 1:
                    adminMenu(productService, adminService, orderService, customerService);
                    break;
                case 2:
                    customerMenu(productService, customerService, orderService);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void adminMenu(ProductService productService, AdminService adminService, OrderService orderService, CustomerService customerService) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Create Admin");
            System.out.println("5. View Admins");
            System.out.println("6. Update Order Status");
            System.out.println("7. View Orders");
            System.out.println("8. Return");
            System.out.print("Choose an option: ");
            int choice = InputUtil.scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int pid = InputUtil.scanner.nextInt();
                    System.out.print("Enter Product Name: ");
                    String pname = InputUtil.scanner.next();
                    System.out.print("Enter Product Price: ");
                    double price = InputUtil.scanner.nextDouble();
                    System.out.print("Enter Stock Quantity: ");
                    int stock = InputUtil.scanner.nextInt();
                    productService.addProduct(new Product(pid, pname, price, stock));
                    System.out.println("Product added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Product ID to remove: ");
                    int removeId = InputUtil.scanner.nextInt();
                    productService.removeProduct(removeId);
                    System.out.println("Product removed.");
                    break;
                case 3:
                    System.out.println("Products:");
                    for (Product p : productService.getAllProducts()) {
                        System.out.println(p);
                    }
                    break;
                case 4:
                    System.out.print("Enter Admin ID: ");
                    int aid = InputUtil.scanner.nextInt();
                    System.out.print("Enter Username: ");
                    String aname = InputUtil.scanner.next();
                    System.out.print("Enter Email: ");
                    String aemail = InputUtil.scanner.next();
                    adminService.addAdmin(new Admin(aid, aname, aemail));
                    System.out.println("Admin created successfully!");
                    break;
                case 5:
                    System.out.println("Admins:");
                    for (Admin a : adminService.getAllAdmins()) {
                        System.out.println(a);
                    }
                    break;
                case 6:
                    System.out.print("Enter Order ID: ");
                    int oid = InputUtil.scanner.nextInt();
                    System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
                    String status = InputUtil.scanner.next();
                    orderService.updateOrderStatus(oid, status);
                    System.out.println("Order status updated.");
                    break;
                case 7:
                    for (Order o : orderService.getAllOrders()) {
                        System.out.println(o);
                    }
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void customerMenu(ProductService productService, CustomerService customerService, OrderService orderService) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Create Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return");
            System.out.print("Choose an option: ");
            int choice = InputUtil.scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int uid = InputUtil.scanner.nextInt();
                    System.out.print("Enter Username: ");
                    String uname = InputUtil.scanner.next();
                    System.out.print("Enter Email: ");
                    String uemail = InputUtil.scanner.next();
                    System.out.print("Enter Address: ");
                    String address = InputUtil.scanner.next();
                    customerService.addCustomer(new Customer(uid, uname, uemail, address));
                    System.out.println("Customer created successfully!");
                    break;
                case 2:
                    for (Customer c : customerService.getAllCustomers()) {
                        System.out.println(c);
                    }
                    break;
                case 3:
                    System.out.print("Enter Customer ID: ");
                    int cid = InputUtil.scanner.nextInt();
                    Customer customer = customerService.getCustomerById(cid);
                    if (customer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }

                    while (true) {
                        System.out.print("Enter Product ID to add to order (or -1 to complete): ");
                        int prodId = InputUtil.scanner.nextInt();
                        if (prodId == -1) break;

                        Product prod = productService.getProductById(prodId);
                        if (prod == null) {
                            System.out.println("Product not found.");
                            continue;
                        }

                        System.out.print("Enter quantity: ");
                        int qty = InputUtil.scanner.nextInt();
                        customer.getShoppingCart().addProduct(prod, qty);
                    }

                    Order order = orderService.placeOrder(customer);
                    System.out.println("Order placed successfully!");
                    break;
                case 4:
                    System.out.print("Enter Customer ID: ");
                    int custId = InputUtil.scanner.nextInt();
                    Customer cust = customerService.getCustomerById(custId);
                    if (cust != null) {
                        for (Order o : orderService.getOrdersByCustomer(cust)) {
                            System.out.println(o);
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 5:
                    for (Product p : productService.getAllProducts()) {
                        System.out.println(p);
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
