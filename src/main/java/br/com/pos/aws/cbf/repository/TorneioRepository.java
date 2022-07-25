package br.com.pos.aws.cbf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pos.aws.cbf.modelo.Torneio;

public interface TorneioRepository extends JpaRepository<Torneio,Long>{

	Page<Torneio> findByNome(String nome, Pageable paginacao);
}
