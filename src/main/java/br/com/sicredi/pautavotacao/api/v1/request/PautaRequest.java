package br.com.sicredi.pautavotacao.api.v1.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@ToString
public class PautaRequest {

    @NotNull(message = "Título deve ser informado.")
    @NotBlank(message = "Título não pode ser vázio.")
    private String titulo;

    @NotBlank(message = "Descrição não pode ser vázio.")
    private String descricao;
}
