<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="modal fade" id="alterar-usuario" tabindex="-1"
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
					<form class="">
						<div id="foto-usuario" style="text-align: center;">
							<img src="http://placehold.it/150x150" alt="Imagem do Usuário"
								class="img-circle" />
							<div class="form-group">
								<label for="foto">Procurar Foto</label> <input type="file"
									id="foto" />
							</div>
						</div>
						<div id="dados-usuario">
							<div class="form-group">
								<label for="nome">Nome</label> <input type="text"
									class="form-control" id="nome" placeholder="Insira seu Nome"
									value="" />
							</div>
							<div class="form-group">
								<label for="email">Email</label> <span class="add-on"><i
									class="icon-envelope"></i></span> <input type="email"
									class="form-control" id="email" placeholder="insira seu email" />
							</div>
							<div class="form-group">
								<label for="telefone">Telefone</label> <input type="tel"
									class="form-control" id="telefone"
									placeholder="insira seu número de telefone" />
							</div>
							<div class="form-group">
								<label for="login">Login</label> <input type="text"
									class="form-control" id="login"
									placeholder="insira seu nome de usuário" />
							</div>
							<div class="form-group col-xs-6">
								<label for="perfil">Perfil</label> <select class="form-control"
									id="perfil">
									<option>Administrador</option>
									<option>Usuario</option>
								</select>
							</div>
							<div class="form-group col-xs-6">
								<label for="ativo">Ativo</label> <select class="form-control"
									id="ativo">
									<option>Sim</option>
									<option>Não</option>
								</select>
							</div>
							<div class="clear"></div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
					<button type="button" class="btn btn-success">Salvar
						Alterações</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>