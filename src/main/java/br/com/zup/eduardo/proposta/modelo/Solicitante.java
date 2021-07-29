package br.com.zup.eduardo.proposta.modelo;

import br.com.zup.eduardo.proposta.config.anotacao.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
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

    @Deprecated
    public Solicitante() {
    }

    public Solicitante(@NotBlank @Email String email, @NotBlank String nome,
                       @NotBlank String endereco, @Min(value = 0)
                       @NotNull Double salario, @NotBlank String documento) {
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.documento = documento;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitante that = (Solicitante) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
