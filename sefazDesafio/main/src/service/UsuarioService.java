package service;

import models.Usuario;
import repository.UsuarioRepository;

import java.util.List;

public class UsuarioService implements IUsuarioService {

	private UsuarioRepository usuarioRepository;

	public UsuarioService() {
		usuarioRepository = new UsuarioRepository();
	}

	public void createOrUpdate(Usuario usuario) {
		int existeUsuario = findByEmail(usuario.getEmail());
		if (existeUsuario == 0) {
			create(usuario);
		} else {
			update(usuario);
		}
	}

	public List<Usuario> getUsers() {
		List<Usuario> usuarios = usuarioRepository.list();
		return usuarios;
	}

	public int findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	public void update(Usuario usuario) {
		usuarioRepository.salvar(usuario);
	}

	public void delete(Long id) {
		usuarioRepository.deletar(id);
	}

	public void create(Usuario usuario) {
		usuarioRepository.salvar(usuario);
	}

	public boolean validateUser(Usuario usuario) throws Exception {
		return usuarioRepository.validateUser(usuario);
	}
}