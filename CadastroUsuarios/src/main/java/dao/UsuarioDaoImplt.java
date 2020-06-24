package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConexaoJdbc;
import entities.Telefone;
import entities.Usuario;

public class UsuarioDaoImplt implements UsuarioDao {

	public void incluir(Usuario usuario) {

		String sql = "INSERT INTO usuario (nome, email, senha) values(?, ?, ?)";

		Connection conexao;
		try {
			conexao = ConexaoJdbc.getConexao();

			PreparedStatement pst = conexao.prepareStatement(sql);

			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());

			pst.execute();

			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterar(Usuario usuario) {

		String sql = "UPDATE USUARIO SET nome = ?, email = ?, senha = ? where email = ?";

		Connection conexao;
		try {
			conexao = ConexaoJdbc.getConexao();

			PreparedStatement pst = conexao.prepareStatement(sql);

			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setString(4, usuario.getEmail());

			pst.execute();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void remover(Usuario usuario) {

		String sql = "DELETE FROM USUARIO WHERE email = ?";

		Connection conexao;
		try {
			conexao = ConexaoJdbc.getConexao();

			PreparedStatement pst = conexao.prepareStatement(sql);

			pst.setString(1, usuario.getEmail());

			pst.execute();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Usuario> listarTodosUsuarios() {

		String sql = "SELECT u.nome, u.email, t.ddd, t.numero, t.tipo FROM usuario u, telefone t WHERE t.usuario = u.email";

		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		List<Telefone> listaTelefones = new ArrayList<Telefone>();

		Connection conexao;
		try {
			conexao = ConexaoJdbc.getConexao();

			PreparedStatement pst = conexao.prepareStatement(sql);

			ResultSet rest = pst.executeQuery();

			while (rest.next()) {

				Usuario usuario = new Usuario();
				usuario.setNome(rest.getString("nome"));
				usuario.setEmail(rest.getString("email"));

				listaUsuarios.add(usuario);

				Telefone telefone = new Telefone();
				telefone.setDdd(rest.getInt("ddd"));
				telefone.setNumero(rest.getString("numero"));
				telefone.setTipo(rest.getString("tipo"));

				listaTelefones.add(telefone);

				usuario.setTelefones(listaTelefones);
			}

			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaUsuarios;
	}

}
