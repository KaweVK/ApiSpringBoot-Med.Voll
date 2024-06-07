package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //indica que é uma classe que vai ser rodada automaticamente para tratar quando ocorrer algum erro/exceção nos controllers. É um interceptor
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class) //anotação para dizer de qual erro esse método trata
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }



}
