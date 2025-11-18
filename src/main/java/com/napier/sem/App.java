package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/* Simple application to connect to a MySQL database. */
public class App {
    /* Connection to MySQL database. */
    private Connection con = null;

    /* Connect to the MySQL database. */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location + "/employees?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example"
                );
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /* Disconnect from the MySQL database. */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Disconnected from database");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /* Retrieve an employee record by ID */
    public Employee getEmployee(int ID) {
        try {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT emp_no, first_name, last_name "
                            + "FROM employees "
                            + "WHERE emp_no = " + ID;
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                return emp;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    /* Safely display an employee’s information */
    public void displayEmployee(Employee emp) {
        if (emp == null) {
            System.out.println("No employee record found");
            return;
        }

        String empNo = String.valueOf(emp.emp_no);
        String firstName = (emp.first_name == null) ? "N/A" : emp.first_name;
        String lastName = (emp.last_name == null) ? "N/A" : emp.last_name;
        String title = (emp.title == null) ? "N/A" : emp.title;
        String salary = (emp.salary == 0) ? "N/A" : String.valueOf(emp.salary);
        String deptName = (emp.dept_name == null) ? "N/A" : emp.dept_name;
        String manager = (emp.manager == null) ? "N/A" : emp.manager;

        System.out.println(String.format("%-10s %-15s %-20s %-10s %-10s %-15s %-10s",
                "Emp No", "First Name", "Last Name", "Title", "Salary", "Department", "Manager"));
        System.out.println(String.format("%-10s %-15s %-20s %-10s %-10s %-15s %-10s",
                empNo, firstName, lastName, title, salary, deptName, manager));
    }

    /* Print salary report for a list of employees */
    public void printSalaries(ArrayList<Employee> employees) {
        if (employees == null) {
            System.out.println("No employees");
            return;
        }
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
        for (Employee emp : employees) {
            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name, emp.salary);
            System.out.println(emp_string);
        }
    }

    /* Print salaries for a specific department */
    public void printDepartmentSalaries(ArrayList<Employee> employees, String dept_name) {
        if (employees == null) {
            System.out.println("No employees available for department: " + dept_name);
            return;
        }

        System.out.println("Salaries for Department: " + dept_name);
        System.out.println(String.format("%-10s %-15s %-20s %-10s %-8s",
                "Emp No", "First Name", "Last Name", "Title", "Salary"));

        for (Employee emp : employees) {
            if (emp == null)
                continue;
            if (emp.dept_name == null || !emp.dept_name.equals(dept_name))
                continue;

            String emp_string =
                    String.format("%-10s %-15s %-20s %-10s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name, emp.title, emp.salary);
            System.out.println(emp_string);
        }

    }
    public Department getDepartment(String name) {
        // Example: returns a Department object based on name
        if (name.equals("Development")) {
            return new Department("d001", "Development");
        } else if (name.equals("Sales")) {
            return new Department("d002", "Sales");
        }
        // Add more departments as needed
        return null;
    }

    public ArrayList<Employee> getSalariesByDepartment(Department dept) {
        ArrayList<Employee> employees = new ArrayList<>();

        // Example: add a dummy employee for testing
        Employee emp = new Employee();
        emp.emp_no = 255530;
        emp.first_name = "Ronghao";
        emp.last_name = "Garigliano";
        emp.title = "Developer";
        emp.salary = 50000;
        emp.dept_name = dept.getDept_name();
        emp.manager = "John Doe";

        employees.add(emp);

        return employees;
    }

    public static void main(String[] args) {
        // Create new Application and connect to database
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        Department dept = a.getDepartment("Development");
        ArrayList<Employee> employees = a.getSalariesByDepartment(dept);


        // Print salary report
        a.printSalaries(employees);

        // Disconnect from database
        a.disconnect();
    }

}
