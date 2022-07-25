package br.com.pos.aws.cbf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pos.aws.cbf.modelo.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador,Long> {

	Page<Jogador> findByNome(String nome, Pageable paginacao);
	
}