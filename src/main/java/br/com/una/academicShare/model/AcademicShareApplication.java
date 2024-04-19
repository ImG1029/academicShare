package br.com.una.academicShare.model;

import br.com.una.academicShare.model.domain.Publicacao;
import br.com.una.academicShare.model.domain.Usuario;
import br.com.una.academicShare.model.repository.PublicacaoRepositorio;
import br.com.una.academicShare.model.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AcademicShareApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PublicacaoRepositorio publicacaoRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(AcademicShareApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome("Saulo");
		usuario.setEmail("sauloporto@mail.com");

		System.out.println(usuario);
		Usuario	usuarioSalvo = usuarioRepositorio.save(usuario);

		Publicacao publicacao = new Publicacao();
		publicacao.setConteudo("Maça é uma pseudo-fruta, arroz é um fruto seco");
		publicacao.setUsuario(usuarioSalvo);

		publicacaoRepositorio.save(publicacao);

		List<Publicacao> publicacaoList = publicacaoRepositorio.findAll();
		for(Publicacao p : publicacaoList) {
			System.out.println();
			System.out.println("Nome do Usuario: " + p.getUsuario().getNome() + "\n");
			System.out.println(p.getConteudo());
			System.out.println();
		}
	}

}
