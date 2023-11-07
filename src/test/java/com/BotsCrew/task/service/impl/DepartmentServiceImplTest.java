package com.BotsCrew.task.service.impl;

import com.BotsCrew.task.model.Department;
import com.BotsCrew.task.model.Lector;
import com.BotsCrew.task.repository.DepartmentRepository;
import com.BotsCrew.task.repository.LectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private LectorRepository lectorRepository;

    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentService = new DepartmentServiceImpl(departmentRepository, lectorRepository);
    }

    @Test
    void testGetHeadOfDepartment() {

        Department department = new Department();
        Lector head = new Lector();
        head.setFirstName("John");
        head.setLastName("Doe");
        department.setHead(head);
        when(departmentRepository.findByName("Test Department")).thenReturn(department);

        String headOfDepartment = departmentService.getHeadOfDepartment("Test Department");

        assertEquals("JohnDoe", headOfDepartment);
    }

    @Test
    void testGetAssistantCount() {
        Department department = new Department();
        when(departmentRepository.findByName("Test Department")).thenReturn(department);
        when(lectorRepository.countByDepartmentsAndDegree(department, Lector.Degree.ASSISTANT)).thenReturn(5);

        int assistantCount = departmentService.getAssistantCount("Test Department");

        assertEquals(5, assistantCount);
    }

    @Test
    void testGetProfessorCount() {
        Department department = new Department();
        when(departmentRepository.findByName("Test Department")).thenReturn(department);
        when(lectorRepository.countByDepartmentsAndDegree(department, Lector.Degree.PROFESSOR)).thenReturn(3);

        int professorCount = departmentService.getProfessorCount("Test Department");

        assertEquals(3, professorCount);
    }

    @Test
    void testGetAssociateProfessorsCount() {
        Department department = new Department();
        when(departmentRepository.findByName("Test Department")).thenReturn(department);
        when(lectorRepository.countByDepartmentsAndDegree(department, Lector.Degree.ASSOCIATE_PROFESSOR)).thenReturn(7);

        int associateProfessorsCount = departmentService.getAssociateProfessorsCount("Test Department");

        assertEquals(7, associateProfessorsCount);
    }

    @Test
    void testGetAverageSalary() {
        Department department = new Department();
        when(departmentRepository.findByName("Test Department")).thenReturn(department);
        List<Lector> lectors = new ArrayList<>();
        Lector lector1 = new Lector();
        lector1.setSalary(50000.0);
        Lector lector2 = new Lector();
        lector2.setSalary(60000.0);
        lectors.add(lector1);
        lectors.add(lector2);
        when(lectorRepository.findByDepartments(department)).thenReturn(lectors);

        double averageSalary = departmentService.getAverageSalary("Test Department");

        assertEquals(55000.0, averageSalary, 0.01);
    }

    @Test
    void testGetEmployeeCount() {
        Department department = new Department();
        when(departmentRepository.findByName("Test Department")).thenReturn(department);
        when(lectorRepository.countByDepartments(department)).thenReturn(10);

        int employeeCount = departmentService.getEmployeeCount("Test Department");

        assertEquals(10, employeeCount);
    }
}
