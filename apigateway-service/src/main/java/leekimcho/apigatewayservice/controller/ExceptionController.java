package leekimcho.apigatewayservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ExceptionController {

    @GetMapping
    public void throwException() {
        throw new RuntimeException("======= 시나리오를 위한 500 오류입니다 =======");
    }

}
