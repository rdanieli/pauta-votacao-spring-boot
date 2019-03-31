package br.com.sicredi.pautavotacao.repository;

import br.com.sicredi.pautavotacao.domain.Pauta;
import br.com.sicredi.pautavotacao.domain.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoVotacao, Long> {

    Optional<SessaoVotacao> findByPauta(Pauta pauta);


}
