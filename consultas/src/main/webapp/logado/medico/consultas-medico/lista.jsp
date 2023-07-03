<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Consultório</title>
</head>
<body>
    <div align="center">
        <h1>Lista de Consultas</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/medico">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <tr>
                <th>Paciente</th>
                <th>Data</th>
            </tr>
            <c:forEach items="${requestScope.listaConsultas}" var="consulta" varStatus="status">
                <c:set var="paciente" value="${requestScope.listaNomes[status.index]}" />
                <c:set var="dataFormatada" value="${requestScope.listaData[status.index]}" />
                <c:set var="horaFormatada" value="${requestScope.listaHora[status.index]}" />
                <tr>
                    <td>${paciente}</td>
                    <td>${dataFormatada} às ${horaFormatada}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>