<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Login - Water Level</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<link rel="shortcut icon" href="img/ico.png">
<script>
	$(document).ready(function() {
		/* Classe padrão dos backgrounds */
		var bgClass = "div.bg";
		/* Prefixo para IDs dos backgrounds */
		var bgPrefix = "background";
		/* Tempo para fade in-out */
		var fade = 5000;
		/* Não precisa alterar estas =P */
		var bgSize = $(bgClass).size();
		var curSize = 1;
		var timerSrc = "";
		/* Mostra apenas o primeiro bg */
		$(bgClass).hide();
		$(bgClass + ':first').show();
		/* Se houver divs com classes BG */
		if (bgSize) {
			/* Inicia o loop dos backgrounds */
			timerSrc = setInterval(function() {
				/* Oculta um fundo e exibe outro */
				$('#' + bgPrefix + curSize).fadeOut(1000);
				curSize = (curSize >= bgSize) ? 1 : curSize + 1;
				$('#' + bgPrefix + curSize).fadeIn(1000);
			}, fade);
		}
	});

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
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into this app.';
		} else {
			// The person is not logged into Facebook, so we're not sure if
			// they are logged into this app or not.
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into Facebook.';
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

		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});

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
		FB
				.api(
						'/me',
						function(response) {
							console.log('Successful login for: '
									+ response.name);
							document.getElementById('status').innerHTML = 'Obrigado por logar, '
									+ response.name + '!';
							$('#usuario').val(response.email);
							//$('#foto').jsp('<img src="https://graph.facebook.com/' + response.first_name + response.last_name + '/picture" alt="'+response.name+'" />')
						});
	}
</script>
<style>
@font-face {
	font-family: 'Futura Bk BT';
	src: url('fonts/futura-book-bt.ttf');
}

.black {
	position: absolute;
	width: 100%;
	height: 100em;
	background: rgba(0, 0, 0, 0.5) repeat center center fixed;
	z-index: 1;
}

html, body {
	width: 100%;
	height: 100%;
	margin: 0;
	overflow: hidden;
	position: relative;
}

.bg {
	z-index: 0;
	position: absolute;
}

div#background1 {
	background: url('img/fundo-login.jpg') no-repeat center center fixed;
	width: 100%;
	height: 100%;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

div#background2 {
	background: url('img/fundo-login2.jpg') no-repeat center center fixed;
	width: 100%;
	height: 100%;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

div#background3 {
	background: url('img/fundo-login3.jpg') no-repeat center center fixed;
	width: 100%;
	height: 100%;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

.relative {
	position: relative;
}

.login {
	width: 400px;
	margin: 5% auto auto 7%;
	background-color: rgba(255, 255, 255, 0.7);
	box-shadow: 0px 0px 5px rgba(255, 255, 255, 0.5);
	border-radius: 10px;
	padding: 20px 30px;
	z-index: 2;
}

.login form input, .login form label.checkbox {
	font-size: 16px;
	height: auto;
	margin-bottom: 5px;
	padding: 7px 9px;
}

.login form label.checkbox {
	margin-top: 0px;
	margin-left: 10px;
}

@media ( max-width : 480px) {
	.login {
		width: 100%;
		height: 350px;
		margin: auto;
		background-color: rgba(255, 255, 255, 0.7);
		box-shadow: 0px 0px 5px rgba(255, 255, 255, 0.5);
		border-radius: 10px;
		padding: 20px 30px;
		z-index: 2;
	}
}

.msg {
	position: absolute;
	bottom: 0;
	width: 100%;
	text-align: right;
	padding-bottom: 7%;
	padding-right: 7%;
}

.msg h1 {
	color: #fff;
	font-size: 30pt;
	font-weight: bold;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

@media ( max-width : 766px) {
	.msg {
		display: none;
	}
	.logo {
		display: none;
	}
}

.logo {
	width: 100%;
	position: absolute;
	top: 0;
	text-align: right;
	padding: 2% 7%;
}

.logo h1 {
	font-family: 'Futura Bk BT';
	color: #fff;
	font-size: 30pt;
}
</style>
</head>
<body>
	<div id="fb-root"></div>
	<div class="black">
		<div class="login">
			<div class="fb-login-button pull-right" style="padding-top: 30px;">
				<fb:login-button scope="public_profile,email"
					onlogin="checkLoginState();">Entrar com Facebook</fb:login-button>
			</div>
			<h1>Entrar</h1>
			<hr />
			<form class="" method="POST" action="/WaterLevel/ctrl" id="form">
				<input type="hidden" name="acao" value="login" />
				<div class="form-group">
					<input type="text" class="form-control" id="usuario" name="usuario"
						placeholder="Usuario" required="required" />
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="senha" name="senha"
						placeholder="Senha" required="required" />
				</div>
				<label class="checkbox pull-left"> <input type="checkbox"
					name="lembre-se">Lembre-se</input>
				</label> <input type="submit" class="btn btn-large btn-primary pull-right"
					value="Entrar" />
			</form>
			<div class="clear" style="margin-top: 50px;"></div>
			<c:if test="${erros.existeErros}">
				<div id="status" class="alert alert-danger"
					style="margin-top: 65px;">
					<button type='button' class='close' data-dismiss='alert'
						aria-label='Close'><span aria-hidden='true'>&times;</span>
					</button>
					<ul>
						<c:forEach var="erro" items="${erros.erros}">
							<li>${erro}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</div>
		<div class="logo">
			<h1>Water Level</h1>
		</div>
	</div>
	<div class="bg" id="background1">
		<div class="msg">
			<h1>Esta é a porta de entrada...</h1>
		</div>
	</div>
	<div class="bg" id="background2">
		<div class="msg">
			<h1>[...] para um sistema que vai revolucionar...</h1>
		</div>
	</div>
	<div class="bg" id="background3">
		<div class="msg">
			<h1>[...] a forma como você economiza água!</h1>
		</div>
	</div>
</body>
</html>