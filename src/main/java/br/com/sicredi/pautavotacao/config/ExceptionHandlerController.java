package br.com.sicredi.pautavotacao.config;

import br.com.sicredi.pautavotacao.exception.BussinesException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerWebInputException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler
    public ResponseEntity handleException(Throwable throwable) {
        LOGGER.error(throwable.getMessage(), throwable);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler
    public ResponseEntity handleException(BussinesException throwable) {
        LOGGER.error(throwable.getMessage(), throwable);

        return ResponseEntity.status(throwable.getHttpStatus())
                .body(new ErrorResponse(throwable.getMessage(), null));
    }

    @ExceptionHandler
    public ResponseEntity handlerServerInputException(ServerWebInputException e) {
        LOGGER.error(e.getMessage(), e);

        return ResponseEntity.badRequest().body(new ErrorResponse("Falta um parâmetro.", e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        LOGGER.error(e.getMessage(), e);
        String mensagem = String.format("Argumento '%s' deve ser válido '%s' mas é '%s'.",
                e.getName(), Objects.requireNonNull(e.getRequiredType()).getSimpleName(), e.getValue());
        return new ResponseEntity<>(new ErrorResponse(mensagem, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleJsonProcessingException(JsonProcessingException e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorResponse("JSON de entrada inválido.", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.error(e.getMessage(), e);
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(message, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Data
    @ToString
    public static class ErrorResponse {

        private String mensagem;
        private String stackTrace;

        public ErrorResponse(String s, String stackTrace) {
            this.mensagem = s;
            this.stackTrace = stackTrace;
        }
    }
}
