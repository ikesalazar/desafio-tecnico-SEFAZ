package dao;

import java.util.List;

import entities.Telefone;

public interface TelefoneDao {

	public void incluir(Telefone telefone);

	public void alterar(Telefone telefone);

	public void remover(Telefone telefone);

	public List<Telefone> listarTelefones(); 
}
