<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:bundle basename="message">
        <title><fmt:message key="Consultorio"/></title>
    </fmt:bundle>
</head>
<body>
    <fmt:bundle basename="message">
        <div align="center">
            <h1><fmt:message key="GerenciamentoDeConsultas"/></h1>
            <h2>
                <a href="${pageContext.request.contextPath}/paciente"><fmt:message key="MenuPrincipal"/></a> &nbsp;&nbsp;&nbsp; 
                <a href="${pageContext.request.contextPath}/consultas-paciente/cadastro"><fmt:message key="AgendarNovaConsulta"/></a> &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="Logout"/></a>
            </h2>
        </div>

        <div align="center">
            <table border="1">
                <caption><fmt:message key="ListaDeConsultas"/></caption>
                <tr>
                    <th><fmt:message key="Medico"/></th>
                    <th><fmt:message key="Data"/></th>
                    <th><fmt:message key="Acoes"/></th>
                </tr>
                <c:forEach items="${requestScope.listaConsultas}" var="consulta" varStatus="status">
                    <c:set var="medico" value="${requestScope.listaNomes[status.index]}" />
                    <c:set var="dataFormatada" value="${requestScope.listaData[status.index]}" />
                    <c:set var="horaFormatada" value="${requestScope.listaHora[status.index]}" />
                    <tr>
                        <td>${medico}</td>
                        <td>${dataFormatada} <fmt:message key="As" /> ${horaFormatada}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/consultas-paciente/edicao?id=${consulta.id}">
                                <fmt:message key="Edicao"/>
                            </a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/consultas-paciente/remocao?id=${consulta.id}" onclick="return confirm(getMessage());">
                                <fmt:message key="Remocao"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        
        <script>
            function getMessage() {
                return "<fmt:message key='ConfirmacaoRemocaoConsulta'/>";
            }
        </script>
    </fmt:bundle>
</body>
</html>
