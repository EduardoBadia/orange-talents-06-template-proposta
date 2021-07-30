package br.com.zup.eduardo.proposta.utils.validacao;

import br.com.zup.eduardo.proposta.modelo.Solicitante;
import br.com.zup.eduardo.proposta.repositorio.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SolicitanteUtil {

    @Autowired
    private SolicitanteRepository solRepository;

    public boolean verificaExistenciaDeSolicitanteNoBanco(String documento)
    {
        Optional<Solicitante> optSolicitante = solRepository.findByDocumento(documento);
        if(optSolicitante.isPresent())
        {
            return true;
        }

        return false;
    }
}
