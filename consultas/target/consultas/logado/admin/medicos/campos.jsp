<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:bundle basename="message">
    <table border="1">
        <caption>
            <c:choose>
                <c:when test="${medico != null}">
                    <fmt:message key="Edicao" />
                </c:when>
                <c:otherwise>
                    <fmt:message key="Cadastro" />
                </c:otherwise>
            </c:choose>
        </caption>

        <c:if test="${medico != null}">
            <input type="hidden" name="id" value="${medico.id}" />
        </c:if>
        <tr>
            <td><label for="nome"><fmt:message key="Nome" /></label></td>
            <td><input type="text" id="nome" name="nome" size="45" required value="${medico.nome}" /></td>
        </tr>

        <tr>
            <td><label for="email"><fmt:message key="Email" /></label></td>
            <td><input type="text" id="email" name="email" size="45" required value="${medico.email}" /></td>
        </tr>

        <tr>
            <td><label for="senha"><fmt:message key="Senha" /></label></td>
            <td><input type="text" id="senha" name="senha" size="45" required value="${medico.senha}" /></td>
        </tr>

        <tr>
            <td><label for="crm"><fmt:message key="CRM" /></label></td>
            <td><input type="text" id="crm" name="crm" size="45" required value="${medico.crm}" /></td>
        </tr>

        <tr>
            <td><label for="especialidade"><fmt:message key="Especialidade" /></label></td>
            <td>
                <select id="especialidade" name="especialidade" required>
                    <option value=""><fmt:message key="SelecioneEspecialidade" /></option>
                    <c:forEach var="especialidade" items="${listaEspecialidades}">
                        <option value="${especialidade}" ${medico.especialidade == especialidade ? 'selected' : ''}>
                            ${especialidade}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        
        
        <tr>
            <td colspan="2" align="center"><input type="submit" value="<fmt:message key='Salvar' />" /></td>
        </tr>
    </table>
</fmt:bundle>