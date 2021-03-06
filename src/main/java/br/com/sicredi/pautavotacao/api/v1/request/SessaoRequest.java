package br.com.sicredi.pautavotacao.api.v1.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class SessaoRequest {

    @ApiModelProperty(example = "2019-01-08T22:34:22.337Z")
    private LocalDateTime dataFechamento;
}
