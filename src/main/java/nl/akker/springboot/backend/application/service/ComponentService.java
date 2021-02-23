package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.tables.Component;

import java.util.Collection;

public interface ComponentService {

    Collection<Component> getAllParts();
    Component getRowNumber(int componentNumber);
}
