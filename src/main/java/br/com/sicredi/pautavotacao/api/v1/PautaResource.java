package br.com.sicredi.pautavotacao.api.v1;

import br.com.sicredi.pautavotacao.api.v1.request.PautaRequest;
import br.com.sicredi.pautavotacao.api.v1.request.SessaoRequest;
import br.com.sicredi.pautavotacao.api.v1.request.VotoRequest;
import br.com.sicredi.pautavotacao.api.v1.response.PautaResponse;
import br.com.sicredi.pautavotacao.domain.Pauta;
import br.com.sicredi.pautavotacao.domain.Voto;
import br.com.sicredi.pautavotacao.exception.PautaNaoEncontradaException;
import br.com.sicredi.pautavotacao.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/pautas")
@RequiredArgsConstructor
public class PautaResource {

    private static final Logger logger = LoggerFactory.getLogger(PautaResource.class);

    private final PautaService pautaService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<PautaResponse> criarPauta(@RequestBody @Valid PautaRequest pautaRequest) {
        logger.info("Chamada para criar pauta: {}.", pautaRequest);

        Pauta pauta = objectMapper.convertValue(pautaRequest, Pauta.class);

        pauta = pautaService.criarNovaPauta(pauta);

        logger.info("Pauta criada com sucesso.");

        return ResponseEntity.ok(objectMapper.convertValue(pauta, PautaResponse.class))
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping
    public List<PautaResponse> getPautas() {
        logger.info("Retornando pautas.");
        return pautaService.getPautas().stream()
                .map(this::getPautaResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{idPauta}")
    public PautaResponse getPauta(@PathVariable("idPauta")
                                          String idPauta) {
        logger.info("Buscando pauta id {}.", idPauta);

        return getPautaResponse(pautaService.getPauta(idPauta).orElseThrow(() -> new PautaNaoEncontradaException(idPauta)));
    }

    private PautaResponse getPautaResponse(Pauta pauta) {
        PautaResponse pautaResponse = objectMapper.convertValue(pauta, PautaResponse.class);
        pautaResponse.setResultado(pautaService.result(pauta));

        return pautaResponse;
    }

    @PostMapping("/{idPauta}/iniciar-sessao-votacao")
    public ResponseEntity iniciarSessaoVotacao(@PathVariable("idPauta") String idPauta,
                                               @RequestBody SessaoRequest abrirSessaoRequest) {
        logger.info("Pauta {} abertura de sessão.", idPauta);

        pautaService.iniciarSessaoVotacao(idPauta, abrirSessaoRequest != null ? abrirSessaoRequest.getDataFechamento() : null);

        logger.info("Sessão de votação iniciada com sucesso.");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idPauta}/votar")
    public ResponseEntity votar(@PathVariable("idPauta") String idPauta,
                                @RequestBody @Valid VotoRequest votoRequest) {
        logger.info("Pauta {} adicionando voto {}.", idPauta, votoRequest);

        pautaService.votar(idPauta, objectMapper.convertValue(votoRequest, Voto.class));

        logger.info("Voto realizado com sucesso.");

        return ResponseEntity.ok().build();
    }
}
