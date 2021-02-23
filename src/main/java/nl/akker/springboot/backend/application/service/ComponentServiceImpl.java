package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.tables.Component;
import nl.akker.springboot.backend.application.repository.ComponentsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ComponentServiceImpl implements ComponentService {

    private final ComponentsRepository componentsRepository;

    @Override
    public Collection<Component> getAllParts() {
        return componentsRepository.findAll();
    }

    @Override
    public Component getRowNumber(int componentNumber) {
        Component component = componentsRepository.findByComponentNumber(componentNumber);

        return component;
    }

}
