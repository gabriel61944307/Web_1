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
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <fmt:bundle basename="message">
            <h1><fmt:message key="PaginaDeLogin" /></h1>
            <c:if test="${mensagens.existeErros}">
                <div id="erro">
                    <ul>
                        <c:forEach var="erro" items="${mensagens.erros}">
                            <li>${erro}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form method="post" action="index.jsp">
                <table>
                    <tr>
                        <th><fmt:message key="Email"/>:</th>
                        <td><input type="text" name="email" value="${param.email}"/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="Senha"/>:</th>
                        <td><input type="password" name="senha" value="${param.senha}" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"> 
                            <input type="submit" name="bOK" value="<fmt:message key="Entrar"/>">
                        </td>
                    </tr>
                </table>
            </form>

            <form method="get" action="noAuth/consulta-medicos.jsp">
                <input type="submit" name="bListarMedicos" value="<fmt:message key="ListarMedicos" />">
            </form>

            <form method="get" action="noAuth/listaEspecialidade">
                <label for="especialidade"><fmt:message key="SelecioneEspecialidade" /></label>
                <select id="especialidade" name="especialidade">
                    <c:forEach var="especialidade" items="${listaEspecialidades}">
                        <option value="${especialidade}" ${medico.especialidade == especialidade ? 'selected' : ''}>
                            ${especialidade}
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" value="<fmt:message key="ListarEspecialidade" />">
            </form>
        </fmt:bundle>
    </body>
</html>
