<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
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
        <h1><fmt:message key="Ola"/>, ${sessionScope.usuarioLogado.nome}</h1>
    
        <li>
            <a href="${pageContext.request.contextPath}/consultas-paciente/cadastro"><fmt:message key="AgendarNovaConsulta"/></a>
        </li>
    
        <li>
            <a href="${pageContext.request.contextPath}/consultas-paciente/lista.jsp"><fmt:message key="ListaDeConsultas"/></a>
        </li>
    
        <li>
            <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="Sair"/></a>
        </li>
    </fmt:bundle>
</body>
</html>
