package br.com.pos.aws.cbf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pos.aws.cbf.modelo.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia,Long>{

	Page<Transferencia> findByJogadorId(Long id, Pageable paginacao);
	
}