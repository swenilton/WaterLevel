<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.coffeebeans.acionamento.Acionamento"%>
<%@page import="br.com.coffeebeans.usuario.Usuario"%>
<%@page import="br.com.coffeebeans.atividade.AtividadeRealizada"%>
<%@page import="br.com.coffeebeans.util.Erro"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.coffeebeans.fachada.Fachada"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="pt-BR" />
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
	/*
	 $(function() {
	 setInterval(function() {
	 $.post("ctrl?acao=nivel", function(retorno) {
	 //valor = retorno;
	 geraCaixa1(parseFloat(retorno));
	 });
	 geraCaixa2();
	 }, 2000);
	 });

	 function geraCaixa1(nivel) {
	 var skillBar = $('#caixa1.skill div').siblings().find('.inner');
	 //var skillVal = Math.floor((Math.random() * 100) + 1) + "%";
	 var profundidade = 19;
	 var altura = profundidade - nivel;
	 var percent = (altura / profundidade) * 100;
	 var capacidade = 3.14 * (10 * 10 * profundidade);
	 var qtd = capacidade * ((altura / profundidade) * 100);
	 $('#capacidade').text(capacidade + " ml");
	 $('#qtd').text(qtd.toFixed(2) / 100 + " ml");
	 if (nivel > 0 && percent < 101) {
	 var skillVal = percent.toFixed(2) + "%";
	 $('#progress').html("<h1>" + skillVal + "</h1>");
	 $(skillBar).animate({
	 height : skillVal
	 }, 1000);
	 } else {
	 $('#progress').html("<h1>Aguarde...</h1>");
	 //$('#progress').html("<br /><img src='img/aguarde3.gif' />");
	 $(skillBar).animate({
	 height : 0
	 }, 1000);
	 }
	 }
	 */
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

	function acionamento(ac) {
		if (ac == 'auto') {
			$.post("ctrl?acao=acionamento&ac=auto", function(retorno) {

			});
		} else {
			$.post("ctrl?acao=acionamento&ac=man", function(retorno) {

			});
		}
	}

	function defineLimiteMax() {
		$.post("ctrl?acao=defineLimiteMax&limiteMax=" + $('#limite-max').val(),
				function(retorno) {

				});
	}

	controllerMenu = new ControllerMenu();
	$(window).scroll(function() {
		controllerMenu.activeScrollTopMenu();
	});
</script>
</head>
<%
	Fachada f = Fachada.getInstance();
	List<AtividadeRealizada> ultimasAtividades = new ArrayList<AtividadeRealizada>();
	List<Acionamento> acionamentos = f.acionamentoListar();
	Usuario u = (Usuario) request.getSession().getAttribute(
			"usuarioLogado");
	if (u == null) {
		response.sendRedirect("index.jsp");
	} else {
		ultimasAtividades = f.getUltimasAtividades(u.getId());
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
							src="${sessionScope.usuarioLogado.foto}" id="perfil" /><span
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
			<div id="msg" style="display: none;"></div>
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
												<td class="tde" id="capacidade"></td>
											</tr>
											<tr>
												<td class="tdd">Quantidade de água:</td>
												<td class="tde" id="qtd"></td>
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
												onclick="acionamento('auto');" data-toggle="pill"
												aria-controls="auto" role="tab">Automático</a></li>
											<li role="presentation"><a href="#manual"
												onclick="acionamento('manual');" data-toggle="pill"
												aria-controls="manual" role="tab">Manual</a></li>
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
												<input type="hidden" name="acao" value="defLimiteMax" />
												<div class="form-group form-grupo">
													<label for="limite-max">Limite Max</label> <input
														type="text" class="form-control" id="limite-max"
														placeholder="Litros">
												</div>
												<button type="button" class="btn btn-danger btn-salvar"
													id="liga" onclick="defineLimiteMax();">Ligar Bomba</button>
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
										<c:forEach var="atividade" items="<%=ultimasAtividades%>">
											<tr>
												<td>${atividade.atividade.descricao}</td>
												<td><fmt:formatDate pattern="dd/MM/yyyy - HH:mm:ss"
														value="${atividade.dataHoraInicio}" /></td>
												<td><fmt:formatDate pattern="dd/MM/yyyy - HH:mm:ss"
														value="${atividade.dataHoraFim}" /></td>
												<td><fmt:formatNumber value="${atividade.gasto}"
														minFractionDigits="2" />L</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- fim painel atividades -->
						<a href="#" class="pull-right btn btn-default">Ver mais</a>
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
									<% SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); %>
										<%for(int i = 0; i < acionamentos.size(); i++){ %>
											<tr>
												<td><%=sdf.format(acionamentos.get(i).getDataHoraInicio()) %></td>
												<td><%=sdf.format(acionamentos.get(i).getDataHoraFim()) %></td>
												<%
													Calendar dataInicio = Calendar.getInstance();
											        dataInicio.setTime(acionamentos.get(i).getDataHoraInicio());
											        Calendar dataFinal = Calendar.getInstance();
											        dataFinal.setTime(acionamentos.get(i).getDataHoraFim());
											        long diff = dataFinal.getTimeInMillis() - dataInicio.getTimeInMillis();
											        long hours = (60 * 60 * 1000);
											        long minutos = (60 * 1000);
											        long diffHoras = diff / hours;
											        long diffMinutos = diffHoras / minutos;
											        long diffHorasMinutos = (diff % hours) / (60 * 1000);
											        long diffHorasMinutosSegundos = (diff % minutos) / (1000);
											        Calendar resultado = Calendar.getInstance();
											        resultado.set(Calendar.HOUR_OF_DAY, Integer.parseInt(Long.toString(diffHoras)));
											        resultado.set(Calendar.MINUTE, Integer.parseInt(Long.toString(diffHorasMinutos)));
											        resultado.set(Calendar.SECOND, Integer.parseInt(Long.toString(diffHorasMinutosSegundos)));
												%>
												<td><%=sdf.format(new Date(resultado.getTimeInMillis()))%></td>
											</tr>
											<%} %>
									</tbody>
								</table>
							</div>
						</div>
						<!-- fim painel atividades -->
						<a href="#" class="pull-right btn btn-default">Ver mais</a>
					</div>
					<!-- fim coluna -->
				</div>
				<!-- fim row -->
			</section>
		</div>
		<!-- fim conteudo -->
	</div>
	<!-- fim container -->
	<jsp:include page="rodape.jsp"></jsp:include>
</body>
</html>
