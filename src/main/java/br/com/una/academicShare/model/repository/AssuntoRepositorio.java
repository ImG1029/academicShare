package br.com.una.academicShare.model.repository;

import br.com.una.academicShare.model.domain.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssuntoRepositorio extends JpaRepository<Assunto, Long> {
}
