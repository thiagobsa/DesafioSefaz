package beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import models.Usuario;
import service.IUsuarioService;
import service.UsuarioService;

@Named(value = "beanUsuario")
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public UsuarioBean() {

	}

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;
	private IUsuarioService usuarioService = new UsuarioService();

	public void addMessage(String msg) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public String InsertUsuario() throws Exception {
		try {
			usuarioService.createOrUpdate(this.usuario);
			addMessage("Usuário cadastrado com sucesso!");
			return "home";
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void listarUsuario(){
		try {			
			 setUsuarios(usuarioService.getUsers());
		} catch (Exception ex) {
			ex.printStackTrace();			
		}
	}

	public void gravar() {
		usuarioService.createOrUpdate(usuario);
		this.listarUsuario();
	}

	public void excluir(){
		usuarioService.delete(usuario.getId());
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}	
}
