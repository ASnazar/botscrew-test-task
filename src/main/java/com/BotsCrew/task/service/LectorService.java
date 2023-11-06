package com.BotsCrew.task.service;

import com.BotsCrew.task.model.Lector;
import java.util.List;

public interface LectorService {
    Lector getLectorByName(String firstName, String lastName);
    List<Lector> getByLectorByTemplate(String template);
}
