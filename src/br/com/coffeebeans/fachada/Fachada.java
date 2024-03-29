package br.com.coffeebeans.fachada;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.coffeebeans.acionamento.Acionamento;
import br.com.coffeebeans.acionamento.ControladorAcionamento;
import br.com.coffeebeans.atividade.Atividade;
import br.com.coffeebeans.atividade.AtividadeRealizada;
import br.com.coffeebeans.atividade.ControladorAtividade;
import br.com.coffeebeans.atividade.ControladorAtividadeRealizada;
import br.com.coffeebeans.bomba.Bomba;
import br.com.coffeebeans.bomba.ControladorBomba;
import br.com.coffeebeans.exception.AcionamentoJaExistenteException;
import br.com.coffeebeans.exception.AcionamentoNaoEncontradoException;
import br.com.coffeebeans.exception.AtividadeJaExistenteException;
import br.com.coffeebeans.exception.AtividadeNaoEncontradaException;
import br.com.coffeebeans.exception.AtividadeRealizadaJaExistenteException;
import br.com.coffeebeans.exception.BombaJaExistenteException;
import br.com.coffeebeans.exception.BombaNaoEncontradaException;
import br.com.coffeebeans.exception.DAOException;
import br.com.coffeebeans.exception.EmailJaExistenteException;
import br.com.coffeebeans.exception.ListaVaziaException;
import br.com.coffeebeans.exception.PermissaoException;
import br.com.coffeebeans.exception.RepositorioException;
import br.com.coffeebeans.exception.RepositorioJaExistenteException;
import br.com.coffeebeans.exception.RepositorioNaoEncontradoException;
import br.com.coffeebeans.exception.UsuarioInativoException;
import br.com.coffeebeans.exception.UsuarioJaExistenteException;
import br.com.coffeebeans.exception.UsuarioNaoEncontradoException;
import br.com.coffeebeans.exception.ViolacaoChaveEstrangeiraException;
import br.com.coffeebeans.leitura.ControladorLeitura;
import br.com.coffeebeans.repositorio.ControladorRepositorio;
import br.com.coffeebeans.repositorio.Repositorio;
import br.com.coffeebeans.usuario.ControladorUsuario;
import br.com.coffeebeans.usuario.Usuario;

public class Fachada {
	private static Fachada instance = null;
	ControladorRepositorio controladorRepositorio;
	ControladorUsuario controladorUsuario;
	ControladorBomba controladorBomba;
	ControladorAcionamento controladorAcionamento;
	ControladorAtividade controladorAtividade;
	ControladorAtividadeRealizada controladorAtividadeRealizada;
	ControladorLeitura controladorLeitura;

	private Fachada() throws Exception {
		this.controladorRepositorio = new ControladorRepositorio();
		this.controladorUsuario = new ControladorUsuario();
		this.controladorBomba = new ControladorBomba();
		this.controladorAcionamento = new ControladorAcionamento();
		this.controladorAtividade = new ControladorAtividade();
		this.controladorAtividadeRealizada = new ControladorAtividadeRealizada();
		this.controladorLeitura = new ControladorLeitura();
	}

