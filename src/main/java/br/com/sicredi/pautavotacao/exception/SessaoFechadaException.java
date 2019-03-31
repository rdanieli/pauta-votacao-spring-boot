package br.com.sicredi.pautavotacao.exception;

public class SessaoFechadaException extends BussinesException {
    public SessaoFechadaException() {
        super("Sessão encontra-se fechada, não é possível realizar mais votações na sessão.");
    }
}