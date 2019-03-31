package br.com.sicredi.pautavotacao.exception;

public class PautaNaoEncontradaException extends BussinesException {
    public PautaNaoEncontradaException(String mensagem) {
        super(String.format("Pauta %s não existe.", mensagem));
    }
}