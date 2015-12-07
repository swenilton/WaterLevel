<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- modal alterar dados usuario logado -->
	<div class="modal fade" id="alterar-usuario-logado" tabindex="-1"
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
					<form class="" method="POST" action="/WaterLevel/ctrl" enctype="multipart/form-data">
						<input type="hidden" name="acao" value="alterarUsuario" /> <input
							type="hidden" name="id" value="${sessionScope.usuarioLogado.id}" />
						<div id="foto-usuario" style="text-align: center;">
							<img src="ctrl?acao=getFoto&id=${sessionScope.usuarioLogado.id}" alt="Imagem do Usuário"
								class="img-circle" width="150" height="150" />
							<div class="form-group">
								<label for="foto">Procurar Foto</label> <input type="file"
									name="foto" id="foto"/>
							</div>
						</div>
						<div id="dados-usuario">
							<div class="form-group">
								<label for="nome">Nome</label> <input type="text"
									class="form-control" id="nome" name="nome"
									placeholder="Insira seu Nome"
									value="${sessionScope.usuarioLogado.nome}" />
							</div>
							<div class="form-group">
								<label for="email">Email</label> <span class="add-on"><i
									class="icon-envelope"></i></span> <input type="email"
									class="form-control" id="email" name="email"
									placeholder="insira seu email"
									value="${sessionScope.usuarioLogado.email}" />
							</div>
							<div class="form-group">
								<label for="telefone">Telefone</label> <input type="tel"
									class="form-control" id="telefone" name="telefone"
									placeholder="insira seu número de telefone"
									value="${sessionScope.usuarioLogado.telefone}" />
							</div>
							<div class="form-group">
								<label for="login">Login</label> <input type="text"
									class="form-control" id="login" name="login"
									placeholder="insira seu nome de usuário"
									value="${sessionScope.usuarioLogado.login}" />
							</div>
							<div class="form-group col-xs-6">
								<label for="perfil">Perfil</label> <select class="form-control"
									id="perfil" name="perfil"
									value="${sessionScope.usuarioLogado.perfil}">
									<option>Administrador</option>
									<option>Usuario</option>
								</select>
							</div>
							<div class="form-group col-xs-6">
								<label for="ativo">Ativo</label> <select class="form-control"
									id="ativo" name="ativo"
									value="${sessionScope.usuarioLogado.ativo}">
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
	<!-- fim modal alterar dados usuario logado -->
</body>
</html>