<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<%@ page pageEncoding="UTF-8" %>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<title>Bomba - Water Level</title>
<link rel="shortcut icon" href="img/ico.png"/>
<link rel="stylesheet" type="text/css" href="css/estilo2.css"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/ControllerMenu.js"></script>
<script type="text/javascript">//<![CDATA[ 
controllerMenu = new ControllerMenu();
$( window ).scroll(function() {
    controllerMenu.activeScrollTopMenu();
});
</script>
</head>
<body>    
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="home.jsp"><img src="img/Logo-150px.png" id="logo" class=""/></a>            
    </div> 
    <div class="collapse navbar-collapse" id="navbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="home.jsp">Inicio </a></li>
        <!-- menu cadastros -->
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Cadastros <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="usuario.jsp">Usuário</a></li>
            <li><a href="repositorio.jsp">Repositório</a></li>
            <li><a href="atividade.jsp">Atividade</a></li>
            <li class="active"><a href="bomba.jsp">Bomba</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Relatórios <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="consumo-periodico.jsp">Consumo periódico</a></li>
            <li><a href="#">Gráficos</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" id="usuario-logado" data-toggle="dropdown" role="button" aria-expanded="false">
            <span class="usuario">Swenilton Souza</span><img src="img/perfil.png" id="perfil" width="40px"/><span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#" data-toggle="modal" data-target="#alterar-usuario">Editar perfil</a></li>
            <li><a href="#" data-toggle="modal" data-target="#ver-gastos">Ver gastos</a></li>
            <li><a href="#" data-toggle="modal" data-target="#ver-rank">Ver Rank</a></li>
            <li class="divider"></li>
            <li><a href="index.jsp">Sair</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div><!-- fim container -->
</nav>
<!-- modal alterar dados usuario logado -->
<div class="modal fade" id="alterar-usuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Alterar Usuário</h4>
      </div>
      <div class="modal-body">
        <form class="">
          <div id="foto-usuario" style="text-align: center;">
            <img src="http://placehold.it/150x150" alt="Imagem do Usuário" class="img-circle"/>
            <div class="form-group">
              <label for="foto">Procurar Foto</label>
              <input type="file" id="foto"/>
            </div>
          </div>
          <div id="dados-usuario">
            <div class="form-group">
              <label for="nome">Nome</label>
              <input type="text" class="form-control" id="nome" placeholder="Insira seu Nome"/>
            </div>
            <div class="form-group">
              <label for="email">Email</label>
              <span class="add-on"><i class="icon-envelope"></i></span>
              <input type="email" class="form-control" id="email" placeholder="insira seu email"/>
            </div>
            <div class="form-group">
              <label for="telefone">Telefone</label>
              <input type="tel" class="form-control" id="telefone" placeholder="insira seu número de telefone"/>
            </div>
            <div class="form-group">
              <label for="login">Login</label>
              <input type="text" class="form-control" id="login" placeholder="insira seu nome de usuário"/>
            </div>
            <div class="form-group col-xs-6">
              <label for="perfil">Perfil</label>
              <select class="form-control" id="perfil">
                <option>Administrador</option>
                <option>Usuario</option>
              </select>
            </div>
            <div class="form-group col-xs-6">
              <label for="ativo">Ativo</label>
              <select class="form-control" id="ativo">
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
        <button type="button" class="btn btn-success">Salvar Alterações</button>
      </div>
    </div>
  </div>
</div><!-- fim modal alterar dados usuario logado -->
<!-- modal ver gastos -->
<div class="modal fade" id="ver-gastos" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <br/>
      </div>
      <div class="modal-body">
        <h2>Esta função será implementada na próxima versão.</h2>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
      </div>
    </div>
  </div>
</div><!-- fim modal ver gastos -->
<!-- modal ver rank -->
<div class="modal fade" id="ver-rank" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <br/>
      </div>
      <div class="modal-body">
        <h2>Esta função será implementada na próxima versão.</h2>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
      </div>
    </div>
  </div>
