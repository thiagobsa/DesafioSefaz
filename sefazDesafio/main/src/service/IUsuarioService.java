package service;

import models.Usuario;

import java.util.List;

public interface IUsuarioService {
	void createOrUpdate(Usuario usuario);
	List<Usuario> getUsers();
	int findByEmail(String email);
	void delete(Long id);
	boolean validateUser(Usuario usuario);
}