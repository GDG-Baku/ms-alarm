package az.gdg.msalarm.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alarm")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class AlarmController {

    @GetMapping
    public String invokeOtherApps() {
        return "All services will be invoked";
    }
}
