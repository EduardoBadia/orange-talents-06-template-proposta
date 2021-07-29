package br.com.zup.eduardo.proposta.controller;

import br.com.zup.eduardo.proposta.modelo.Solicitante;
import br.com.zup.eduardo.proposta.request.SolicitanteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class SolicitanteController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/solicitantes")
    @Transactional
    public ResponseEntity<String> cria
            (@RequestBody @Valid SolicitanteRequest request, UriComponentsBuilder uriBuilder) {

        Solicitante solicitante = request.toModel();

        manager.persist(solicitante);

        URI uri = uriBuilder.path("/solicitantes/{id}").buildAndExpand(solicitante.getId()).toUri();
        return ResponseEntity.created(uri).body(uri.getPath());
    }
}
