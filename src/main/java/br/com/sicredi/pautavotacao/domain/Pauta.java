package br.com.sicredi.pautavotacao.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "pautas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pauta extends BaseEntity<Long> {

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "titulo")
    private String titulo;
}
