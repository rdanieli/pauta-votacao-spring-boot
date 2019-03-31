package br.com.sicredi.pautavotacao.api.v1.response;

import br.com.sicredi.pautavotacao.domain.OpcaoVoto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@Data
@EqualsAndHashCode
@ToString
public class PautaResponse {

    private String id;

    private String titulo;

    private String descricao;

    private Map<OpcaoVoto, Long> resultado;
}
