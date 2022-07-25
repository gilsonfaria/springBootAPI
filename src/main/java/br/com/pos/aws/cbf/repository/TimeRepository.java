package br.com.pos.aws.cbf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pos.aws.cbf.modelo.Time;

public interface TimeRepository extends JpaRepository<Time,Long>{

	Page<Time> findByNome(String nome, Pageable paginacao);
	
//	Page<Time> findByNome(String nome, Pageable paginacao);
}
