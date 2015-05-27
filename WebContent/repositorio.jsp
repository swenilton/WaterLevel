<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.coffeebeans.fachada.Fachada"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.com.coffeebeans.repositorio.Repositorio"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Repositório - Water Level</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="shortcut icon" href="img/ico.png" />
<link rel="stylesheet" type="text/css" href="css/estilo2.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/ControllerMenu.js"></script>
<script type="text/javascript">
	//<![CDATA[ 
	controllerMenu = new ControllerMenu();
	$(window).scroll(function() {
		controllerMenu.activeScrollTopMenu();
	});
	function remover() {
		var id = $("input[name='repositorio-selected']:checked").val();
		if (id == null) {
			$('#msg').removeClass().addClass('alert alert-danger');
			$('#msg')
					.html(
							"Nenhum repositório selecionado <button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
									+ "<span aria-hidden='true'>&times;</span>"
									+ "</button>");
		} else {
			if (confirm('Tem certeza que deseja remover?')) {
				$
						.post(
								"ctrl?acao=removerRepositorio&id=" + id,
								function(dadosDeResposta) {
									$("#" + id).html('');
									$('#msg').removeClass().addClass(
											'alert alert-success');
									$('#msg')
											.html(
													"Repositório removido. <button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
															+ "<span aria-hidden='true'>&times;</span>"
															+ "</button>");
								});
			}
		}

	}
	function alterar() {
		var id = $("input[name='repositorio-selected']:checked").val();
		if (id == null) {
			$('#msg').removeClass().addClass('alert alert-danger');
			$('#msg')
					.html(
							"Nenhum repositorio selecionado <button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
									+ "<span aria-hidden='true'>&times;</span>"
									+ "</button>");
		} else {
			$.post("ctrl?acao=pegarRepositorio&id=" + id, function(resposta) {
				var dados = resposta.split(",");
				$('#alterar-repositorio-modal').modal('show');
				$('#id').val(id);
				$('.modal #nome').val(dados[0]);
				$('.modal #limiteMax').val(dados[1]);
				$('.modal #limiteMin').val(dados[2]);
				$('.modal #produndidade').val(dados[3]);
			})

		}
	}
</script>
<%
	Fachada f = Fachada.getInstance();
	List<Repositorio> repositorios = f.repositorioListar();
