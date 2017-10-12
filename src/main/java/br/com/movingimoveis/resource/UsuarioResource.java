package br.com.movingimoveis.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.movingimoveis.model.Usuario;
import br.com.movingimoveis.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("${origem-permitida}")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarios;

	@RequestMapping(method = RequestMethod.GET, params = {"email", "senha"})
	public ResponseEntity<Usuario> login(@RequestParam(value = "email") String email,
			@RequestParam(value = "senha") String senha) {
		Usuario user = usuarios.localizarUsuario(email, senha);
		if (user == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Usuario> adicionar(@RequestBody @Valid Usuario usuario) {
		Usuario user = usuarios.localizarUsuario(usuario.getEmail(),
				usuario.getSenha());

		if (user != null) {
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Usuario>(usuarios.save(usuario),
				HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Usuario> alterar(@RequestBody Usuario usuario) {
		Usuario user = usuarios.pesquisarPorEmail(usuario.getEmail());
		
		if (user == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		user.setSenha(usuario.getSenha());
		
		return new ResponseEntity<Usuario>(usuarios.save(user),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, params = {"email"})
	public ResponseEntity<Usuario> recuperarSenha(@RequestParam(value = "email") String email) {
		Usuario user = usuarios.pesquisarPorEmail(email);
		
		if (user == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		
		System.out.println("email enviado");
		
		//aqui seria implementado a rotina para enviar email
		//eu não implementei por falta de um servidor smtp

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

}
