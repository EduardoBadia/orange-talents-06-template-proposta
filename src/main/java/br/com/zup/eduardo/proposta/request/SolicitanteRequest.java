package br.com.zup.eduardo.proposta.request;

import br.com.zup.eduardo.proposta.config.anotacao.Document;
import br.com.zup.eduardo.proposta.config.anotacao.UniqueValue;
import br.com.zup.eduardo.proposta.modelo.Solicitante;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitanteRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Solicitante.class, fieldName="email", message="este email já existe")
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @Min(value = 0)
    @NotNull
    private Double salario;

    @Document
    @NotBlank
    private String documento;

    public SolicitanteRequest(@NotBlank @Email String email, @NotBlank String nome,
                              @NotBlank String endereco,
                              @Min(value = 0) @NotNull Double salario, @NotBlank String documento) {
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.documento = documento;
    }

    public Solicitante toModel()
    {
        return new Solicitante(this.email, this.nome, this.endereco, this.salario, this.documento);
    }
}
