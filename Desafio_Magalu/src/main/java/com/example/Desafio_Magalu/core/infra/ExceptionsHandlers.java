package com.example.Desafio_Magalu.core.infra;

import com.example.Desafio_Magalu.core.exception.IdNaoEncontrado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandlers {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageRestError> GlobalExceptionHandler()
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.INTERNAL_SERVER_ERROR,"Erro interno, tente novamente mais tarde");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageRestError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageRestError> InputErrorExceptionHandler(MethodArgumentNotValidException ex)
    {
        String MensagemErro = ex.getBindingResult().getFieldErrors().stream().map(error->error.getField()).findFirst().orElse("Erro de validação");
        MessageRestError messageRestError = new MessageRestError(HttpStatus.BAD_REQUEST, MensagemErro);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageRestError);
    }

    @ExceptionHandler(IdNaoEncontrado.class)
    public ResponseEntity<MessageRestError> IdNaoEncontradoExceptionHandler(IdNaoEncontrado ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.NOT_FOUND,ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageRestError);
    }
}
