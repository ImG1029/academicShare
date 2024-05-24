package br.com.una.academicShare.model.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_assunto")
public class Assunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAssunto;
    private String Nome;
    public Long getIdAssunto() {
        return idAssunto;
    }
    public void setIdAssunto(Long idAssunto) {
        this.idAssunto = idAssunto;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assunto assunto = (Assunto) o;
        return Objects.equals(idAssunto, assunto.idAssunto) && Objects.equals(Nome, assunto.Nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAssunto, Nome);
    }

    public void update(Long idAssunto, Assunto assunto) {
        this.idAssunto = idAssunto;
        this.Nome = assunto.getNome();

    }

}
