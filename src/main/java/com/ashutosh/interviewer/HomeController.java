package controller;//error 1

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {//error 2

    @GetMapping("/")
    public String home() {
        return "Welcome to AI Mock Interviewer Backend!";
    }
}
