<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <fmt:bundle basename="message">
            <title><fmt:message key="Consultorio" /></title>
        </fmt:bundle>
    </head>
    <body>
        <fmt:bundle basename="message">
            <h1><fmt:message key="PaginaAdministrador" /></h1>
            <p><fmt:message key="Ola" />, ${sessionScope.usuarioLogado.nome}</p>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/pacientes/lista.jsp"><fmt:message key="AdministrarPacientes" /></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/medicos/lista.jsp"><fmt:message key="AdministrarMedicos" /></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/usuarios/lista.jsp"><fmt:message key="AdministrarUsuariosSistema" /></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="Sair" /></a>
                </li>
            </ul>
        </fmt:bundle>
    </body>
</html>
