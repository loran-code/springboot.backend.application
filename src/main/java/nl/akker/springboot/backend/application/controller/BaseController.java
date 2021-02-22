package nl.akker.springboot.backend.application.controller;

import nl.akker.springboot.backend.application.model.Info;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class BaseController {

    @GetMapping()
    public String hello() {
        return "Novi backend API assignment";
    }

    @GetMapping(value = "info", produces = { "application/json" })
        public String info() {
            return Info.getInfo();
        }
}
