package br.com.sicredi.pautavotacao.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "votos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "sessaoVotacao")
public class Voto extends BaseEntity<Long> {

    @Column(name = "opcao")
    private OpcaoVoto opcaoVoto;

    @Column(name = "data")
    private LocalDateTime dataHora;

    @Column(name = "id_eleitor")
    private Long idEleitor;

    @ManyToOne
    @JoinColumn(name = "id_sessao_votacao")
    private SessaoVotacao sessaoVotacao;

//    public boolean eleitorJaVotou() {
//        return Voto.find("sessaoVotacao = ?1 and idEleitor = ?2", sessaoVotacao, idEleitor).count() > 0;
//    }
}
