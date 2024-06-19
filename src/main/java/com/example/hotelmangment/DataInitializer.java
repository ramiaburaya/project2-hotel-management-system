package com.example.hotelmangment;

import com.example.hotelmangment.User.Model.UserInfo;
import com.example.hotelmangment.User.Model.UserRepository;
import com.example.hotelmangment.User.Model.UserRole;
import com.example.hotelmangment.User.Model.UserRoleRepository;
import com.example.hotelmangment.customer.Model.Customer;
import com.example.hotelmangment.customer.Model.CustomerRepository;
import com.example.hotelmangment.employee.Model.Employee;
import com.example.hotelmangment.employee.Model.EmployeeRepository;
import com.example.hotelmangment.housekeeping.Model.Housekeeping;
import com.example.hotelmangment.housekeeping.Model.HousekeepingRepository;
import com.example.hotelmangment.housekeeping.Model.HousekeepingStatus;
import com.example.hotelmangment.room.Model.Room;
import com.example.hotelmangment.room.Model.RoomRepository;
import com.example.hotelmangment.room.Model.RoomStatus;
import com.example.hotelmangment.room.Model.RoomType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Component
public class DataInitializer {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HousekeepingRepository housekeepingRepository;

    private static final String[] FIRST_NAMES = {
            "John", "Jane", "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah"
    };

    private static final String[] LAST_NAMES = {
            "Doe", "Smith", "Johnson", "Brown", "Williams", "Jones", "Garcia", "Miller", "Davis", "Rodriguez"
    };

    private static final String[] ROLES = {
            "Manager", "Receptionist", "Housekeeping", "Chef", "Bellhop", "Concierge", "Maintenance", "Security", "Bartender", "Waiter"
    };

    private static final String[] STREETS = {
            "Main St", "Oak St", "Pine St", "Elm St", "Maple St", "Cedar St", "Spruce St", "Birch St", "Walnut St", "Chestnut St"
    };
    private static final String[] HOUSEKEEPING_TASKS = {
            "Clean room", "Change bed sheets", "Restock minibar", "Clean bathroom"
    };
    private static final RoomStatus[] ROOM_STATUSES = RoomStatus.values();

    @PostConstruct
    public void init() {
        Random random = new Random();

        UserRole customerRole = userRoleRepository.findById(1L).orElse(null);
        if (customerRole == null) {
            customerRole = new UserRole();
            customerRole.setName("ROLE_CUSTOMER");
            userRoleRepository.save(customerRole);
        }

        UserRole adminRole = userRoleRepository.findById(2L).orElse(null);
        if (adminRole == null) {
            adminRole = new UserRole();
            adminRole.setName("ROLE_ADMIN");
            userRoleRepository.save(adminRole);
        }


        if (employeeRepository.count() == 0) {
            IntStream.range(0, 100).forEach(i -> {
                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + i + "@example.com";
                String phoneNumber = "059" + String.format("%07d", i + 1000);
                String address = (random.nextInt(999) + 1) + " " + STREETS[random.nextInt(STREETS.length)] + ", Anytown";
                String role = ROLES[random.nextInt(ROLES.length)];

                Employee employee = new Employee();
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setEmail(email);
                employee.setPhoneNumber(phoneNumber);
                employee.setRole(role);
                employee.setAddress(address);
                employeeRepository.save(employee);
            });
        }

        if (customerRepository.count() == 0) {
            IntStream.range(0, 100).forEach(i -> {
                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + (i + 100) + "@example.com";
                String phoneNumber = "059" + String.format("%07d", i + 2000);
                String password = "pass@12354" + i;

                Customer customer = new Customer();
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setEmail(email);
                customer.setPhoneNumber(phoneNumber);
                customer.setPassword(password);
                customerRepository.save(customer);

                // Create user for each customer
                UserInfo user = new UserInfo();
                user.setUsername(email);
                user.setPassword(passwordEncoder.encode(password));
                user.setRole(Objects.requireNonNull(userRoleRepository.findById(1L).orElse(null)));

                userRepository.save(user);

            });
        }

        if (userRepository.count() == 100) {
            userRepository.save(new UserInfo("ra@gmail.com", passwordEncoder.encode("rami2006!"), Objects.requireNonNull(userRoleRepository.findById(2L).orElse(null))));
        }


        if (roomRepository.count() == 0) {
            AtomicInteger roomNumber = new AtomicInteger(1);
            createRooms(RoomType.SINGLE, 15, random, roomNumber);
            createRooms(RoomType.DOUBLE, 20, random, roomNumber);
            createRooms(RoomType.SUITE, 10, random, roomNumber);
            createRooms(RoomType.DELUXE, 5, random, roomNumber);
        }
        if (housekeepingRepository.count() == 0) {
            List<Room> rooms = roomRepository.findAll();
            createHousekeepingTasksForReservedAndOccupiedRooms(rooms, random);
        }
    }

    private void createRooms(RoomType roomType, int count, Random random, AtomicInteger roomNumber) {

        IntStream.range(0, count).forEach(i -> {
            Room room = new Room();
            room.setRoomNumber(roomNumber.getAndIncrement());
            room.setStatus(ROOM_STATUSES[random.nextInt(ROOM_STATUSES.length)]);
            room.setRoomType(roomType);
            room.setPrice(BigDecimal.valueOf((random.nextInt(5) + 1) * 100));
            room.setCapacity(random.nextInt(4) + 1);
            room.setFacilities("Wi-Fi, TV, Air conditioning");
            room.setFeatures("Ocean view, Balcony");
            System.out.println(room);
            roomRepository.save(room);

        });
    }

    private void createHousekeepingTasksForReservedAndOccupiedRooms(List<Room> rooms, Random random) {
        List<Employee> housekeepingEmployees = employeeRepository.findAll()
                .stream()
                .filter(employee -> "Housekeeping".equals(employee.getRole()))
                .toList();

        rooms.stream()
                .filter(room -> room.getStatus() == RoomStatus.RESERVED || room.getStatus() == RoomStatus.OCCUPIED)
                .limit(20)
                .forEach(room -> {
                    Housekeeping housekeeping = new Housekeeping();
                    housekeeping.setRoom(room);
                    housekeeping.setEmployee(housekeepingEmployees.get(random.nextInt(housekeepingEmployees.size())));
                    housekeeping.setTaskDescription(HOUSEKEEPING_TASKS[random.nextInt(HOUSEKEEPING_TASKS.length)]);
                    housekeeping.setScheduleDate(Date.valueOf(LocalDate.now().plusDays(random.nextInt(10))));
                    housekeeping.setStatus(HousekeepingStatus.SCHEDULED);
                    housekeepingRepository.save(housekeeping);
                });
    }
}
