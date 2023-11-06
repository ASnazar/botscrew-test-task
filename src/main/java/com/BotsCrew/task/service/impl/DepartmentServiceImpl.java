package com.BotsCrew.task.service.impl;

import java.util.List;
import com.BotsCrew.task.model.Department;
import com.BotsCrew.task.model.Lector;
import com.BotsCrew.task.repository.DepartmentRepository;
import com.BotsCrew.task.repository.LectorRepository;
import com.BotsCrew.task.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private LectorRepository lectorRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, LectorRepository lectorRepository) {
        this.departmentRepository = departmentRepository;
        this.lectorRepository = lectorRepository;
    }

    @Override
    public String getHeadOfDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department != null) {
            Lector head = department.getHead();
            if (head != null) {
                return head.getFirstName() + head.getLastName();
            }
        }
        return "Department not found or head information is missing.";
    }

    @Override
    public int getAssistantCount(String departmentName) {
        return getCountByDegree(departmentName, Lector.Degree.ASSISTANT );
    }

    @Override
    public int getProfessorCount(String departmentName) {
        return getCountByDegree(departmentName, Lector.Degree.PROFESSOR);
    }

    @Override
    public int getAssociateProfessorsCount(String departmentName) {
        return getCountByDegree(departmentName, Lector.Degree.ASSOCIATE_PROFESSOR);
    }

    @Override
    public double getAverageSalary(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department != null) {
            List<Lector> lectors = lectorRepository.findByDepartments(department);
            if (!lectors.isEmpty()) {
                double totalSalary = lectors.stream().mapToDouble(Lector::getSalary).sum();
                return totalSalary / lectors.size();
            }
        }
        return 0.0;
    }

    @Override
    public int getEmployeeCount(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department != null) {
            return lectorRepository.countByDepartments(department);
        }
        return 0;
    }

    private int getCountByDegree(String departmentName,Lector.Degree degree) {
        Department department = departmentRepository.findByName(departmentName);
        if (department != null) {
            return lectorRepository.countByDepartmentsAndDegree(department, degree);
        }
        return 0;
    }
}
