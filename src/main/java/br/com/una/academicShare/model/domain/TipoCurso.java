package br.com.una.academicShare.model.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_tipo_curso")
public class TipoCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoCurso;
    private String nome;

    public Long getIdTipoCurso() {
        return idTipoCurso;
    }

    public void setIdTipoCurso(Long idTipoCurso) {
        this.idTipoCurso = idTipoCurso;
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
        TipoCurso tipoCurso = (TipoCurso) o;
        return Objects.equals(idTipoCurso, tipoCurso.idTipoCurso) && Objects.equals(nome, tipoCurso.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoCurso, nome);
    }
}
