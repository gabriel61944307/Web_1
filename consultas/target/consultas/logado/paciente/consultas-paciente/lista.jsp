<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Consultório</title>
</head>
<body>
    <div align="center">
        <h1>Gerenciamento de Consultas</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/paciente">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/consultas-paciente/cadastro">Agendar Nova Consulta</a> &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <caption>Lista de Consultas</caption>
            <tr>
                <th>Médico</th>
                <th>Data</th>
                <th>Ações</th>
            </tr>
            <c:forEach items="${requestScope.listaConsultas}" var="consulta" varStatus="status">
                <c:set var="medico" value="${requestScope.listaNomes[status.index]}" />
                <c:set var="dataFormatada" value="${requestScope.listaData[status.index]}" />
                <c:set var="horaFormatada" value="${requestScope.listaHora[status.index]}" />
                <tr>
                    <td>${medico}</td>
                    <td>${dataFormatada} às ${horaFormatada}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/consultas-paciente/edicao?id=${consulta.id}">
                            Edição
                        </a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/consultas-paciente/remocao?id=${consulta.id}" onclick="return confirm('Tem certeza de que deseja excluir esta consulta?');">
                            Remoção
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>