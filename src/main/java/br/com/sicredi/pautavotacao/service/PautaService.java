package br.com.sicredi.pautavotacao.service;

import br.com.sicredi.pautavotacao.domain.OpcaoVoto;
import br.com.sicredi.pautavotacao.domain.Pauta;
import br.com.sicredi.pautavotacao.domain.SessaoVotacao;
import br.com.sicredi.pautavotacao.domain.Voto;
import br.com.sicredi.pautavotacao.exception.*;
import br.com.sicredi.pautavotacao.repository.PautaRepository;
import br.com.sicredi.pautavotacao.repository.SessaoRepository;
import br.com.sicredi.pautavotacao.repository.VotoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PautaService {

    @Value( "${tempo.sessao.padrao.segundos}" )
    private Integer tempoSessaoPadrao;

    private final PautaRepository pautaRepository;
    private final SessaoRepository sessaoRepository;
    private final VotoRepository votoRepository;

    @Transactional
    public Pauta criarNovaPauta(Pauta pauta) {
        pautaRepository.save(pauta);
        return pauta;
    }

    public List<Pauta> getPautas() {
        return pautaRepository.findAll();
    }

    public Optional<Pauta> getPauta(String id) {
        return pautaRepository.findById(Long.valueOf(id));
    }

    @Transactional
    public void iniciarSessaoVotacao(String idPauta, LocalDateTime dataFechamento) {
        Pauta pauta = getPauta(idPauta).orElseThrow(() -> new PautaNaoEncontradaException(idPauta));

        if(Objects.requireNonNull(getSessaoVotacao(pauta)).isPresent()){
            throw new JaExisteSessaoException(idPauta);
        }

        if (dataFechamento != null && LocalDateTime.now().isAfter(dataFechamento)) {
            throw new DataFechamentoSessaoInferiorAtualException();
        }

        criaSessaoVotacao(pauta, dataFechamento);
    }

    private void criaSessaoVotacao(Pauta pauta, LocalDateTime dataFechamento) {
        SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
                .dataAbertura(LocalDateTime.now())
                .dataFechamento(dataFechamento(dataFechamento))
                .pauta(pauta)
                .build();

        sessaoRepository.save(sessaoVotacao);
    }

    private LocalDateTime dataFechamento(LocalDateTime dataFechamento) {
        return dataFechamento == null ? LocalDateTime.now().plusSeconds(tempoSessaoPadrao) : dataFechamento;
    }

    private Optional<SessaoVotacao> getSessaoVotacao(Pauta pauta) {
        return sessaoRepository.findByPauta(pauta);
    }

    @Transactional
    public void votar(String idPauta, Voto voto) {
        SessaoVotacao sessaoVotacao = getSessaoVotacao(getPauta(idPauta)
                .orElseThrow(() -> new PautaNaoEncontradaException(idPauta)))
                .orElseThrow(SessaoNaoEncontradaException::new);

        if (LocalDateTime.now().isAfter(sessaoVotacao.getDataFechamento())) {
            throw new SessaoFechadaException();
        }

        voto.setSessaoVotacao(sessaoVotacao);
        voto.setDataHora(LocalDateTime.now());

        if(votoRepository.existsBySessaoVotacaoAndIdEleitor(sessaoVotacao, voto.getIdEleitor())) {
            throw new EleitorJaVotouException();
        }

        votoRepository.save(voto);
    }

    public Map<OpcaoVoto, Long> result(Pauta pauta) {
        return getSessaoVotacao(pauta).map(sv -> sv.getVotos()
                .stream()
                .collect(Collectors.groupingBy(Voto::getOpcaoVoto,
                        Collectors.counting()))).orElse(null);
    }
}
