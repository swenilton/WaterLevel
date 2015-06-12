<%@page import="java.util.List"%>
<%@page import="br.com.coffeebeans.fachada.Fachada"%>
<%@page import="br.com.coffeebeans.repositorio.Repositorio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Inserir Bomba - Water Level</title>
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
<%
	if (request.getSession().getAttribute("usuarioLogado") == null) {
		response.sendRedirect("index.jsp");
	}
	Fachada f = Fachada.getInstance();
	List<Repositorio> repositorios = f.repositorioListar();
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
					<li><a href="home.jsp">Inicio </a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Cadastros
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="usuario.jsp">Usuário</a></li>
							<li><a href="repositorio.jsp">Repositório</a></li>
							<li><a href="atividade.jsp">Atividade</a></li>
							<li class="active"><a href="bomba.jsp">Bomba</a></li>
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
								data-target="#alterar-usuario">Editar perfil</a></li>
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
					<li><a href="bomba.jsp">Bomba</a></li>
					<li class="active">Inserir</li>
				</ol>
				<!-- titulo -->
				<h1>
					Bomba <small>Inserir</small>
				</h1>
			</div>
			<!-- fim page-header -->
			<form class="" method="POST" action="/WaterLevel/ctrl">
				<input type="hidden" name="acao" value="inserirBomba" />
				<div class="form-group col-md-8">
					<label for="descricao">Descrição</label> <input type="text"
						class="form-control" id="descricao" name="descricao"
						required="required" placeholder="Insira uma descrição" />
				</div>
				<div class="form-group col-md-2">
					<label for="potencia">Potência</label>
					<div class="input-group">
						<input type="number" class="form-control" id="potencia"
							name="potencia" required="required" placeholder="Ex: 1/2" />
						<div class="input-group-addon">CV</div>
					</div>
				</div>
				<div class="form-group col-md-2">
					<label for="vazao">Vazão</label>
					<div class="input-group">
						<input type="number" class="form-control" name="vazao"
							required="required" id="vazao" placeholder="Ex: 120" />
						<div class="input-group-addon">l/min</div>
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="acionamento">Acionamento</label> <select
						class="form-control" id="acionamento" name="acionamento">
						<option>AUTOMATICO</option>
						<option>MANUAL</option>
					</select>
				</div>
				<div class="form-group col-md-4">
					<label for="enche">Enche</label> <select class="form-control"
						id="enche" name="enche">
						<option value="0"></option>
						<c:forEach var="repositorio" items="<%=repositorios%>">
							<option value="${repositorio.id}">${repositorio.id}-
								${repositorio.descricao}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group col-md-4">
					<label for="seca">Seca</label> <select class="form-control"
						id="seca" name="seca">
						<option value="0"></option>
						<c:forEach var="repositorio" items="<%=repositorios%>">
							<option value="${repositorio.id}">${repositorio.id}-
								${repositorio.descricao}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-12">
					<button type="reset" class="btn btn-warning" />
					Limpar
					</button>
					<button type="submit" class="btn btn-success" />
					Inserir
					</button>
				</div>
				<div class="clear"></div>
			</form>
			<c:if test="${erros.existeErros}">
				<div class="alert alert-danger" style="margin-top: 20px;">
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
	<jsp:include page="rodape.jsp"></jsp:include>
</body>
</html>
