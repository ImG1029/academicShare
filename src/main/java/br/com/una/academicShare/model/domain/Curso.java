package br.com.una.academicShare.model.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "ID_Tipo_Curso")
    private TipoCurso idTipoCurso;
    @ManyToOne
    @JoinColumn(name = "ID_Faculdade")
    private Faculdade faculdade;

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCurso getIdTipoCurso() {
        return idTipoCurso;
    }

    public void setIdTipoCurso(TipoCurso idTipoCurso) {
        this.idTipoCurso = idTipoCurso;
    }

    public Faculdade getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(idCurso, curso.idCurso) && Objects.equals(nome, curso.nome) && Objects.equals(idTipoCurso, curso.idTipoCurso) && Objects.equals(faculdade, curso.faculdade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurso, nome, idTipoCurso, faculdade);
    }

    public void update(Long idCurso , Curso curso) {
        this.idCurso = idCurso;
        this.nome = getNome();

    }

}
