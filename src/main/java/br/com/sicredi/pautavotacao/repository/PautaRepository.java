package br.com.sicredi.pautavotacao.repository;

import br.com.sicredi.pautavotacao.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
