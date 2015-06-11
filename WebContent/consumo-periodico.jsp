<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Relatórios - Water Level</title>
<link rel="shortcut icon" href="img/ico.png" />
<link rel="stylesheet" type="text/css" href="css/estilo2.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.vertical-tabs.css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/ControllerMenu.js"></script>
<script type="text/javascript">
	controllerMenu = new ControllerMenu();
	$(window).scroll(function() {
		controllerMenu.activeScrollTopMenu();
	});
</script>
<style type="text/css">
.relatorio {
	margin-top: 30px;
	text-align: center;
}

.resultado {
	margin-top: 30px;
}
</style>
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
					<li class="active">Consumo Periódico</li>
				</ol>
				<!-- titulo -->
				<h1>
					Relatórios <small>Consumo</small>
				</h1>
			</div>
			<!-- fim page-header -->
			<!-- Nav tabs -->
			<ul class="nav nav-tabs nav-justified">
				<li role="presentation" class="active"><a href="#hora"
					data-toggle="tab" aria-controls="hora" role="tab">Horario</a></li>
				<li role="presentation"><a href="#diario" data-toggle="tab"
					aria-controls="diario" role="tab">Diario</a></li>
				<li role="presentation"><a href="#mensal" data-toggle="tab"
					aria-controls="mensal" role="tab">Mensal</a></li>
			</ul>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane fade in active" id="hora">
					<section class="relatorio">
						<form class="form-inline">
							<div class="form-group">
								<label for="hora">Data e Hora Inicial</label> <input
									class="form-control" type="datetime-local" name="hora-inicial"
									id="hora-inicial" />
							</div>
							<div class="form-group">
								<label for="hora">Data e Hora Final</label> <input
									class="form-control" type="datetime-local" name="hora-final"
									id="hora-final" />
							</div>
							<input type="submit" class="btn btn-default" value="Ok" />
						</form>
					</section>
					<section class="resultado">
						<div class="panel panel-default">
							<div class="panel-heading">Resultado</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Data Hora (de/até)</th>
											<th>Consumo</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>01/05/2015 12:00 a 13:00</td>
											<td>10 L</td>
										</tr>
										<tr>
											<td>01/05/2015 13:00 a 14:00</td>
											<td>5 L</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<!-- fim painel atividades -->
					</section>
				</div>
				<!-- fim tab 1 -->
				<div role="tabpanel" class="tab-pane" id="diario">
					<section class="relatorio">
						<form class="form-inline">
							<div class="form-group">
								<label for="data-inicio">Data de Início</label> <input
									class="form-control" type="date" name="data-inicio"
									id="data-inicio" />
							</div>
							<div class="form-group">
								<label for="data-fim">Data de Fim</label> <input
									class="form-control" type="date" name="data-fim" id="data-fim" />
							</div>
							<input type="submit" class="btn btn-default" value="Ok" />
						</form>
					</section>
					<section class="resultado">
						<div class="panel panel-default">
							<div class="panel-heading">Resultado</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Dia</th>
											<th>Consumo</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>01/05/2015</td>
											<td>245 L</td>
										</tr>
										<tr>
											<td>02/05/2015</td>
											<td>145 L</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</section>
				</div>
				<!-- fim tab 2 -->
				<div role="tabpanel" class="tab-pane fade" id="mensal">
					<section class="relatorio">
						<form class="form-inline">
							<div class="form-group">
								<label for="mes">Mês</label> <input class="form-control"
									type="month" name="mes" id="mes" />
							</div>
							<input type="submit" class="btn btn-default" value="Ok" />
						</form>
					</section>
					<section class="resultado">
						<div class="panel panel-default">
							<div class="panel-heading">Resultado</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Mês</th>
											<th>Consumo</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>04/2015</td>
											<td>45.585 L</td>
										</tr>
										<tr>
											<td>05/2015</td>
											<td>345 L</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<!-- fim painel atividades -->
					</section>
				</div>
				<!-- fim tab 3 -->
			</div>
			<!-- fim tab content -->
			<button class="btn btn-default" type="button">Salvar</button>
			<button class="btn btn-default" type="button">Imprimir</button>
			<button class="btn btn-default" type="button">Enviar</button>
		</div>
		<!-- fim conteudo -->
	</div>
	<!-- fim container -->
	<jsp:include page="rodape.jsp"></jsp:include>
</body>
</html>
