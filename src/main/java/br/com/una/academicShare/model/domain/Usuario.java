package br.com.una.academicShare.model.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;
    private String email;
    private String senha;
    private int numeroDeMatricula;
    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNumeroDeMatricula() {
        return numeroDeMatricula;
    }

    public void setNumeroDeMatricula(int numeroDeMatricula) {
        this.numeroDeMatricula = numeroDeMatricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Double.compare(numeroDeMatricula, usuario.numeroDeMatricula) == 0 && Objects.equals(idUsuario, usuario.idUsuario) && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(curso, usuario.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nome, email, senha, numeroDeMatricula, curso);
    }

    public void update(Long idUsuario, Usuario usuario) {
        this.idUsuario = idUsuario;
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.numeroDeMatricula = usuario.getNumeroDeMatricula();
        this.curso = usuario.getCurso();
    }

}
