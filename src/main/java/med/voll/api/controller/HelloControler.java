package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello") // indica o caminho da requisição: localhost://8080/hello
public class HelloControler {

    @GetMapping // se a requisição pra "/hello" for do tipo get chama o método olá mundo
    public String olaMundo() {
        return "Hello World Spring";
    }

}
