package br.com.sicredi.pautavotacao.exception;

public class SessaoNaoEncontradaException extends BussinesException {
    public SessaoNaoEncontradaException() {
        super("Sessão de votação não encontrada.");
    }
}