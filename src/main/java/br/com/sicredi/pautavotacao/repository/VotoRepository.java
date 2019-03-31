package br.com.sicredi.pautavotacao.repository;

import br.com.sicredi.pautavotacao.domain.SessaoVotacao;
import br.com.sicredi.pautavotacao.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    Boolean existsBySessaoVotacaoAndIdEleitor(SessaoVotacao sessaoVotacao, Long idEleLong);

}
