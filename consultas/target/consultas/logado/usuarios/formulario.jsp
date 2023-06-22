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
				<a href="lista">Listar Usuários</a>
			</h2>
		</div>
		<div align="center">
			<c:choose>
				<c:when test="${usuario != null}">
					<form action="atualizacao" method="post">
						<%@include file="campos.jsp"%>
					</form>
				</c:when>
				<c:otherwise>
					<form action="insercao" method="post">
						<%@include file="campos.jsp"%>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
		<c:if test="${!empty requestScope.mensagens}">
			<ul class="erro">
				<c:forEach items="${requestScope.mensagens}" var="mensagem">
					<li>${mensagem}</li>
				</c:forEach>
			</ul>
		</c:if>
	</body>
</html>
