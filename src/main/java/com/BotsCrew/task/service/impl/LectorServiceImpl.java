package com.BotsCrew.task.service.impl;

import java.util.List;
import com.BotsCrew.task.model.Lector;
import com.BotsCrew.task.repository.LectorRepository;
import com.BotsCrew.task.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectorServiceImpl implements LectorService {

    private LectorRepository lectorRepository;

    @Autowired
    public LectorServiceImpl(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    @Override
    public Lector getLectorByName(String firstName, String lastName) {
        return lectorRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Lector> getByLectorByTemplate(String template) {
        return lectorRepository.findByFirstNameContainingOrLastNameContaining(template, template);
    }
}
