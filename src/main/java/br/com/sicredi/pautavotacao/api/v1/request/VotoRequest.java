package br.com.sicredi.pautavotacao.api.v1.request;

import br.com.sicredi.pautavotacao.domain.OpcaoVoto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@ToString
public class VotoRequest {

    @NotNull(message = "informe o eleitor que irá votar.")
    private Long idEleitor;

    @NotNull(message = "informe a opção do voto, valores podem ser: SIM/NAO")
    private OpcaoVoto opcaoVoto;
}
