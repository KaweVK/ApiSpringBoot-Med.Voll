package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //Indica que é uma classe que vai ser rodada automaticamente para tratar quando ocorrer algum erro/exceção nos controllers. É um intercepitor
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class) //anotação para dizer de qual erro esse método trata
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //erro 400 é o erro para quando é enviado na requisição um argumento inválido
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) { //posso passar a excecão para poder pegar o erro
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList()); //mapeio erro e faço uma lista
    }

    private record DadosErroValidacao(String campo, String mensagem) { //Um DTO pode ser criado numa classe para ser usado apenas dentro dela
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}

