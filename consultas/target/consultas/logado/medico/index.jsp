<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <fmt:bundle basename="message">
            <fmt:message key="Consultorio"/>
        </fmt:bundle>
    </title>
</head>
<body>
    <fmt:bundle basename="message">
        <h1>
            <fmt:message key="Ola"/>, ${sessionScope.usuarioLogado.nome}
        </h1>

        <li>
            <a href="${pageContext.request.contextPath}/consultas-medico/lista.jsp">
                <fmt:message key="ListaDeConsultas"/>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/logout.jsp">
                <fmt:message key="Sair"/>
            </a>
        </li>
    </fmt:bundle>  
</body>
</html>
