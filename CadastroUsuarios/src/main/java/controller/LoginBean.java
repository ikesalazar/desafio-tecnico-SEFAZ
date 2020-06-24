package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDao;
import dao.UsuarioDaoImplt;
import entities.Usuario;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean {

	private String inputEmail;
	private String inputSenha;

	private List<Usuario> listaUsuarios;
	private Usuario usuario;
	private UsuarioDao usuarioDao;

	public LoginBean() {
		this.listaUsuarios = new ArrayList<Usuario>();
		this.usuario = new Usuario();
		this.usuarioDao = new UsuarioDaoImplt();
	}

	public String loginIn() {

		Usuario usuarioLogado = null;

		this.listaUsuarios = this.usuarioDao.listarTodosUsuarios();

		for (Usuario usuarioPesquisa : listaUsuarios) {

			if (inputEmail != null && usuarioPesquisa.getEmail().equals(this.inputEmail) && inputSenha != null
					&& usuarioPesquisa.getSenha().equals(this.inputSenha)) {

				usuarioLogado = usuarioPesquisa;
			}
		}

		if (usuarioLogado != null) {

			return "/pages/panelUsuariosView.xhtml";

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail ou senha inválidos.", ""));

			return null;
		}
	}

	public void criarLogin() {

		Usuario novoLogin = new Usuario();

		novoLogin.setNome(this.usuario.getNome());
		novoLogin.setEmail(this.usuario.getEmail());
		novoLogin.setSenha(this.usuario.getSenha());

		boolean buscaEmail = false;

		this.listaUsuarios = this.usuarioDao.listarTodosUsuarios();

		for (Usuario emailPesquisa : listaUsuarios) {
			if (emailPesquisa.getEmail().equals(this.usuario.getEmail())) {
				buscaEmail = true;
			}
		}

		if (buscaEmail) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário já existe!", ""));
		} else {

			this.usuarioDao.incluir(novoLogin);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuário Cadastrado!"));

			this.usuario = new Usuario();
		}
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getInputEmail() {
		return inputEmail;
	}

	public void setInputEmail(String inputEmail) {
		this.inputEmail = inputEmail;
	}

	public String getInputSenha() {
		return inputSenha;
	}

	public void setInputSenha(String inputSenha) {
		this.inputSenha = inputSenha;
	}

}
