package br.com.coffeebeans.atividade;

import java.sql.Connection;

import br.com.coffeebeans.util.Conexao;

public class AtividadeDAO {
	
	private String sistema = "mysql";
	private Connection conn;
	
	public AtividadeDAO() throws Exception {
		this.conn = new Conexao().conectar(sistema);
	}

}
