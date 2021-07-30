package br.com.zup.eduardo.proposta.controller;

import br.com.zup.eduardo.proposta.modelo.Solicitante;
import br.com.zup.eduardo.proposta.repositorio.SolicitanteRepository;
import br.com.zup.eduardo.proposta.request.SolicitanteRequest;
import br.com.zup.eduardo.proposta.utils.validacao.SolicitanteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class SolicitanteController {

    @Autowired
    private SolicitanteRepository solRepository;

    @Autowired
    private SolicitanteUtil solUtil;

    @PostMapping("/solicitantes")
    @Transactional
    public ResponseEntity<String> cria
            (@RequestBody @Valid SolicitanteRequest request, UriComponentsBuilder uriBuilder) {

        Solicitante solicitante = request.toModel();

        boolean haSolicitante = solUtil.verificaExistenciaDeSolicitanteNoBanco(solicitante.getDocumento());

        if(haSolicitante)
        {
            return ResponseEntity.unprocessableEntity()
                    .body("Não foi possível processar a sua requisição");
        }

        else {
            solRepository.save(solicitante);

            URI uri = uriBuilder.path("/solicitantes/{id}").buildAndExpand(solicitante.getId()).toUri();
            return ResponseEntity.created(uri).body(uri.getPath());
        }
    }
}
