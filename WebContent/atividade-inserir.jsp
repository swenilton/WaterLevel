<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Inserir Atividade - Water Level</title>
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
</script>
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
							<li><a href="repositorio.jsp">Repositório</a></li>
							<li class="active"><a href="atividade.jsp">Atividade</a></li>
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
							src="i${sessionScope.usuarioLogado.foto}" id="perfil"
							width="40px" /><span class="caret"></span></a>
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
					<li><a href="atividade.jsp">Atividade</a></li>
					<li class="active">Inserir</li>
				</ol>
				<!-- titulo -->
				<h1>
					Atividade <small>Inserir</small>
				</h1>
			</div>
			<!-- fim page-header -->
			<form action="/WaterLevel/ctrl" method="POST">
			<input type="hidden" name="acao" value="inserirAtividade" />
				<div class="form-group">
					<label for="nome">Descrição</label> <input type="text"
						class="form-control" id="nome" name="nome" placeholder="Ex: Tomar Banho" required="required"/>
				</div>
				<button type="reset" class="btn btn-warning" />
				Limpar
				</button>
				<button type="submit" class="btn btn-success" />
				Inserir
				</button>
			</form>
			<c:if test="${erros.existeErros}">
				<div id="status" class="alert alert-danger" style="margin-top: 20px;">
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
				<div class="alert alert-success" style="margin-top: 20px;">
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
		</div>
		<!-- fim conteudo -->
	</div>
	<!-- fim container -->
	<footer class="rodape">
		<div id="footer">
			<div class="container">
				<a href="home.jsp">Início</a> | <a href="#">Termos e Condições</a> |
				<a href="#">Ajuda e Suporte</a> | <a href="#">Mapa do site</a> | <a
					href="#">Sobre a Coffee Beans</a> <br />
				<br /> Water Level &copy 2015 - Todos os direitos reservados. <br />
				Desenvolvido por <a href="http://coffeebeansdev.com.br"
					target="_blank"><img src="img/coffee-logo.png"
					alt="Logo Coffee Beans" width="70px"></a> <a
					href="http://facebook.com.br/coffeebeansdev" target="_blank"><img
					src="img/facebook.jpeg" alt="Logo Facebook" width="25px"></a><br />
				<small>Version 1.0</small>
			</div>
		</div>
	</footer>
</body>
</html>
