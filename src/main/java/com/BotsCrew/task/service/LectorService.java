package com.BotsCrew.task.service;

import java.util.List;
import com.BotsCrew.task.model.Lector;

public interface LectorService {
    Lector getLectorByName(String firstName, String lastName);

    List<Lector> getByLectorByTemplate(String template);
}
