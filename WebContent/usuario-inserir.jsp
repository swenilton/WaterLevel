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
				$('#fotoPerfil').attr('src', e.target.result).width(150)
						.height(150);
			};
			reader.readAsDataURL(input.files[0]);
		}
	}

	// This is called with the results from from FB.getLoginStatus().
	function statusChangeCallback(response) {
		console.log('statusChangeCallback');
		console.log(response);
		// The response object is returned with a status field that lets the
		// app know the current login status of the person.
		// Full docs on the response object can be found in the documentation
		// for FB.getLoginStatus().
		if (response.status === 'connected') {
			// Logged into your app and Facebook.
			testAPI();
		} else if (response.status === 'not_authorized') {
			// The person is logged into Facebook, but not your app.
			document.getElementById('status').innerHTML = 'Por favor, '
					+ 'logue no aplicativo';
			$('#status').addClass("alert alert-danger");
		} else {
			// The person is not logged into Facebook, so we're not sure if
			// they are logged into this app or not.
			document.getElementById('status').innerHTML = 'Por favor, '
					+ 'logue no aplicativo';
			$('#status').addClass("alert alert-danger");
		}
	}

	// This function is called when someone finishes with the Login
	// Button.  See the onlogin handler attached to it in the sample
	// code below.
	function checkLoginState() {
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}

	window.fbAsyncInit = function() {
		FB.init({
			appId : '1632493973636313',
			cookie : true, // enable cookies to allow the server to access 
			// the session
			xfbml : true, // parse social plugins on this page
			version : 'v2.2' // use version 2.2
		});

		// Now that we've initialized the JavaScript SDK, we call 
		// FB.getLoginStatus().  This function gets the state of the
		// person visiting this page and can return one of three states to
		// the callback you provide.  They can be:
		//
		// 1. Logged into your app ('connected')
		// 2. Logged into Facebook, but not your app ('not_authorized')
		// 3. Not logged into Facebook and can't tell if they are logged into
		//    your app or not.
		//
		// These three cases are handled in the callback function.
	};

	// Load the SDK asynchronously
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/pt_BR/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	// Here we run a very simple test of the Graph API after login is
	// successful.  See statusChangeCallback() for when this call is made.
	function testAPI() {
		console.log('Welcome!  Fetching your information.... ');
		FB.api('/me', function(response) {
			//console.log('Successful login for: '
			//		+ response.name);
			//document.getElementById('status').innerHTML = 'Obrigado por logar, '
			//		+ response.name + '!';
			//$('#usuario').val(response.email);
			//$('#foto').jsp('<img src="https://graph.facebook.com/' + response.first_name + response.last_name + '/picture" alt="'+response.name+'" />')
			//$.post("/WaterLevel/ctrl?acao=loginFacebook", {'email' : response.email}, function(resposta) {});
			$('#form #nome')
					.val(response.name);
			$('#form #email').val(response.email);
			$('#form #login').val(response.first_name);
		});
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
				name="form-inserir-usuario" enctype="multipart/form-data" id="form"
				onsubmit="validarSenha()">
				<input type="hidden" name="acao" value="inserirUsuario" />
				<div id="foto-usuario-form">
					<img src="http://placehold.it/150x150" alt="Imagem do Usuário"
						class="img-circle" id="fotoPerfil" />
					<div class="form-group">
						<label for="foto">Procurar Foto</label> <input type="file"
							accept="image/*" id="foto" name="foto" size="60"
							onchange="preview(foto)" />
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
						<label for="confirmar_senha">Confirmar senha</label> <input
							type="password" class="form-control" id="confirmar_senha"
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
					<input type="reset" class="btn btn-warning" name="limpar"
						value="Limpar" /> <input type="submit" class="btn btn-success"
						name="inserir" value="Inserir" />
					<div class="fb-login-button pull-right">
						<fb:login-button size="large" scope="public_profile,email"
							onlogin="checkLoginState();">Cadastrar com Facebook</fb:login-button>
					</div>
				</div>
			</form>
			<c:if test="${erros.existeErros}">
				<div id="status" class="alert alert-danger"
					style="margin-top: 50px;">
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
	<jsp:include page="rodape.jsp"></jsp:include>
</body>
</html>
