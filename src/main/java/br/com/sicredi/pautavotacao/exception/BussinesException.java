package br.com.sicredi.pautavotacao.exception;

import org.springframework.http.HttpStatus;

public class BussinesException extends RuntimeException {
    public BussinesException(String mensagem) {
        super(mensagem, null, true, false);
    }

    public int getHttpStatus() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
