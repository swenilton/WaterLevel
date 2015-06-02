<%@page import="br.com.coffeebeans.util.Erro"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.coffeebeans.fachada.Fachada"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Inicio - Water Level</title>
<link rel="shortcut icon" href="img/ico.png" />
<link rel="stylesheet" type="text/css" href="css/estilo2.css" />
<link rel="stylesheet" type="text/css" href="css/caixa2.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/toggle.js"></script>
<script type="text/javascript" src="js/ControllerMenu.js"></script>
<script type="text/javascript">
	$(function() {
		$('#caixa1.skill div').load('.inner', geraCaixa1());
		$('#caixa2.skill div').load('.inner', geraCaixa2());
	});

	$(function() {
		setInterval(function() {
			geraCaixa1();
			geraCaixa2();
		}, 2000);
	});

	function geraCaixa1() {
		var skillBar = $('#caixa1.skill div').siblings().find('.inner');
		var skillVal = Math.floor((Math.random() * 100) + 1) + "%";
		document.getElementById("progress").innerHTML = "<h1>" + skillVal
				+ "</h1>";
		$(skillBar).animate({
			height : skillVal
		}, 1000);
	}

	function geraCaixa2() {
		var skillBar = $('#caixa2.skill div').siblings().find('.inner');
		var skillVal = Math.floor((Math.random() * 100) + 1) + "%";
		document.getElementById("progress2").innerHTML = "<h1>" + skillVal
				+ "</h1>";
		$(skillBar).animate({
			height : skillVal
		}, 1000);
	}

	$().ready(function() {
		$("#liga").click(function() {
			$(this).toogleText({
				type : "html",
				text : [ "Desligar Bomba", "Ligar Bomba" ]
			});
		});
	});

	controllerMenu = new ControllerMenu();
	$(window).scroll(function() {
		controllerMenu.activeScrollTopMenu();
	});
</script>
</head>
<%
	if (request.getSession().getAttribute("usuarioLogado") == null) {
		response.sendRedirect("index.jsp");
	}
