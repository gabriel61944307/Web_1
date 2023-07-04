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
            <h1><fmt:message key="GerenciamentoPacientes" /></h1>
            <h2>
                <a href="${pageContext.request.contextPath}/admin"><fmt:message key="MenuPrincipal" /></a> &nbsp;&nbsp;&nbsp; 
                <a href="${pageContext.request.contextPath}/pacientes/cadastro"><fmt:message key="AdicionarNovoPaciente" /></a> &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="Logout" /></a>
            </h2>
        </div>

        <div align="center">
            <table border="1">
                <caption><fmt:message key="PacienteLista" /></caption>
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="Nome" /></th>
                    <th><fmt:message key="Email" /></th>
                    <th><fmt:message key="Senha" /></th>
                    <th><fmt:message key="CPF" /></th>
                    <th><fmt:message key="Telefone" /></th>
                    <th><fmt:message key="Sexo" /></th>
                    <th><fmt:message key="DataDeNascimento" /></th>
                    <th><fmt:message key="Acoes" /></th>
                </tr>
                <c:forEach var="paciente" items="${requestScope.listaPacientes}">
                    <tr>
                        <td>${paciente.id}</td>
                        <td>${paciente.nome}</td>
                        <td>${paciente.email}</td>
                        <td>${paciente.senha}</td>
                        <td>${paciente.cpf}</td>
                        <td>${paciente.telefone}</td>
                        <td>${paciente.sexo}</td>
                        <td>${paciente.dataNascimento}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/pacientes/edicao?id=${paciente.id}">
                                <fmt:message key="Edicao" />
                            </a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/pacientes/remocao?id=${paciente.id}" onclick="return confirm(getMessage());">
                                <fmt:message key="Remocao" />
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <script>
            function getMessage() {
                return "<fmt:message key='ConfirmacaoRemocaoPaciente'/>";
            }
        </script>
    </fmt:bundle>
</body>
</html>
