package br.com.una.academicShare;

import br.com.una.academicShare.model.domain.*;
import br.com.una.academicShare.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AcademicShareApplication implements CommandLineRunner {

	@Autowired
	private AssuntoRepositorio assuntoRepositorio;

	@Autowired
	private CursoRepositorio cursoRepositorio;

	@Autowired
	private FaculdadeRepositorio faculdadeRepositorio;

	@Autowired
	private PublicacaoRepositorio publicacaoRepositorio;

	@Autowired
	private TipoCursoRepositorio tipoCursoRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(AcademicShareApplication.class, args);
	}

	public void run(String... args) throws Exception {

		TipoCurso tipoCurso = new TipoCurso();
		tipoCurso.setNome("Engenharia");

		TipoCurso tipoCursoSalvo = tipoCursoRepositorio.save(tipoCurso);

		Faculdade faculdade = new Faculdade();
		faculdade.setNome("Universidade Federal de Santa Maria");

		Faculdade faculdadeSalvo = faculdadeRepositorio.save(faculdade);

		Curso curso = new Curso();
		curso.setNome("Agronomia");
		curso.setIdTipoCurso(tipoCursoSalvo);
		curso.setFaculdade(faculdadeSalvo);

		Curso cursoSalvo = cursoRepositorio.save(curso);

		Usuario usuario = new Usuario();
		usuario.setNome("Saulo");
		usuario.setEmail("sauloporto@mail.com");
		usuario.setSenha("454928A@fs300");
		usuario.setNumeroDeMatricula(123456);
		usuario.setCurso(cursoSalvo);

		Usuario	usuarioSalvo = usuarioRepositorio.save(usuario);

		Assunto assunto = new Assunto();
		assunto.setNome("Percevejos");

		Assunto assuntoSalvo = assuntoRepositorio.save(assunto);

		Publicacao publicacao = new Publicacao();
		publicacao.setConteudo("Sexo.");
		publicacao.setUsuario(usuarioSalvo);
		publicacao.setAssunto(assuntoSalvo);

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
