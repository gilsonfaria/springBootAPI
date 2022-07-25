package br.com.pos.aws.cbf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<br.com.pos.aws.cbf.modelo.Usuario, Long> {
	
	Optional<br.com.pos.aws.cbf.modelo.Usuario> findByEmail(String email);

}
