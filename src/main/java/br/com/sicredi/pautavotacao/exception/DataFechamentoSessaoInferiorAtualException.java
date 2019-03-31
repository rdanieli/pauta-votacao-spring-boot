package br.com.sicredi.pautavotacao.exception;

public class DataFechamentoSessaoInferiorAtualException extends BussinesException {
    public DataFechamentoSessaoInferiorAtualException() {
        super("Data de fechamento da sessão não pode ser inferior a atual.");
    }
}