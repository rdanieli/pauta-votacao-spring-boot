package br.com.sicredi.pautavotacao.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "sessoes_votacao")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"pauta"})
public class SessaoVotacao extends BaseEntity<Long> {

    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;

    @Column(name = "data_encerramento")
    private LocalDateTime dataFechamento;

    @OneToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @OneToMany(mappedBy = "sessaoVotacao")
    private List<Voto> votos;
}