</div> <!-- fim modal ver rank -->
<div class="container">    
  <div class="conteudo">        
    <div class="page-header page-titulo">
        <!-- migalhas de pão -->
        <ol class="breadcrumb migalhas">
          <li><a href="home.jsp">Inicio</a></li>
          <li class="active">Bomba</li>
        </ol>
        <!-- titulo -->
        <h1>Bomba <small>Gerenciamento</small></h1>
    </div><!-- fim page-header -->
    <div class="panel panel-default panel-user">
      <div class="panel-heading">
          Lista de Bombas
      </div>
      <div class="table-responsive table-user">
        <table class="table table-hover">
          <caption class="hidden">Lista de Bombas</caption>
          <thead>
            <tr>
                <th>#</th>
                <th>Código</th>
                <th>Descrição</th>
                <th>Status</th>
                <th>Potência</th>
                <th>Vazão</th>
                <th>Acionamento</th> 
            </tr>
          </thead>
          <tbody>
            <tr>
                <th width="40px"><input type="radio" name="user-selected" value="1"/></th>            
                <td width="60px">1</td>
                <td>Bomba principal</td>
                <td>DESLIGADA</td>
                <td>1/2 CV</td>
                <td>110 l/min</td>
                <td>AUTOMATICO</td>
            </tr>
          </tbody>
        </table>
      </div><!-- fim div  table -->  
    </div><!-- fim painel -->
    <div class="btn-group" role="group" aria-label="">
      <a class="btn btn-default" href="bomba-inserir.jsp" role="button">Inserir</a>
      <button type="button" class="btn btn-default" data-toggle="modal" data-target="#alterar-bomba">Alterar</button>
      <button type="button" class="btn btn-default">Remover</button>
    </div>      
  </div><!-- fim conteudo -->
</div><!-- fim container -->
<!-- Modal Alterar bomba -->
<div class="modal fade" id="alterar-bomba" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Alterar Bomba</h4>
      </div>
      <div class="modal-body">
        <form class="">
          <div class="form-group col-md-12">
            <label for="descricao">Descrição</label>
            <input type="text" class="form-control" id="descricao" placeholder="Insira uma descrição"/>
          </div>
          <div class="form-group col-md-4">
            <label for="potencia">Potência</label>
            <div class="input-group">
              <input type="numeric" class="form-control" id="potencia" placeholder="Ex: 1/2"/>
              <div class="input-group-addon">CV</div>
            </div>
          </div>
          <div class="form-group col-md-4">
            <label for="vazao">Vazão</label>
            <div class="input-group">
              <input type="number" class="form-control" id="vazao" placeholder="Ex: 120"/>
              <div class="input-group-addon">l/min</div>
            </div>
          </div>
          <div class="form-group col-md-4">
            <label for="acionamento">Acionamento</label>
            <select class="form-control" id="acionamento">
              <option>AUTOMATICO</option>
              <option>MANUAL</option>
            </select> 
          </div>
          <div class="clear"></div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-success">Salvar Alterações</button>
      </div>
    </div>
  </div>
</div>
<footer class="rodape">
    <div id="footer">
        <div class="container">
            <a href="home.jsp">Início</a> | <a href="#">Termos e Condições</a> | <a href="#">Ajuda e Suporte</a> | <a href="#">Mapa do site</a> | <a href="#">Sobre a Coffee Beans</a> <br /><br />
            Water Level &copy 2015 - Todos os direitos reservados. <br/>
            Desenvolvido por <a href="http://coffeebeansdev.com.br" target="_blank"><img src="img/coffee-logo.png" alt="Logo Coffee Beans" width="70px"></a>
            <a href="http://facebook.com.br/coffeebeansdev" target="_blank"><img src="img/facebook.jpeg" alt="Logo Facebook" width="25px"></a><br/>
            <small>Version 1.0</small>
        </div>
    </div>
</footer>
</body>
</html>