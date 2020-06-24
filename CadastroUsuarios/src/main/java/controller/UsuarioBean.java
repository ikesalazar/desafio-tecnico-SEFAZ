package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.TelefoneDao;
import dao.TelefoneDaoImplt;
import dao.UsuarioDao;
import dao.UsuarioDaoImplt;
import entities.Telefone;
import entities.Usuario;

@ManagedBean(name = "UsuarioBean")
@RequestScoped
public class UsuarioBean {

	private Telefone telefone;
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private TelefoneDao telefoneDao;

	private List<Usuario> listaUsuarios;
	private List<Telefone> listaTelefones;

	public UsuarioBean() {
		this.usuario = new Usuario();
		this.telefone = new Telefone();
		this.usuarioDao = new UsuarioDaoImplt();
		this.telefoneDao = new TelefoneDaoImplt();
		this.listaUsuarios = new ArrayList<Usuario>();
		this.listaTelefones = new ArrayList<Telefone>();
		this.usuario.setTelefones(new ArrayList<Telefone>());
	}

	public void salvarUsuario() {

		boolean usuarioEncontrado = false;

		Usuario usuarioSalvo = new Usuario(this.usuario.getNome(), this.usuario.getEmail(), this.usuario.getSenha());

		Telefone telefoneSalvo = new Telefone(this.telefone.getDdd(), this.telefone.getNumero(),
				this.telefone.getTipo(), usuarioSalvo);

		for (Usuario procurarUsuario : this.usuarioDao.listarTodosUsuarios()) {
			if (usuarioSalvo.getEmail().equals(procurarUsuario.getEmail())) {

				usuarioEncontrado = true;
			}
		}

		if (usuarioEncontrado) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário já existe!", ""));
		} else {
			this.usuarioDao.incluir(usuarioSalvo);
			this.telefoneDao.incluir(telefoneSalvo);
		}

		this.usuario = new Usuario();
		this.telefone = new Telefone();
	}

	public void maisUmTelefone() {
		Telefone segundoTelefone = new Telefone(this.telefone.getDdd(), this.telefone.getNumero(),
				this.telefone.getTipo(), this.usuario);

		this.usuario.getTelefones().add(segundoTelefone);
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public TelefoneDao getTelefoneDao() {
		return telefoneDao;
	}

	public void setTelefoneDao(TelefoneDao telefoneDao) {
		this.telefoneDao = telefoneDao;
	}

	public List<Telefone> getListaTelefones() {
		return listaTelefones;
	}

	public void setListaTelefones(List<Telefone> listaTelefones) {
		this.listaTelefones = listaTelefones;
	}
}
