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
            <h1><fmt:message key="GerenciamentoMedicos" /></h1>
            <h2>
                <a href="${pageContext.request.contextPath}/admin"><fmt:message key="MenuPrincipal" /></a> &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/medicos/cadastro"><fmt:message key="AdicionarNovoMedico" /></a> &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="Logout" /></a>
            </h2>
        </div>

        <div align="center">
            <table border="1">
                <caption><fmt:message key="ListaMedicos" /></caption>
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="Nome" /></th>
                    <th><fmt:message key="Email" /></th>
                    <th><fmt:message key="Senha" /></th>
                    <th><fmt:message key="CRM" /></th>
                    <th><fmt:message key="Especialidade" /></th>
                    <th><fmt:message key="Acoes" /></th>
                </tr>
                <c:forEach var="medico" items="${requestScope.listaMedicos}">
                    <tr>
                        <td>${medico.id}</td>
                        <td>${medico.nome}</td>
                        <td>${medico.email}</td>
                        <td>${medico.senha}</td>
                        <td>${medico.crm}</td>
                        <td>${medico.especialidade}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/medicos/edicao?id=${medico.id}"><fmt:message key="Edicao" /></a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/usuarios/remocao?id=${usuario.id}" onclick="return confirm(getMessage());">
                                <fmt:message key="Remocao" />
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <script>
            function getMessage() {
                return "<fmt:message key='ConfirmacaoRemocaoMedico'/>";
            }
        </script>
    </fmt:bundle>
</body>
</html>
