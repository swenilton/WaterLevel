<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.coffeebeans.fachada.Fachada"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.com.coffeebeans.usuario.Usuario"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Usuário - Water Level</title>
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
		var id = $("input[name='user-selected']:checked").val();
		if (id == null) {
			$('#msg').removeClass().addClass('alert alert-danger');
			$('#msg')
					.html(
							"Nenhum usuário selecionado <button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
									+ "<span aria-hidden='true'>&times;</span>"
									+ "</button>");
		} else {
			if (confirm('Tem certeza que deseja remover?')) {
				$
						.post(
								"ctrl?acao=removerUsuario&id=" + id,
								function(dadosDeResposta) {
									$("#" + id).html('');
									$('#msg').removeClass().addClass(
											'alert alert-success');
									$('#msg')
											.html(
													"Usuário removido. <button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
															+ "<span aria-hidden='true'>&times;</span>"
															+ "</button>");
								});
			}
		}

	}

	function alterar() {
		var id = $("input[name='user-selected']:checked").val();
		if (id == null) {
			$('#msg').removeClass().addClass('alert alert-danger');
			$('#msg')
					.html(
							"Nenhum usuário selecionado <button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
									+ "<span aria-hidden='true'>&times;</span>"
									+ "</button>");
		} else {
			$.post("ctrl?acao=pegarUsuario&id=" + id, function(resposta) {
				var dados = resposta.split(",");
				$('#alterar-usuario-modal').modal('show');
				$('#id').val(id);
				$('.modal #nome').val(dados[0]);
				$('.modal #email').val(dados[1]);
				$('.modal #telefone').val(dados[2]);
				$('.modal #login').val(dados[3]);
				$('.modal #perfil').val(dados[4]);
				$('.modal #ativo').val(dados[5]);
				$('.modal #foto').val(dados[6]);
			})

		}
	}

	function alterarSenha() {
		var id = $("input[name='user-selected']:checked").val();
		if (id == null) {
			$('#msg').removeClass().addClass('alert alert-danger');
			$('#msg')
					.html(
							"Nenhum usuário selecionado <button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
									+ "<span aria-hidden='true'>&times;</span>"
									+ "</button>");
		} else {
			$('#alterar-senha').modal('show');
			$('#id2').val(id);
		}
	}

	function confirmaSenha() {
		if ($('#novaSenha').val == $('#confirmaNovaSenha').val) {
			return true;
		} else {
			$('#msg').removeClass().addClass('alert alert-danger');
			$('#msg')
					.html(
							"Confirmação de senha incorreta <button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
									+ "<span aria-hidden='true'>&times;</span>"
									+ "</button>");
			return false;
		}
	}

	function preview(input) {	
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#fotoPerfil').attr('src', e.target.result).width(150)
						.height(150);
			};
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<%
	if (request.getSession().getAttribute("usuarioLogado") == null) {
		response.sendRedirect("index.jsp");
	}
	Fachada f = Fachada.getInstance();
	List<Usuario> usuarios = f.getUsuarioLista();
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
					<!-- menu cadastros -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Cadastros
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li class="active"><a href="usuario.jsp">Usuário</a></li>
							<li><a href="repositorio.jsp">Repositório</a></li>
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
					<li class="active">Usuário</li>
				</ol>
				<!-- titulo -->
				<h1>
					Usuário <small>Gerenciamento</small>
				</h1>
			</div>
			<!-- fim page-header -->
			<div class="" role="alert" id="msg"></div>
			<div class="panel panel-default panel-user">
				<div class="panel-heading">Lista de Usuários</div>
				<div class="table-responsive table-user" id="tabela">
					<table class="table table-hover" id="table">
						<caption class="hidden">Lista de Usuários</caption>
						<thead>
							<tr>
								<th>#</th>
								<th>ID</th>
								<th>Imagem</th>
								<th>Nome Completo</th>
								<th>Email</th>
								<th>Telefone</th>
								<th>Login</th>
								<th>Perfil</th>
								<th>Ativo</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="usuario" items="<%=usuarios%>">
								<tr id="${usuario.id}">
									<th><input type="radio" name="user-selected"
										value="${usuario.id}" /></th>
									<td>${usuario.id}</td>
									<td class="center"><img src="${usuario.foto}" id="perfil" /></td>
									<td>${usuario.nome}</td>
									<td>${usuario.email}</td>
									<td>${usuario.telefone}</td>
									<td>${usuario.login}</td>
									<td>${usuario.perfil}</td>
									<td>${usuario.ativo}</td>
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
				<a class="btn btn-default" href="usuario-inserir.jsp" role="button">Inserir</a>
				<button type="button" class="btn btn-default" onclick="alterar()">Alterar</button>
				<button type="button" class="btn btn-default" onclick="remover()">Remover</button>
			</div>
			<button type="button" class="btn btn-default"
				onclick="alterarSenha()">Alterar Senha</button>
		</div>
		<!-- fim conteudo -->
	</div>
	<!-- fim container -->
	<!-- Modal Alterar Usuario -->
	<div class="modal fade" id="alterar-usuario-modal" tabindex="-1"
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
					<form class="" method="POST" action="/WaterLevel/ctrl"
						enctype="multipart/form-data" id="form">
						<input type="hidden" name="acao" value="alterarUsuario" /> <input
							type="hidden" name="id" id="id" />
						<div id="foto-usuario" style="text-align: center;">
							<img src="http://placehold.it/150x150" alt="Imagem do Usuário"
								class="img-circle" id="fotoPerfil"/>
							<div class="form-group">
								<label for="foto">Procurar Foto</label> <input type="file"
									accept="image/*" id="foto" name="foto" size="60"
									onchange="preview(foto)" />
							</div>
						</div>
						<div id="dados-usuario">
							<div class="form-group">
								<label for="nome">Nome</label> <input type="text"
									class="form-control" name="nome" id="nome"
									placeholder="Insira seu Nome" />
							</div>
							<div class="form-group">
								<label for="email">Email</label> <input type="email"
									class="form-control" name="email" id="email"
									placeholder="insira seu email" />
							</div>
							<div class="form-group">
								<label for="telefone">Telefone</label> <input type="tel"
									class="form-control" name="telefone" id="telefone"
									placeholder="insira seu número de telefone" />
							</div>
							<div class="form-group">
								<label for="login">Login</label> <input type="text"
									class="form-control" id="login" name="login"
									placeholder="insira seu nome de usuário" />
							</div>
							<div class="form-group col-xs-6">
								<label for="perfil">Perfil</label> <select class="form-control"
									name="perfil" id="perfil">
									<option>Administrador</option>
									<option>Usuario</option>
								</select>
							</div>
							<div class="form-group col-xs-6">
								<label for="ativo">Ativo</label> <select class="form-control"
									name="ativo" id="ativo">
									<option>Sim</option>
									<option>Nao</option>
								</select>
							</div>
							<div class="clear"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-warning"
								data-dismiss="modal">Cancelar</button>
							<input type="submit" class="btn btn-success"
								value="Salvar Alterações" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal Alterar Senha -->
	<div class="modal fade" id="alterar-senha" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Alterar Senha</h4>
				</div>
				<div class="modal-body">
					<form class="" action="/WaterLevel/ctrl" method="post"
						onsubmit="confirmaSenha()">
						<input type="hidden" name="acao" value="alterarSenha" /> <input
							type="hidden" name="id" id="id2" />
						<div class="row">
							<div class="form-group col-xs-6">
								<label for="senhaAtual">Senha Atual</label> <input
									type="password" class="form-control" id="senhaAtual"
									name="senhaAtual" placeholder="insira sua senha"
									required="required" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-6">
								<label for="novaSenha">Nova Senha</label> <input type="password"
									class="form-control" id="novaSenha" name="novaSenha"
									placeholder="insira sua senha" required="required" />
							</div>
							<div class="form-group col-xs-6">
								<label for="confirmaNovaSenha">Confirmar Nova senha</label> <input
									type="password" class="form-control" id="confirmaNovaSenha"
									name="confirmaNovaSenha" placeholder="Confirme sua senha"
									required="required" />
							</div>
						</div>
						<div class="clear"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-warning"
								data-dismiss="modal">Cancelar</button>
							<input type="submit" class="btn btn-success"
								value="Salvar
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
