package br.com.sicredi.pautavotacao.exception;

public class EleitorJaVotouException extends BussinesException {
    public EleitorJaVotouException() {
        super("Eleitor já efetuou o seu voto.");
    }
}