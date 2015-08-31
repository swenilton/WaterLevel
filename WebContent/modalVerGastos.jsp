<%@page import="br.com.coffeebeans.usuario.Usuario"%>
<%@page import="br.com.coffeebeans.atividade.AtividadeRealizada"%>
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
</head>
<%
	Fachada f = Fachada.getInstance();
	Usuario u = (Usuario) request.getSession().getAttribute(
			"usuarioLogado");
	if (u == null) {
		response.sendRedirect("index.jsp");
	}
%>
<body>
	<div class="modal fade" id="ver-gastos" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Minhas Atividades Realizadas</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">Atividades</div>
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
									<c:forEach var="atividade" items="<%=f.atividadeRealizadaListar(u.getId())%>">
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
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
					<div class="btn-group pull-left" role="group">
						<button type="button" class="btn btn-default">Exportar</button>
						<button type="button" class="btn btn-default">Imprimir</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>