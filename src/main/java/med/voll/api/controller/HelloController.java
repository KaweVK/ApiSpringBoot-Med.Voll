package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //anotação para informar que é o controler de uma api rest
@RequestMapping("/hello") //diz a url que esse controller vai responder. Nesse caso, o "/hello"
public class HelloController {

    @GetMapping //diz que ao chegar a requisição Mapping, se for do tipo get, execute o que estiver abaixo
    public String olaMundo() {
        return "Hello, World with Spring!"; //se eu mudo algo do projeto, como essa frase, quando salvar o spring vai identificar e reiniciar automaticamente
    }

}
