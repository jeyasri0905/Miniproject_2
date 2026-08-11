import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Functional Interface
@FunctionalInterface
interface EmployeeFilter {
    boolean check(Employee employee);
}

// Interface with default and static methods
interface EmployeeOperations {

    // Default method
    default void showCompanyName() {
        System.out.println("Company: ABC Technologies");
    }

    // Static method
    static void showSystemInfo() {
        System.out.println("Employee Management System");
        System.out.println("Java 8+ Features Demo");
    }
}

// Employee Class
class Employee implements EmployeeOperations {

    private int id;
    private String name;
    private String department;
    private double salary;
    private LocalDate joiningDate;

    public Employee(int id, String name, String department,
                    double salary, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    // Display employee details
    public void display() {
        System.out.println(
            "ID: " + id +
            ", Name: " + name +
            ", Department: " + department +
            ", Salary: " + salary +
            ", Joining Date: " + joiningDate
        );
    }
}

// Main Class
public class EmployeeManagementSystem {

    public static void main(String[] args) {

        // Static method
        EmployeeOperations.showSystemInfo();

        System.out.println();

        // Employee list
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(
                101, "Arun", "IT", 50000,
                LocalDate.of(2023, 6, 15)
        ));

        employees.add(new Employee(
                102, "Priya", "HR", 45000,
                LocalDate.of(2022, 8, 20)
        ));

        employees.add(new Employee(
                103, "Rahul", "IT", 60000,
                LocalDate.of(2021, 3, 10)
        ));

        employees.add(new Employee(
                104, "Divya", "Finance", 55000,
                LocalDate.of(2024, 1, 5)
        ));

        employees.add(new Employee(
                105, "Kiran", "IT", 70000,
                LocalDate.of(2020, 11, 25)
        ));

        // Default method
        employees.get(0).showCompanyName();

        System.out.println("\n--- All Employees ---");

        for (Employee e : employees) {
            e.display();
        }

        // ------------------------------------------------
        // 1. LAMBDA EXPRESSION
        // ------------------------------------------------

        System.out.println("\n--- Lambda Expression ---");

        employees.forEach(e ->
                System.out.println(e.getName())
        );

        // ------------------------------------------------
        // 2. FUNCTIONAL INTERFACE
        // ------------------------------------------------

        System.out.println("\n--- Functional Interface ---");

        EmployeeFilter highSalaryEmployee =
                e -> e.getSalary() > 55000;

        for (Employee e : employees) {
            if (highSalaryEmployee.check(e)) {
                e.display();
            }
        }

        // ------------------------------------------------
        // 3. STREAM API
        // ------------------------------------------------

        System.out.println("\n--- Stream API: IT Employees ---");

        employees.stream()
                .filter(e -> e.getDepartment().equals("IT"))
                .forEach(Employee::display);

        // Stream API - Salary greater than 50000
        System.out.println("\n--- Salary Greater Than 50000 ---");

        employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .forEach(Employee::display);

        // Stream API - Sort by salary
        System.out.println("\n--- Employees Sorted By Salary ---");

        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(Employee::display);

        // Stream API - Collect employee names
        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println("\nEmployee Names:");
        System.out.println(names);

        // ------------------------------------------------
        // 4. OPTIONAL CLASS
        // ------------------------------------------------

        System.out.println("\n--- Optional Class ---");

        Optional<Employee> employee =
                employees.stream()
                        .filter(e -> e.getId() == 103)
                        .findFirst();

        employee.ifPresent(e ->
                System.out.println("Employee Found: " + e.getName())
        );

        // Employee not found example
        Optional<Employee> notFound =
                employees.stream()
                        .filter(e -> e.getId() == 999)
                        .findFirst();

        System.out.println(
                "Employee 999 exists: " + notFound.isPresent()
        );

        // ------------------------------------------------
        // 5. NEW DATE AND TIME API
        // ------------------------------------------------

        System.out.println("\n--- New Date & Time API ---");

        // Current date
        LocalDate today = LocalDate.now();

        System.out.println("Today's Date: " + today);

        // Current date and time
        LocalDateTime currentDateTime =
                LocalDateTime.now();

        System.out.println(
                "Current Date & Time: " + currentDateTime
        );

        // Formatting date
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println(
                "Formatted Date: " +
                today.format(formatter)
        );

        // Adding days
        LocalDate futureDate =
                today.plusDays(30);

        System.out.println(
                "Date After 30 Days: " + futureDate
        );

        // ------------------------------------------------
        // 6. AVERAGE SALARY USING STREAM
        // ------------------------------------------------

        System.out.println("\n--- Average Salary ---");

        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        System.out.println(
                "Average Salary: " + averageSalary
        );

        // ------------------------------------------------
        // 7. HIGHEST SALARY
        // ------------------------------------------------

        System.out.println("\n--- Highest Salary Employee ---");

        Optional<Employee> highestSalary =
                employees.stream()
                        .max(Comparator.comparing(
                                Employee::getSalary));

        highestSalary.ifPresent(Employee::display);

        // ------------------------------------------------
        // END
        // ------------------------------------------------

        System.out.println("\n--- Program Completed ---");
    }
}