%>
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
					<li class="active"><a href="home.jsp">Inicio <span
							class="sr-only">(current)</span></a></li>
					<!-- menu cadastros -->
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
					<!-- menu relatorios -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Relatórios
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="consumo-periodico.jsp">Consumo periódico</a></li>
							<li><a href="#">Gráficos</a></li>
						</ul></li>
					<!-- usuario logado -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						id="usuario-logado" data-toggle="dropdown" role="button"
						aria-expanded="false"> <span class="usuario">${sessionScope.usuarioLogado.nome}</span><img
							src="${sessionScope.usuarioLogado.foto}" id="perfil" width="40px" height="40px" /><span
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
					<li class="active">Inicio</li>
					<!--<li><a href="#">Teste</a></li>-->
				</ol>
				<!-- titulo -->
				<h1>
					Resumo Diário <small>Monitoramento</small>
				</h1>
			</div>
			<!-- abas -->
			<ul class="nav nav-tabs grupo-abas">
				<li role="presentation" class="active"><a href="#caixa1"
					data-toggle="tab" aria-controls="caixa1" role="tab">Caixa 1</a></li>
				<li role="presentation"><a href="#caixa2" data-toggle="tab"
					aria-controls="caixa2" role="tab">Caixa 2</a></li>
			</ul>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane fade in active" id="caixa1">
					<section class="caixa">
						<div class="row">
							<div class="col-md-6">
								<div class='skill' id="caixa1">
									<div class='outer'>
										<div class='inner'>
											<div></div>
										</div>
										<div class="progress" id="progress"></div>
									</div>
									<div id='caixa'>
										<img src='img/caixa-800px.png' class="img-responsive"
											alt="Imagem ilustrativa do nivél de água" />
									</div>
								</div>
								<!-- fim skill -->
							</div>
							<div class="col-md-6">
								<div class="panel panel-default informacoes">
									<div class="panel-heading">Informações em tempo real</div>
									<div class="table-responsive">
										<table class="table">
											<tr>
												<td class="tdd">Repositorio:</td>
												<td class="tde">Caixa Superior</td>
											</tr>
											<tr>
												<td class="tdd">Capacidade:</td>
												<td class="tde">2.000 L</td>
											</tr>
											<tr>
												<td class="tdd">Quantidade de água:</td>
												<td class="tde">850 L</td>
											</tr>
											<tr>
												<td class="tdd">Gasto Hoje:</td>
												<td class="tde">250 L</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="panel panel-default bomba">
									<div class="panel-heading">
										Acionamento da bomba
										<ul class="nav nav-pills pull-right" id="tab">
											<li role="presentation" class="active"><a href="#auto"
												data-toggle="pill" aria-controls="auto" role="tab">Automático</a></li>
											<li role="presentation"><a href="#manual"
												data-toggle="pill" aria-controls="manual" role="tab">Manual</a></li>
										</ul>
									</div>
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane fade in active" id="auto">
											<form class="form-inline">
												<div class="form-group form-grupo">
													<label for="limite-max">Limite Max</label> <input
														type="text" class="form-control" id="limite-max"
														placeholder="Litros">
												</div>
												<div class="form-group form-grupo">
													<label for="limite-min">Limite Min </label> <input
														type="text" class="form-control" id="limite-min"
														placeholder="Litros">
												</div>
												<button type="submit" class="btn btn-success btn-salvar">Salvar</button>
											</form>
										</div>
										<div role="tabpanel" class="tab-pane fade" id="manual">
											<form class="form-inline">
												<div class="form-group form-grupo">
													<label for="limite-max">Limite Max</label> <input
														type="text" class="form-control" id="limite-max"
														placeholder="Litros">
												</div>
												<button type="button" class="btn btn-danger btn-salvar"
													id="liga">Ligar Bomba</button>
											</form>
										</div>
									</div>
								</div>
								<!-- fim painel bomba -->
							</div>
							<!-- fim coluna -->
						</div>
						<!-- fim row -->
					</section>
					<!-- fim section caixa -->
				</div>
				<!-- fim tab1 -->
				<div role="tabpanel" class="tab-pane fade" id="caixa2">
					<section class="caixa">
						<div class="row">
							<div class="col-md-6">
								<div class='skill' id="caixa2">
									<div class='outer'>
										<div class='inner'>
											<div></div>
										</div>
										<div class="progress" id="progress2"></div>
									</div>
									<div id='caixa'>
										<img src='img/caixa-800px.png' class="img-responsive"
											alt="Imagem ilustrativa do nivél de água" />
									</div>
								</div>
								<!-- fim skill -->
							</div>
							<div class="col-md-6">
								<div class="panel panel-default informacoes">
									<div class="panel-heading">Informações em tempo real</div>
									<div class="table-responsive">
										<table class="table">
											<tr>
												<td class="tdd">Repositorio:</td>
												<td class="tde">Caixa Subterrânea</td>
											</tr>
											<tr>
												<td class="tdd">Capacidade:</td>
												<td class="tde">8.000 L</td>
											</tr>
											<tr>
												<td class="tdd">Quantidade de água:</td>
												<td class="tde">5.000 L</td>
											</tr>
											<tr>
												<td class="tdd">Gasto Hoje:</td>
												<td class="tde">500 L</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!-- fim row -->
					</section>
					<!-- fim section caixa -->
				</div>
				<!-- fim tab 2 -->
			</div>
			<!-- fim tab content -->
			<section class="ultimas-atividades">
				<div class="row">
					<div class="col-md-6">
						<div class="page-header page-titulo">
							<!-- titulo -->
							<h2>
								Últimas Atividades <small>Resumo</small>
							</h2>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">Atividades recentes</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Atividade</th>
											<th>Início</th>
											<th>Fim</th>
											<th>Gasto</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Escovar os Dentes</td>
											<td>10/04/2015 - 17:30</td>
											<td>10/04/2015 - 17:35</td>
											<td>1,75 L</td>
										</tr>
										<tr>
											<td>Tomar Banho</td>
											<td>10/04/2015 - 17:36</td>
											<td>10/04/2015 - 17:48</td>
											<td>13,15 L</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<!-- fim painel atividades -->
					</div>
					<!-- fim coluna -->
					<div class="col-md-6">
						<div class="page-header page-titulo">
							<!-- titulo -->
							<h2>
								Últimos Acionamentos <small>Resumo</small>
							</h2>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">Acionamentos Recentes</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Início</th>
											<th>Fim</th>
											<th>Tempo</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>10/04/2015 - 08:35</td>
											<td>10/04/2015 - 10:20</td>
											<td>1:45 hrs</td>
										</tr>
										<tr>
											<td>08/04/2015 - 14:30</td>
											<td>08/04/2015 - 15:30</td>
											<td>1:00 hrs</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<!-- fim painel atividades -->
					</div>
					<!-- fim coluna -->
				</div>
				<!-- fim row -->
			</section>
		</div>
		<!-- fim conteudo -->
	</div>
	<!-- fim container -->
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
	</footer>
</body>
</html>
