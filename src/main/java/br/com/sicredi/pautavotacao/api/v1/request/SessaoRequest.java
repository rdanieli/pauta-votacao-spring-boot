package br.com.sicredi.pautavotacao.api.v1.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class SessaoRequest {

    private LocalDateTime dataFechamento;
}
