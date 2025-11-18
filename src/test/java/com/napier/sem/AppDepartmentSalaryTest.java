package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AppDepartmentSalaryTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printDepartmentSalariesTestNullList()
    {
        app.printDepartmentSalaries(null, "Engineering");
    }

    @Test
    void printDepartmentSalariesTestEmptyList()
    {
        ArrayList<Employee> employees = new ArrayList<>();
        app.printDepartmentSalaries(employees, "Engineering");
    }

    @Test
    void printDepartmentSalariesTestContainsNull()
    {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(null);
        app.printDepartmentSalaries(employees, "Engineering");
    }

    @Test
    void printDepartmentSalariesTestDifferentDept()
    {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "John";
        emp.last_name = "Smith";
        emp.title = "HR Officer";
        emp.salary = 40000;
        emp.dept_name = "HR";
        employees.add(emp);
        app.printDepartmentSalaries(employees, "Engineering");
    }

    @Test
    void printDepartmentSalariesTestValid()
    {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee emp = new Employee();
        emp.emp_no = 2;
        emp.first_name = "Jane";
        emp.last_name = "Doe";
        emp.title = "Software Engineer";
        emp.salary = 70000;
        emp.dept_name = "Engineering";
        employees.add(emp);
        app.printDepartmentSalaries(employees, "Engineering");
    }
}
