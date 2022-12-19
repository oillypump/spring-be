package rodl.projecttigabe.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class WelcomeController {
    
    @GetMapping(value = "/api/welcome")
    public String welcome() {
        return "check success!";
    }
}
