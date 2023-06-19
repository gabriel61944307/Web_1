<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Bem-vindo aos Usuários</title>
	</head>
	<body>
		<div align="center">
			<h1>Bem-vindo aos Usuários</h1>
			<h2>
				<a href="/${sessionScope.contextPath}/editoras">Entidade Editoras</a>&nbsp;&nbsp;&nbsp;
				<a href="/${sessionScope.contextPath}/livros">Entidade Livros</a>&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
				<br/><br/>
				<a href="/${sessionScope.contextPath}/usuarios/cadastro">Criar Usuário</a>
			</h2>
			<h3>Listagem de Usuários</h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Login</th>
					<th>Senha</th>
					<th>Nome</th>
					<th>Papel</th>
					<th>Ações</th>
				</tr>
				<c:forEach var="usuario" items="${requestScope.listaUsuarios}">
					<tr>
						<td><c:out value="${usuario.id}" /></td>
						<td><c:out value="${usuario.login}" /></td>
						<td><c:out value="${usuario.senha}" /></td>
						<td><c:out value="${usuario.nome}" /></td>
						<td><c:out value="${usuario.papel}" /></td>
						<td>
							<a href="/${sessionScope.contextPath}/usuarios/edicao?id=<c:out value='${usuario.id}' />">Atualizar Usuário</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="/${sessionScope.contextPath}/usuarios/remocao?id=<c:out value='${usuario.id}' />" onclick="return confirm('Tem certeza que deseja remover o usuário?');">Remover Usuário</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>
