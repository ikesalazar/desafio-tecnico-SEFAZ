package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConexaoJdbc;
import entities.Telefone;

public class TelefoneDaoImplt implements TelefoneDao {

	private Long resgataId() {

		String sqnc = "SELECT s_telefone.NEXTVAL FROM DUAL";

		Long id = null;

		Connection conexao;
		try {
			conexao = ConexaoJdbc.getConexao();

			PreparedStatement pst = conexao.prepareStatement(sqnc);

			ResultSet rest = pst.executeQuery();

			while (rest.next()) {

				id = rest.getLong(1);

			}

			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public void incluir(Telefone telefone) {

		String sql = "INSERT INTO telefone (id, ddd, numero, tipo, usuario) values(?, ?, ?, ?, ?)";

		Connection conexao;

		try {

			conexao = ConexaoJdbc.getConexao();

			PreparedStatement pst = conexao.prepareStatement(sql);

			Long id = this.resgataId();

			pst.setLong(1, id);
			pst.setInt(2, telefone.getDdd());
			pst.setString(3, telefone.getNumero());
			pst.setString(4, telefone.getTipo());
			pst.setString(5, telefone.getUsuario().getEmail());

			pst.execute();

			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterar(Telefone telefone) {

		String sql = "UPDATE telefone SET ddd = ?, numero = ?, tipo = ? where id = ?";

		Connection conexao;
		try {
			conexao = ConexaoJdbc.getConexao();

			PreparedStatement pst = conexao.prepareStatement(sql);

			pst.setInt(1, telefone.getDdd());
			pst.setString(2, telefone.getNumero());
			pst.setString(3, telefone.getTipo());
			pst.setLong(4, telefone.getId());

			pst.execute();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void remover(Telefone telefone) {

		String sql = "DELETE FROM telefone WHERE id = ?";

		Connection conexao;
		try {
			conexao = ConexaoJdbc.getConexao();

			PreparedStatement pst = conexao.prepareStatement(sql);

			pst.setLong(1, telefone.getId());

			pst.execute();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Telefone> listarTelefones() {

		String sql = "SELECT t.ddd, t.numero, t.tipo FROM telefone t";

		List<Telefone> listaTelefones = new ArrayList<Telefone>();

		Connection conexao;
		try {
			conexao = ConexaoJdbc.getConexao();

			PreparedStatement pst = conexao.prepareStatement(sql);

			ResultSet rest = pst.executeQuery();

			while (rest.next()) {

				Telefone telefone = new Telefone();

				telefone.setDdd(rest.getInt("ddd"));
				telefone.setNumero(rest.getString("numero"));
				telefone.setTipo(rest.getString("tipo"));

				listaTelefones.add(telefone);
			}
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaTelefones;
	}

}
