package br.com.una.academicShare.model.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_faculdade")
public class Faculdade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFaculdade;
    private String nome;

    public Long getIdFaculdade() {
        return idFaculdade;
    }

    public void setIdFaculdade(Long idFaculdade) {
        this.idFaculdade = idFaculdade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculdade faculdade = (Faculdade) o;
        return Objects.equals(idFaculdade, faculdade.idFaculdade) && Objects.equals(nome, faculdade.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFaculdade, nome);
    }

    public void update(Long idFaculdade, Faculdade faculdade) {
        this.idFaculdade = idFaculdade;
        this.nome = faculdade.getNome();

    }
}
