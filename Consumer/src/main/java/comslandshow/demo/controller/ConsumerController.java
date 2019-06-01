package comslandshow.demo.controller;

import comslandshow.demo.service.ConsumerService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/", description = "controller", produces = "application/json")
public class ConsumerController {

    private final ConsumerService service = new ConsumerService();

    @GetMapping("/communication")
    public String[] connection() {
        return service.getCommunication();
    }

}
