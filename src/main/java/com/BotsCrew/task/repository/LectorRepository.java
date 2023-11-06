package com.BotsCrew.task.repository;

import com.BotsCrew.task.model.Department;
import com.BotsCrew.task.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    int countByDepartmentsAndDegree(Department department, Lector.Degree degree);

    List<Lector> findByDepartments(Department department);

    int countByDepartments(Department department);

    List<Lector> findByFirstNameContainingOrLastNameContaining(String template, String template1);

    Lector findByFirstNameAndLastName(String firstName, String lastName);
}
