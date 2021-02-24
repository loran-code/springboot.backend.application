package nl.akker.springboot.backend.application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnObject {

    public String message;

    public Object object;
}