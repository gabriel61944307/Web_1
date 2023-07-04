<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Médicos por Especialidade</title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Lista de Médicos por Especialidade</h1>
    <div align="center">
        <table border="1">
            <caption>Lista de Médicos por Especialidade</caption>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CRM</th>
                <th>Especialidade</th>
            </tr>
            <c:forEach var="medico" items="${requestScope.listaMedicosEspecialidade}">
                <tr>
                    <td>${medico.id}</td>
                    <td>${medico.nome}</td>
                    <td>${medico.crm}</td>
                    <td>${medico.especialidade}</td>
                </tr>
            </c:forEach>
        </table>
        
        <div>
            <a href="${pageContext.request.contextPath}/logout.jsp">Voltar</a>
        </div>    
    </div>
</body>
</html>
