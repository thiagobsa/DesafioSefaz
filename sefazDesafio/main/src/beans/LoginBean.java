package beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import models.Usuario;
import service.IUsuarioService;
import service.UsuarioService;

@Named(value = "beanLogin")
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private IUsuarioService usuarioService;
	public LoginBean() {
		usuarioService = new UsuarioService();
	}

	private Usuario usuario = new Usuario();
	private String mensagem = "";

	
	public String validateUser() {
		try {
			if (usuarioService.validateUser(this.usuario)) {
				return "listarTelefone";
			}
			return null;
		} catch (Exception ex) {
			mensagem = "" + ex;
			return null;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
