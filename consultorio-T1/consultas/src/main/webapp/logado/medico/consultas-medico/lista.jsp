<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:bundle basename="message">
        <title><fmt:message key="Consultorio" /></title>
    </fmt:bundle>
</head>
<body>
    <fmt:bundle basename="message">
        <div align="center">
            <h1><fmt:message key="ListaDeConsultas" /></h1>
            <h2>
                <a href="${pageContext.request.contextPath}/medico"><fmt:message key="MenuPrincipal" /></a> &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="Logout" /></a>
            </h2>
        </div>

        <div align="center">
            <table border="1">
                <tr>
                    <th><fmt:message key="Paciente" /></th>
                    <th><fmt:message key="Data" /></th>
                </tr>
                <c:forEach items="${requestScope.listaConsultas}" var="consulta" varStatus="status">
                    <c:set var="paciente" value="${requestScope.listaNomes[status.index]}" />
                    <c:set var="dataFormatada" value="${requestScope.listaData[status.index]}" />
                    <c:set var="horaFormatada" value="${requestScope.listaHora[status.index]}" />
                    <tr>
                        <td>${paciente}</td>
                        <td>${dataFormatada} <fmt:message key="As" /> ${horaFormatada}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </fmt:bundle>
</body>
</html>
