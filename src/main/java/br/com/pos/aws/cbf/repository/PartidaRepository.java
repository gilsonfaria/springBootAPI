package br.com.pos.aws.cbf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pos.aws.cbf.modelo.Partida;

public interface PartidaRepository extends JpaRepository<Partida,Long>{

}
