 import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
class Employee implements Serializable {
    int id;
    String name;
    float salary;
    long contact_no;
    String email_id;

    public Employee(int id, String name, float salary, long contact_no, String email_id) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.contact_no = contact_no;
        this.email_id = email_id;
    }

    public String toString() {
        return "\nEmployee Details :" + "\nID: " + this.id + "\nName: " + this.name + "\nSalary: " +
                this.salary + "\nContact No: " + this.contact_no + "\nEmail-id: " + this.email_id;
    }
}

public class EmployeeManagementGUI {
    private ArrayList<Employee> employees;
    private JFrame frame;
    private JTextField idField;
    private JTextField nameField;
    private JTextField salaryField;
    private JTextField contactField;
    private JTextField emailField;
    private JTextArea displayArea;
    private JLabel Limg1;
    Icon img1;

    private final String FILE_PATH = "C:/Users/suloc/OneDrive/Desktop/java proj/EmployeeDataList1.txt";
ṇ
    public EmployeeManagementGUI() {
        employees = new ArrayList<>();

        frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);
        JLabel salaryLabel = new JLabel("Salary:");
        salaryField = new JTextField(10);
        JLabel contactLabel = new JLabel("Contact No:");
        contactField = new JTextField(10);
        JLabel emailLabel = new JLabel("Email ID:");
        emailField = new JTextField(10);


        
        

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(salaryLabel);
        inputPanel.add(salaryField);
        inputPanel.add(contactLabel);
        inputPanel.add(contactField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        JButton searchButton = new JButton("Search Employee");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchEmployee();
            }
        });

        JButton displayButton = new JButton("Display Employees");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayEmployees();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);

        img1=new ImageIcon("1.png");
        Limg1= new JLabel(img1);
        Limg1.setBounds(10,0,1000,359);
        
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        
        frame.add(Limg1);
        
        loadEmployees();
    }

    private void addEmployee() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        float salary = Float.parseFloat(salaryField.getText());
        long contact_no = Long.parseLong(contactField.getText());
        String email_id = emailField.getText();
        employees.add(new Employee(id, name, salary, contact_no, email_id));

        
        clearInputFields();

        
        saveEmployees();
    }

    private void deleteEmployee() {
        int id = Integer.parseInt(idField.getText());
        Employee foundEmployee = null;

        for (Employee employee : employees) {
            if (employee.id == id) {
                foundEmployee = employee;
                break;
            }
        }

        if (foundEmployee != null) {
            employees.remove(foundEmployee);
            displayArea.append("\nEmployee with ID " + id + " has been deleted.\n");
        } else {
            displayArea.append("\nEmployee with ID " + id + " does not exist.\n");
        }

        
        clearInputFields();

        
        saveEmployees();
    }

    private void searchEmployee() {
        int id = Integer.parseInt(idField.getText());
        Employee foundEmployee = null;

        for (Employee employee : employees) {
            if (employee.id == id) {
                foundEmployee = employee;
                break;
            }
        }

        if (foundEmployee != null) {
            displayArea.setText(foundEmployee.toString());
        } else {
            displayArea.setText("Employee with ID " + id + " does not exist.\n");
        }

        
        clearInputFields();
    }

    private void displayEmployees() {
        displayArea.setText("");

        if (employees.isEmpty()) {
            displayArea.append("No employees to display.\n");
        } else {
            displayArea.append("--------------Employee List---------------\n");
            displayArea.append(String.format("%-10s%-15s%-10s%-20s%-10s\n", "ID", "Name", "Salary", "Contact-No", "Email-Id"));

            for (Employee e : employees) {
                displayArea.append(String.format("%-5s%-20s%-10s%-15s%-10s\n", e.id, e.name, e.salary, e.contact_no, e.email_id));
            }
        }

        
        clearInputFields();
    }

    private void clearInputFields() {
        idField.setText("");
        nameField.setText("");
        salaryField.setText("");
        contactField.setText("");
        emailField.setText("");
    }

    private void loadEmployees() {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employees = (ArrayList<Employee>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
           
        }
    }

    private void saveEmployees() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employees);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
           
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmployeeManagementGUI();
            }
        });
    }
}