%>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="home.jsp"><img
					src="img/Logo-150px.png" id="logo" class="" /></a>
			</div>
			<div class="collapse navbar-collapse" id="navbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="home.jsp">Inicio </a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Cadastros
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="usuario.jsp">Usuário</a></li>
							<li class="active"><a href="repositorio.jsp">Repositório</a></li>
							<li><a href="atividade.jsp">Atividade</a></li>
							<li><a href="bomba.jsp">Bomba</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Relatórios
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="consumo-periodico.jsp">Consumo periódico</a></li>
							<li><a href="#">Gráficos</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						id="usuario-logado" data-toggle="dropdown" role="button"
						aria-expanded="false"> <span class="usuario">${sessionScope.usuarioLogado.nome}</span><img
							src="${sessionScope.usuarioLogado.foto}" id="perfil" width="40px" /><span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" data-toggle="modal"
								data-target="#alterar-usuario-logado">Editar perfil</a></li>
							<li><a href="#" data-toggle="modal"
								data-target="#ver-gastos">Ver gastos</a></li>
							<li><a href="#" data-toggle="modal" data-target="#ver-rank">Ver
									Rank</a></li>
							<li class="divider"></li>
							<li><a href="/WaterLevel/ctrl?acao=sair">Sair</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<!-- fim container -->
	</nav>
	<jsp:include page="modalAlterarUsuario.jsp"></jsp:include>
	<jsp:include page="modalVerGastos.jsp"></jsp:include>
	<jsp:include page="modalVerRank.jsp"></jsp:include>
	<div class="container">
		<div class="conteudo">
			<div class="page-header page-titulo">
				<!-- migalhas de pão -->
				<ol class="breadcrumb migalhas">
					<li><a href="home.jsp">Inicio</a></li>
					<li class="active">Repositório</li>
				</ol>
				<!-- titulo -->
				<h1>
					Repositório <small>Gerenciamento</small>
				</h1>
			</div>
			<!-- fim page-header -->
			<div class="" role="alert" id="msg"></div>
			<div class="panel panel-default panel-user">
				<div class="panel-heading">Lista de Repositórios</div>
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>ID</th>
								<th>Descrição</th>
								<th>Capacidade</th>
								<th>Profundidade</th>
								<th>Modelo</th>
								<th>Limite Min</th>
								<th>Limite Max</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="repositorio" items="<%=repositorios%>">
								<tr id="${repositorio.id}">
									<th><input type="radio" name="repositorio-selected"
										value="${repositorio.id}" /></th>
									<td>${repositorio.id}</td>
									<td>${repositorio.descricao}</td>
									<td>${repositorio.capacidade}L</td>
									<td>${repositorio.profundidade}cm</td>
									<td>${repositorio.limiteMinimo}</td>
									<td>${repositorio.limiteMaximo}</td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- fim div  table -->
			</div>
			<!-- fim painel -->
			<c:if test="${erros.existeErros}">
				<div class="alert alert-danger">
					<button type='button' class='close' data-dismiss='alert'
						aria-label='Close'>
						<span aria-hidden='true'>&times;</span>
					</button>
					<ul>
						<c:forEach var="erro" items="${erros.erros}">
							<li>${erro}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<c:if test="${sucessos.existeSucessos}">
				<div class="alert alert-success">
					<button type='button' class='close' data-dismiss='alert'
						aria-label='Close'>
						<span aria-hidden='true'>&times;</span>
					</button>
					<ul>
						<c:forEach var="sucesso" items="${sucessos.sucessos}">
							<li>${sucesso}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<div class="btn-group" role="group" aria-label="">
				<a class="btn btn-default" href="repositorio-inserir.jsp"
					role="button">Inserir</a>
				<button type="button" class="btn btn-default" onclick="alterar()">Alterar</button>
				<button type="button" class="btn btn-default" onclick="remover()">Remover</button>
			</div>
		</div>
		<!-- fim conteudo -->
	</div>
	<!-- fim container -->
	<!-- Modal Alterar repositorio -->
	<div class="modal fade" id="alterar-repositorio-modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Alterar Usuário</h4>
				</div>
				<div class="modal-body">
					<form class="" method="POST" action="/WaterLevel/ctrl">
					<input type="hidden" name="acao" value="alterarRepositorio" />					
					<input type="hidden" name="id" id="id" />
						<div class="form-group col-md-6">
							<label for="formato">Formato</label> <select class="form-control"
								id="formato" name="formato">
								<option>Circular</option>
								<option>Retangular</option>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="nome">Identificação</label> <input type="text"
								class="form-control" id="nome" name="nome"
								placeholder="Ex: Reservatório subterrâneo" />
						</div>
						<div class="form-group col-md-6">
							<label for="profundidade">Profundidade</label>
							<div class="input-group">
								<input type="text" class="form-control" id="profundidade"
									placeholder="Ex: 120" name="profundidade"/>
								<div class="input-group-addon">cm</div>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="area">Area da Base</label>
							<div class="input-group">
								<input type="text" class="form-control" id="area"
									placeholder="Ex: 1000" name="area"/>
								<div class="input-group-addon">cm</div>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="limiteMin">Limite mínimo</label>
							<div class="input-group">
								<input type="number" class="form-control" id="limiteMin"
									name="limiteMin" required="required" placeholder="Ex: 20" />
								<div class="input-group-addon">L</div>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="limiteMax">Limite máximo</label>
							<div class="input-group">
								<input type="number" class="form-control" id="limiteMax"
									name="limiteMax" required="required" placeholder="Ex: 1800" />
								<div class="input-group-addon">L</div>
							</div>
						</div>
						<div class="clear"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-warning"
								data-dismiss="modal">Cancelar</button>
							<input type="submit" class="btn btn-success" value="Salvar
								Alterações" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<footer class="rodape">
		<div id="footer">
			<div class="container">
				<a href="home.jsp">Início</a> | <a href="#">Termos e Condições</a> |
				<a href="#">Ajuda e Suporte</a> | <a href="#">Mapa do site</a> | <a
					href="#">Sobre a Coffee Beans</a> <br /> <br /> Water Level &copy
				2015 - Todos os direitos reservados. <br /> Desenvolvido por <a
					href="http://coffeebeansdev.com.br" target="_blank"><img
					src="img/coffee-logo.png" alt="Logo Coffee Beans" width="70px"></a>
				<a href="http://facebook.com.br/coffeebeansdev" target="_blank"><img
					src="img/facebook.jpeg" alt="Logo Facebook" width="25px"></a><br />
				<small>Version 1.0</small>
			</div>
		</div>
	</footer>
</body>
</html>
