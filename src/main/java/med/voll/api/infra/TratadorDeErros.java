package med.voll.api.infra;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class) // anotação para que esse método seja chamado quando ocorrer essa exceção
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }


}
