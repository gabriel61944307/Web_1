<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Consultório</title>
</head>
<body>
    <div align="center">
        <h1>Gerenciamento de Médicos</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/admin">Menu Principal</a> &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/medicos/cadastro">Adicionar Novo Médico</a> &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <caption>Lista de Médicos</caption>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Senha</th>
                <th>CRM</th>
                <th>Especialidade</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="medico" items="${requestScope.listaMedicos}">
                <tr>
                    <td>${medico.id}</td>
                    <td>${medico.nome}</td>
                    <td>${medico.email}</td>
                    <td>${medico.senha}</td>
                    <td>${medico.crm}</td>
                    <td>${medico.especialidade}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/medicos/edicao?id=${medico.id}">Edição</a> &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/medicos/remocao?id=${medico.id}" onclick="return confirm('Tem certeza de que deseja excluir este médico?');">Remoção</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
