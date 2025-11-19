package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        // Connect to local Docker MySQL container on port 33060
        app.connect("localhost:33060", 30000);

        // Ensure test data exists for testGetEmployee
        Employee existingEmp = app.getEmployee(255530);
        if (existingEmp == null) {
            Employee newEmp = new Employee();
            newEmp.emp_no = 255530;
            newEmp.first_name = "Ronghao";
            newEmp.last_name = "Garigliano";
            app.addEmployee(newEmp);
        }

        // Ensure test data exists for testAddEmployee (remove if already exists)
        Employee testEmp = app.getEmployee(500000);
        if (testEmp != null) {
            app.deleteEmployee(500000); // optional: remove old test data
        }
    }

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee(255530);
        assertNotNull(emp, "Employee 255530 should exist in the database");
        assertEquals(255530, emp.emp_no);
        assertEquals("Ronghao", emp.first_name);
        assertEquals("Garigliano", emp.last_name);
    }

    @Test
    void testAddEmployee()
    {
        Employee emp = new Employee();
        emp.emp_no = 500000;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";

        app.addEmployee(emp);

        Employee fetched = app.getEmployee(500000);
        assertNotNull(fetched, "Employee 500000 should have been added");
        assertEquals(500000, fetched.emp_no);
        assertEquals("Kevin", fetched.first_name);
        assertEquals("Chalmers", fetched.last_name);
    }
}
