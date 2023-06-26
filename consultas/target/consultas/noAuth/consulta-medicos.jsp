<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Médicos</title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Lista de Médicos</h1>
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
            </tr>
            <c:forEach var="medico" items="${requestScope.listaMedicos}">
                <tr>
                    <td>${medico.id}</td>
                    <td>${medico.nome}</td>
                    <td>${medico.email}</td>
                    <td>${medico.senha}</td>
                    <td>${medico.crm}</td>
                    <td>${medico.especialidade}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
