<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Mapa do Site - Water Level</title>
<link rel="shortcut icon" href="img/ico.png" />
<link rel="stylesheet" type="text/css" href="css/estilo2.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.vertical-tabs.css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/ControllerMenu.js"></script>
<script type="text/javascript" src="js/jquery.rwdImageMaps.js"></script>
<script type="text/javascript">
	controllerMenu = new ControllerMenu();
	$(window).scroll(function() {
		controllerMenu.activeScrollTopMenu();
	});
	$(document).ready(function(e) {
		$('img[usemap]').rwdImageMaps();
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
					<li><a href="home.jsp">Inicio</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Cadastros
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="usuario.jsp">Usuário</a></li>
							<li><a href="repositorio.jsp">Repositório</a></li>
							<li><a href="atividade.jsp">Atividade</a></li>
							<li><a href="bomba.jsp">Bomba</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Relatórios
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li class="active"><a href="consumo-periodico.jsp">Consumo
									periódico <span class="sr-only">(current)</span>
							</a></li>
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
					<li><a href="home.jsp">Início</a></li>
					<li class="active">Mapa do site</li>
				</ol>
				<!-- titulo -->
				<h1>
					Mapa do Site <small>Ajuda</small>
				</h1>
			</div>
			<!-- fim page-header -->
<div class="col-md-1"></div>
			<div class="col-md-10">
				<map name="map">
					<area shape="rect" alt="Início" coords="68,
						105, 165, 163"
						href="home.jsp" />
					<area shape="rect" alt="Atividade" coords="0,
						331, 97, 390"
						href="atividade.jsp" />
					<area shape="rect" alt="Usuário" coords="133,
						331, 226, 390"
						href="usuario.jsp" />
					<area shape="rect" alt="Repositório"
						coords="265,
						331, 360, 390" href="repositorio.jsp" />
					<area shape="rect" alt="Bomba" coords="396,
						331, 492, 390"
						href="bomba.jsp" />
					<area shape="rect" alt="Relatório Periódico"
						coords="464,
						208, 561, 267" href="consumo-periodico.jsp" />
					<area shape="rect" alt="Editar Usuário Logado"
						coords="276,
						444, 373, 503" href="#" data-toggle="modal"
						data-target="#alterar-usuario-logado" />
					<area shape="rect" alt="Ver Rank"
						coords="408,
						444, 505, 503" href="#" data-toggle="modal"
						data-target="#ver-rank" />
					<area shape="rect" alt="Ver Gastos"
						coords="540,
						444, 636, 503" href="#" data-toggle="modal"
						data-target="#ver-gastos" />
					<area shape="rect" alt="Sair do Sistema"
						coords="672,
						444, 768, 503"
						href="/WaterLevel/ctrl?acao=sair" />
				</map>
				<img class="img-responsive" alt="Mapa do Site"
					src="img/Mapa do site2.png" usemap="#map" />
			</div>
			<div class="col-md-1"></div>
			<div class="clear"></div>
		</div>
		<!-- fim conteudo -->
	</div>
	<!-- fim container -->
	<jsp:include page="rodape.jsp"></jsp:include>
</body>
</html>
