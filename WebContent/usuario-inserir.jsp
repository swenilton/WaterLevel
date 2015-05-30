<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8" />
<title>Inserir Usuário - Water Level</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	function preview(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#fotoPerfil').attr('src', e.target.result).width(150).height(
						150);
			};
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<style>
#foto-usuario-form {
	width: 30%;
	height: 300px;
	display: block;
	float: left;
	text-align: center;
}

#dados-usuario-form {
	width: 60%;
	height: 100%;
	margin-left: 40%;
	display: block;
}

#dados-usuario-form .form-esquerda {
	display: block;
	float: left;
	width: 45%;
}

#dados-usuario-form .form-direita {
	display: block;
	margin-left: 50%;
	width: 45%;
}

#dados-usuario select {
	width: 70%;
}
</style>
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
					<li><a href="home.jsp">Início </a></li>
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
						aria-expanded="false"> <span class="usuario">${sessionScope.usuarioLogado.nome}</span><img src="${sessionScope.usuarioLogado.nome}" id="perfil" width="40px" /><span
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
					<li><a href="usuario.jsp">Usuário</a></li>
					<li class="active">Inserir</li>
				</ol>
				<!-- titulo -->
				<h1>
					Usuário <small>Inserir</small>
				</h1>
			</div>
			<!-- fim page-header -->
			<form action="/WaterLevel/ctrl" method="POST"
				name="form-inserir-usuario" enctype="multipart/form-data" id="form">
				<input type="hidden" name="acao" value="inserirUsuario" />
				<div id="foto-usuario-form">
					<img src="http://placehold.it/150x150" alt="Imagem do Usuário"
						class="img-circle" id="fotoPerfil" />
					<div class="form-group">
						<label for="foto">Procurar Foto</label> <input type="file"
							accept="image/*" id="foto" name="foto" size="60" onchange="preview(foto)" />
					</div>
				</div>
				<div id="dados-usuario-form">
					<div class="form-group">
						<label for="nome">Nome</label> <input type="text"
							class="form-control" id="nome" name="nome"
							placeholder="EX: João da Silva" required="required" />
					</div>
					<div class="form-group">
						<label for="email">Email</label> <span class="add-on"><i
							class="icon-envelope"></i></span> <input type="email"
							class="form-control" id="email" name="email"
							placeholder="Ex: joao@ex.com" required="required" />
					</div>
					<div class="form-group">
						<label for="telefone">Telefone</label> <input type="tel"
							class="form-control" id="telefone" name="telefone"
							placeholder="Ex: (xx) 1234-5678"
							pattern="\([0-9]{2}\)[\s][0-9]{4,5}-[0-9]{4}" />
					</div>
					<div class="form-group">
						<label for="login">Login</label> <input type="text"
							class="form-control" id="login" name="login"
							placeholder="Ex: joao" required="required" />
					</div>
					<div class="form-group form-esquerda">
						<label for="senha">Senha</label> <input type="password"
							class="form-control" id="senha" name="senha"
							placeholder="insira sua senha" required="required" />
					</div>
					<div class="form-group form-direita">
						<label for="confirma-senha">Confirmar senha</label> <input
							type="password" class="form-control" id="confirma-senha"
							name="confirma-senha" placeholder="Confirme sua senha"
							required="required" />
					</div>
					<div class="form-group form-esquerda">
						<label for="perfil">Perfil</label> <select class="form-control"
							id="perfil" name="perfil">
							<option>Administrador</option>
							<option>Usuario</option>
						</select>
					</div>
					<div class="form-group form-direita">
						<label for="ativo">Ativo</label> <select class="form-control"
							id="ativo" name="ativo">
							<option>Sim</option>
							<option>Nao</option>
						</select>
					</div>
					<button type="reset" class="btn btn-warning" />
					Limpar
					</button>
					<input type="submit" class="btn btn-success" name="inserir" value="Inserir"/>
				</div>
			</form>
			<c:if test="${erros.existeErros}">
				<div id="status" class="alert alert-danger">
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
				<div class="alert alert-success" style="margin-top: 50px;">
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
