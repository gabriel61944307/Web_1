<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Consultório</title>
</head>
<body>
    <div align="center">
        <h1>Gerenciamento de Usuários do Sistema</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/admin">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/usuarios/cadastro">Adicionar Novo Usuário</a> &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <caption>Lista de Usuários Administradores</caption>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Senha</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="usuario" items="${requestScope.listaUsuarios}">
                <tr>
                    <td>${usuario.id}</td>
                    <td>${usuario.nome}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.senha}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/usuarios/edicao?id=${usuario.id}">
                            Edição
                        </a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/usuarios/remocao?id=${usuario.id}" onclick="return confirm('Tem certeza de que deseja excluir este usuário?');">
                            Remoção
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>