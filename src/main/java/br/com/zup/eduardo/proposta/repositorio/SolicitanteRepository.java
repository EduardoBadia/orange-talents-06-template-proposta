package br.com.zup.eduardo.proposta.repositorio;

import br.com.zup.eduardo.proposta.modelo.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {

     Optional<Solicitante> findByDocumento(String documento);
}
