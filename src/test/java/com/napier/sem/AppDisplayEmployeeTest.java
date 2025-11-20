package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppDisplayEmployeeTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void displayEmployeeTestNull()
    {
        app.displayEmployee(null);
    }

    @Test
    void displayEmployeeTestMissingFields()
    {
        Employee emp = new Employee();
        emp.emp_no = 1;
        app.displayEmployee(emp);
    }

    @Test
    void displayEmployeeTestValid()
    {
        Employee emp = new Employee();
        emp.emp_no = 2;
        emp.first_name = "Alice";
        emp.last_name = "Brown";
        emp.title = "Engineer";
        emp.salary = 65000;
        emp.dept_name = "Engineering";
        emp.manager = "Dr. Smith";
        app.displayEmployee(emp);
    }
}
