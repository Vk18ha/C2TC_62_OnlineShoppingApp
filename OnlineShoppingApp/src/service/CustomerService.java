package service;

import model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }

    public Customer getCustomerById(int id) {
        for (Customer c : customerList) {
            if (c.getUserId() == id) {
                return c;
            }
        }
        return null;
    }
}
