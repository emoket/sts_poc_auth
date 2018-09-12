package emoket.example.security.backendservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {

    @GetMapping("/admin-only")
    public String admin() {
        return "Admin only service here!";
    }

    @GetMapping("/user-service")
    public String user() {
        return "This is USER service.";
    }    

    @GetMapping("/public-service")
    public String guest() {
        return "This service is for EVERYONE.";
    }
}

