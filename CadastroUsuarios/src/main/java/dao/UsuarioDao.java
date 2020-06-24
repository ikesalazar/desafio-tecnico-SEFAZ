package dao;

import java.util.List;

import entities.Usuario;

public interface UsuarioDao {

	public void incluir(Usuario usuario);

	public void alterar(Usuario usuario);

	public void remover(Usuario usuario);

	public List<Usuario> listarTodosUsuarios();

}