	public static Fachada getInstance() throws Exception {
		if (Fachada.instance == null) {
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}

	public <E> void cadastrar(E element) throws SQLException,
			RepositorioJaExistenteException, RepositorioNaoEncontradoException,
			UsuarioJaExistenteException, UsuarioNaoEncontradoException,
			RepositorioException, BombaJaExistenteException,
			BombaNaoEncontradaException, ViolacaoChaveEstrangeiraException,
			AcionamentoNaoEncontradoException, AcionamentoJaExistenteException,
			AtividadeJaExistenteException, AtividadeNaoEncontradaException,
			DAOException, EmailJaExistenteException, PermissaoException, AtividadeRealizadaJaExistenteException {
		if (element instanceof Repositorio) {
			controladorRepositorio.cadastrar((Repositorio) element);
		} else if (element instanceof Usuario) {
			controladorUsuario.cadastrar((Usuario) element);
		} else if (element instanceof Bomba) {
			controladorBomba.cadastrar((Bomba) element);
		} else if (element instanceof Acionamento) {
			controladorAcionamento.cadastrar((Acionamento) element);
		} else if (element instanceof Atividade) {
			controladorAtividade.cadastrar((Atividade) element);
		} else if (element instanceof AtividadeRealizada) {
			controladorAtividadeRealizada
					.cadastrar((AtividadeRealizada) element);
		}

	}

	public <E> void atualizar(E element) throws SQLException,
			RepositorioNaoEncontradoException, UsuarioNaoEncontradoException,
			RepositorioException, DAOException, BombaNaoEncontradaException,
			AcionamentoNaoEncontradoException, AcionamentoJaExistenteException,
			AtividadeNaoEncontradaException, DAOException,
			AtividadeJaExistenteException, PermissaoException {
		if (element instanceof Repositorio) {
			controladorRepositorio.atualizar((Repositorio) element);
		} else if (element instanceof Usuario) {
			controladorUsuario.atualizar((Usuario) element);
		} else if (element instanceof Bomba) {
			controladorBomba.atualizar((Bomba) element);
		} else if (element instanceof Acionamento) {
			controladorAcionamento.atualizar((Acionamento) element);
		} else if (element instanceof Atividade) {
			controladorAtividade.atualizar((Atividade) element);
		} else if (element instanceof AtividadeRealizada) {
			controladorAtividadeRealizada
					.atualizar((AtividadeRealizada) element);
		}

	}

	public void atividadeRealizadaRemover(int id)
			throws AtividadeNaoEncontradaException, SQLException,
			RepositorioException {
		controladorAtividadeRealizada.excluir(id);
	}

	public void acionamentoRemover(int id) throws SQLException,
			AcionamentoNaoEncontradoException, RepositorioException {
		controladorAcionamento.excluir(id);
	}

	public void bombaRemover(int id) throws SQLException,
			BombaNaoEncontradaException {
		controladorBomba.remover(id);
	}

	public void repositorioRemover(int id) throws SQLException,
			RepositorioNaoEncontradoException {
		controladorRepositorio.remover(id);
	}

	public void usuarioRemover(int id) throws SQLException,
			UsuarioNaoEncontradoException, DAOException, PermissaoException {
		controladorUsuario.remover(id);
	}

	public void atividadeRemover(int id) throws SQLException,
			AtividadeNaoEncontradaException, DAOException {
		controladorAtividade.remover(id);
	}

	public List<AtividadeRealizada> atividadeRealizadaListar()
			throws SQLException, ListaVaziaException, RepositorioException {
		return controladorAtividadeRealizada.listar();
	}

	public List<AtividadeRealizada> atividadeRealizadaListar(int id)
			throws SQLException, ListaVaziaException, RepositorioException {
		return controladorAtividadeRealizada.listar(id);
	}

	public List<Acionamento> acionamentoListar() throws SQLException,
			ListaVaziaException, RepositorioException {
		return controladorAcionamento.listar();
	}

	public List<Acionamento> getUltimosAcionamentos() throws SQLException,
			ListaVaziaException, RepositorioException {
		return controladorAcionamento.getUltimosAcionamentos();
	}

	public List<Bomba> bombaListar() throws SQLException,
			ListaVaziaException {
		return controladorBomba.listar();
	}

	public List<Repositorio> repositorioListar() throws SQLException,
			RepositorioException {
		return controladorRepositorio.listar();

	}

	public List<Atividade> atividadeListar() throws SQLException, DAOException {
		return controladorAtividade.listar();
	}

	public List<Usuario> getUsuarioLista() throws SQLException, DAOException {
		return controladorUsuario.getLista();

	}
	
	public List<AtividadeRealizada> atividadeRealizadaProcurar(String descricao)
			throws SQLException, AtividadeNaoEncontradaException,
			RepositorioException {
		return controladorAtividadeRealizada.procurar(descricao);
	}

	public AtividadeRealizada atividadeRealizadaProcurar(int id)
			throws SQLException, AtividadeNaoEncontradaException,
			RepositorioException {
		return controladorAtividadeRealizada.procurar(id);
	}

	public Acionamento acionamentoProcurar(int id)
			throws AcionamentoNaoEncontradoException, SQLException,
			RepositorioException {
		return controladorAcionamento.procurar(id);
	}

	public Bomba bombaProcurar(int id) throws SQLException,
			BombaNaoEncontradaException {
		return controladorBomba.procurar(id);
	}

	public Bomba bombaProcurarPorRepositorio(int idRepositorio)
			throws SQLException, BombaNaoEncontradaException {
		return controladorBomba.procurarPorRepositorio(idRepositorio);
	}

	public Repositorio repositorioProcurar(int id) throws SQLException,
			RepositorioNaoEncontradoException {
		return controladorRepositorio.procurar(id);
	}

	public Repositorio repositorioProcurar(String descricao)
			throws SQLException, RepositorioNaoEncontradoException {

		return controladorRepositorio.procurar(descricao);

	}

	public Usuario usuarioProcurar(int id) throws SQLException,
			UsuarioNaoEncontradoException, DAOException {
		return controladorUsuario.procurar(id);
	}

	public Atividade atividadeProcurar(int id) throws SQLException,
			AtividadeNaoEncontradaException, DAOException {
		return controladorAtividade.procurar(id);
	}

	public boolean loginFacebook(String email) throws DAOException,
			SQLException {
		return controladorUsuario.loginFacebook(email);
	}

	public void alterarSenhaUsuario(int id, String senha) throws SQLException,
			UsuarioNaoEncontradoException, DAOException {
		controladorUsuario.alterarSenha(id, senha);
	}

	public boolean login(String usuario, String senha)
			throws UsuarioInativoException, DAOException, SQLException {
		return controladorUsuario.login(usuario, senha);
	}

	public List<AtividadeRealizada> getUltimasAtividades()
			throws RepositorioException, SQLException {
		return controladorAtividadeRealizada.getUltimasAtividades();
	}

	public boolean existeAtividadeRealizada(int id_usuario, int id_atividade,
			Date dataHoraInicio, Date dataHoraFim) throws SQLException,
			DAOException {
		return controladorAtividadeRealizada.existe(id_usuario,id_atividade,dataHoraInicio,dataHoraFim);
	}

	public boolean existeUsuario(String login) throws SQLException,
			DAOException {
		return controladorUsuario.existe(login);
	}

	public boolean existeEmail(String email) throws SQLException, DAOException {
		return controladorUsuario.existeEmail(email);
	}

	public String md5(String senha) throws DAOException {
		return controladorUsuario.md5(senha);
	}

	public boolean existeAtividade(String descricao) throws SQLException,
			DAOException {
		return controladorAtividade.existe(descricao);
	}
	public Acionamento acionamentoProcurarIni(Date date3, Date date4)
			throws SQLException, AcionamentoNaoEncontradoException,
			RepositorioException {

	return controladorAcionamento.procurarIni(date3, date4);	
	}
	
	public Acionamento acionamentoProcurarFim(Date date3, Date date4)
			throws SQLException, AcionamentoNaoEncontradoException,
			RepositorioException {

	return controladorAcionamento.procurarFim(date3, date4);	
	}

	public Bomba bombaProcurar(String descricao) throws SQLException, BombaNaoEncontradaException {
		return controladorBomba.procurar(descricao);
	}
	
	public Double getUltimaLeitura(int idRepositorio) throws SQLException, DAOException{
		return controladorLeitura.getUltimaLeitura(idRepositorio);
	}

}
