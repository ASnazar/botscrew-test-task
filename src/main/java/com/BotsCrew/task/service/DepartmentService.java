package com.BotsCrew.task.service;

public interface DepartmentService {
    String getHeadOfDepartment(String departmentName);

    int getAssistantCount(String departmentName);

    int getProfessorCount(String departmentName);

    int getAssociateProfessorsCount(String departmentName);

    double getAverageSalary(String departmentName);

    int getEmployeeCount(String departmentName);
}
