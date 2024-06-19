package com.example.hotelmangment.customer.Service;

import com.example.hotelmangment.customer.Model.Customer;

import java.util.List;

public interface CustomerService {

    void register(Customer customer);

    Customer ViewUserDetails(Long id);

    void changePassword(Long id, String currentPassword, String newPassword);

    void updateName(Long id, String firstName, String lastName);

    void changeEmail(Long id, String newEmail);

    void changePhone(Long id, String newPhone);

    List<Customer> getAllCustomers();

    List<Customer> searchByName(String firstName,String lastName);
}
