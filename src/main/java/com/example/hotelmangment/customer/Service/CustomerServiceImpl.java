package com.example.hotelmangment.customer.Service;

import com.example.hotelmangment.User.Model.UserInfo;
import com.example.hotelmangment.User.Model.UserRepository;
import com.example.hotelmangment.User.Model.UserRole;
import com.example.hotelmangment.customer.CustomerException.*;
import com.example.hotelmangment.customer.Model.Customer;
import com.example.hotelmangment.customer.Model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    private Customer findByID(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }


    @Override
    public void register(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        if (customerRepository.findByPhoneNumber(customer.getPhoneNumber()).isPresent()) {
            throw new PhoneNumberAlreadyExistsException();
        }

        ValidatePassword(customer);

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(customer.getEmail());
        userInfo.setPassword(passwordEncoder.encode(customer.getPassword()));
        userInfo.setRole(new UserRole("ROLE_CUSTOMER"));

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        userRepository.save(userInfo);
        customerRepository.save(customer);
    }

    private void ValidatePassword(Customer customer) {
        if (customer.getPassword().length() < 8)
            throw new PasswordValidation("Password must at least 8 characters");

        if (!customer.getPassword().matches(".*\\d.*"))
            throw new PasswordValidation("Password must contain at least one digit");

        if (!customer.getPassword().matches(".*[@#$%^&+=!].*"))
            throw new PasswordValidation("Password must contain at least one special character (@#$%^&+=!)");

        if (!customer.getPassword().matches(".*[a-zA-Z].*"))
            throw new PasswordValidation("Password must contain at least one letter");
    }

    @Override
    public Customer ViewUserDetails(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public void changePassword(Long id, String currentPassword, String newPassword) {
        Customer currentCustomer = findByID(id);
        if (!passwordEncoder.encode(currentCustomer.getPassword()).equals(currentPassword)) {
            throw new InvalidCredentialsException();
        }

        ValidatePassword(currentCustomer);

        currentCustomer.setPassword(passwordEncoder.encode(newPassword));

        UserInfo userInfo = userRepository.findByUsername(currentCustomer.getEmail());
        userInfo.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userInfo);

        customerRepository.save(currentCustomer);
    }

    @Override
    public void updateName(Long id, String firstName, String lastName) {
        Customer currentCustomer = findByID(id);
        currentCustomer.setFirstName(firstName);
        currentCustomer.setLastName(lastName);
        customerRepository.save(currentCustomer);

    }

    @Override
    public void changeEmail(Long id, String newEmail) {
        Customer currentCustomer = findByID(id);
        if (customerRepository.findByEmail(newEmail).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        currentCustomer.setEmail(newEmail);
        UserInfo userInfo = userRepository.findByUsername(currentCustomer.getEmail());
        userInfo.setUsername(currentCustomer.getEmail());
        userRepository.save(userInfo);

        customerRepository.save(currentCustomer);
    }

    @Override
    public void changePhone(Long id, String newPhone) {
        Customer currentCustomer = findByID(id);
        if (customerRepository.findByPhoneNumber(newPhone).isPresent()) {
            throw new PhoneNumberAlreadyExistsException();
        }

        currentCustomer.setPhoneNumber(newPhone);
        customerRepository.save(currentCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return !customerRepository.findAll().isEmpty() ? customerRepository.findAll() : new ArrayList<>();
    }

    @Override
    public List<Customer> searchByName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(CustomerNotFoundException::new);
    }
}
