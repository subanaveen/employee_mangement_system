import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

public class EmployeeManagementSystem {
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nEMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    System.out.println("\nThank you for using the Employee Management System!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        } while (choice != 5);
    }

    private static void addEmployee() {
        System.out.print("\nEnter employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter employee designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(id, name, designation, salary);
        employeeList.add(employee);

        System.out.println("\nEmployee added successfully!");
    }

    private static void viewEmployee() {
        if (employeeList.size() == 0) {
            System.out.println("\nThere are no employees to display!");
        } else {
            System.out.println("\nEMPLOYEE LIST:");
            for (Employee employee : employeeList) {
                System.out.println("ID: " + employee.getId());
                System.out.println("Name: " + employee.getName());
                System.out.println("Designation: " + employee.getDesignation());
                System.out.println("Salary: " + employee.getSalary() + "\n");
            }
        }
    }

    private static void updateEmployee() {
        System.out.print("\nEnter the employee id to update: ");
        int id = scanner.nextInt();

        boolean found = false;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                found = true;

                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                employee.setName(name);

                System.out.print("Enter new designation: ");
                String designation = scanner.nextLine();
               
private static void updateEmployee() {
        System.out.print("\nEnter the employee id to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                found = true;

                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                employee.setName(name);

                System.out.print("Enter new designation: ");
                String designation = scanner.nextLine();
                employee.setDesignation(designation);

                System.out.print("Enter new salary: ");
                double salary = scanner.nextDouble();
                employee.setSalary(salary);

                System.out.println("\nEmployee details updated successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("\nEmployee not found!");
        }
    }
