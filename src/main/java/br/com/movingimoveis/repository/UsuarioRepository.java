package br.com.movingimoveis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.movingimoveis.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where u.email = :email and u.senha = :senha")
	public Usuario localizarUsuario(@Param(value = "email") String email,
			@Param(value = "senha") String senha);

	@Query("select u from Usuario u where u.email = :email")
	public Usuario pesquisarPorEmail(@Param(value = "email") String email);

}
