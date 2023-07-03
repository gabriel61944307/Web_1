<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="ListarEspecialidade"/></title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <fmt:bundle basename="message">
        <h1><fmt:message key="ListarEspecialidade"/></h1>

        <form method="get" action="noAuth/listaMedicosEspecialidade">
            <label for="especialidade">Selecione a Especialidade:</label>
            <select name="especialidade" id="especialidade">
                <option value="Especialidade 1">Especialidade 1</option>
                <option value="Especialidade 2">Especialidade 2</option>
            </select>
            <input type="submit" value="Buscar">
        </form>

        <hr>

        <c:if test="${not empty requestScope.listaMedicosEspecialidade}">
            <table border="1">
                <caption><fmt:message key="ListarEspecialidade"/></caption>
                <tr>
                    <th><fmt:message key="ID"/></th>
                    <th><fmt:message key="Nome"/></th>
                    <th><fmt:message key="CRM"/></th>
                    <th><fmt:message key="Especialidade"/></th>
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
        </c:if>

        <div>
            <a href="${pageContext.request.contextPath}/login.jsp">Voltar</a>
        </div>   
    </fmt:bundle>
    <h1><fmt:message key="ListarEspecialidade"/></h1>

    <form method="get" action="noAuth/listaMedicosEspecialidade">
        <label for="especialidade">Selecione a Especialidade:</label>
        <select name="especialidade" id="especialidade">
            <option value="Especialidade 1">Especialidade 1</option>
            <option value="Especialidade 2">Especialidade 2</option>
        </select>
        <input type="submit" value="Buscar">
    </form>

    <hr>

    <c:if test="${not empty requestScope.listaMedicosEspecialidade}">
        <table border="1">
            <caption><fmt:message key="ListarEspecialidade"/></caption>
            <tr>
                <th><fmt:message key="ID"/></th>
                <th><fmt:message key="Nome"/></th>
                <th><fmt:message key="CRM"/></th>
                <th><fmt:message key="Especialidade"/></th>
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
    </c:if>

    <div>
        <a href="${pageContext.request.contextPath}/login.jsp">Voltar</a>
    </div>   
</body>
</html>
