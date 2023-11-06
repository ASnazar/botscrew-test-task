package com.BotsCrew.task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name = "lectors")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Degree degree;
    private double salary;


    @ManyToMany(mappedBy = "lectors")
    private Set<Department> departments;
    public enum Degree {
        ASSISTANT("ASSISTANT"),
        PROFESSOR("PROFESSOR"),
        ASSOCIATE_PROFESSOR("ASSOCIATE PROFESSOR");
        private final String val;

        Degree(String val) {
            this.val = val;
        }
    }
}
