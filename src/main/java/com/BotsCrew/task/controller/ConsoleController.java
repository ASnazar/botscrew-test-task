package com.BotsCrew.task.controller;

import java.util.List;
import java.util.Scanner;
import com.BotsCrew.task.model.Lector;
import com.BotsCrew.task.service.DepartmentService;
import com.BotsCrew.task.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Controller
public class ConsoleController {
    private final DepartmentService departmentService;
    private final LectorService lectorService;

    @Autowired
    public ConsoleController(DepartmentService departmentService, LectorService lectorService) {
        this.departmentService = departmentService;
        this.lectorService = lectorService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void run() {
        System.out.println("Welcome to the University Console App!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a command (type 'exit' to quit):");
            String command = scanner.nextLine();

            if ("exit".equalsIgnoreCase(command)) {
                break;
            }

            processCommand(command);
        }
    }

    private void processCommand(String command) {
        if (command.startsWith("Who is head of department")) {
            processHeadOfDepartmentCommand(command);
        } else if (command.startsWith("Show ")) {
            processShowCommand(command);
        } else if (command.startsWith("Global search by ")) {
            processGlobalSearchCommand(command);
        } else {
            System.out.println("Invalid command. Please use one of the supported formats.");
        }
    }

    private void processHeadOfDepartmentCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length == 6) {
            String departmentName = parts[5];
            String headOfDepartment = departmentService.getHeadOfDepartment(departmentName);
            System.out.println("Head of " + departmentName + " department is " + headOfDepartment);
        } else {
            System.out.println("Invalid command. Please use the format: Who is head of department {department_name}");
        }
    }

    private void processShowCommand(String command) {
        if (command.contains("statistics")) {
            processStatisticsCommand(command);
        } else if (command.contains("average salary for the department")) {
            processAverageSalaryCommand(command);
        } else if (command.contains("count of employee for")) {
            processEmployeeCountCommand(command);
        } else {
            System.out.println("Invalid command. Please use one of the supported formats.");
        }
    }

    private void processStatisticsCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length == 3) {
            String departmentName = parts[1];
            int assistantCount = departmentService.getAssistantCount(departmentName);
            int associateProfessorCount = departmentService.getAssociateProfessorsCount(departmentName);
            int professorCount = departmentService.getProfessorCount(departmentName);

            System.out.println("assistants - " + assistantCount);
            System.out.println("associate professors - " + associateProfessorCount);
            System.out.println("professors - " + professorCount);
        } else {
            System.out.println("Invalid command. Please use the format: Show {department_name} statistics");
        }
    }

    private void processAverageSalaryCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length == 8) {
            String departmentName = parts[7];
            double averageSalary = departmentService.getAverageSalary(departmentName);
            System.out.println("The average salary of " + departmentName + " is " + averageSalary);
        } else {
            System.out.println("Invalid command. Please use the format: Show the average salary for the department {department_name}");
        }
    }

    private void processEmployeeCountCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length == 6) {
            String departmentName = parts[5];
            int employeeCount = departmentService.getEmployeeCount(departmentName);
            System.out.println("The count of employees for " + departmentName + " is " + employeeCount);
        } else {
            System.out.println("Invalid command. Please use the format: Show count of employee for {department_name}");
        }
    }

    private void processGlobalSearchCommand(String command) {
        String template = command.substring("Global search by ".length());
        List<Lector> matchingLectors = lectorService.getByLectorByTemplate(template);

        if (!matchingLectors.isEmpty()) {
            System.out.println("Matching lecturers:");
            for (Lector lector : matchingLectors) {
                System.out.println(lector.getFirstName() + " " + lector.getLastName());
            }
        } else {
            System.out.println("No matching lecturers found.");
        }
    }
}
