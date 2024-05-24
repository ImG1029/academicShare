package br.com.una.academicShare.model.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_publicacao")
public class Publicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacao;
    private String conteudo;
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Usuario usuario;
    @ManyToOne //@OneToMany
    @JoinColumn(name = "id_assunto")
    private Assunto assunto;

    public Long getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Long idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publicacao that = (Publicacao) o;
        return Objects.equals(idPublicacao, that.idPublicacao) && Objects.equals(conteudo, that.conteudo) && Objects.equals(usuario, that.usuario) && Objects.equals(assunto, that.assunto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPublicacao, conteudo, usuario, assunto);
    }

    public void update(Long idPublicacao, Publicacao publicacao) {
        this.idPublicacao = idPublicacao;
        this.conteudo = publicacao.getConteudo();
    }




}
