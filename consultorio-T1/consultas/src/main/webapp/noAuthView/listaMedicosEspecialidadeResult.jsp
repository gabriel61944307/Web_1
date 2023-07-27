<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <fmt:bundle basename="message">
        <title><fmt:message key="Consultorio" /></title>
    </fmt:bundle>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <fmt:bundle basename="message">
        <h1><fmt:message key="ListaDeMedicosEspecialidade" /></h1>
        <div align="center">
            <table border="1">
                <caption><fmt:message key="ListaDeMedicosEspecialidade" /></caption>
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="Nome" /></th>
                    <th><fmt:message key="CRM" /></th>
                    <th><fmt:message key="Especialidade" /></th>
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
                <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="Voltar" /></a>
            </div>    
        </div>
    </fmt:bundle>
</body>
</html>
