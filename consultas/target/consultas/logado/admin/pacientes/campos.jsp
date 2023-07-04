<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table border="1">
    <fmt:bundle basename="message">
        <caption>
            <c:choose>
                <c:when test="${paciente != null}">
                    <fmt:message key="Edicao"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="Cadastro"/>
                </c:otherwise>
            </c:choose>
        </caption>
    
        <c:if test="${paciente != null}">
            <input type="hidden" name="id" value="${paciente.id}" />
        </c:if>
    
        <tr>
            <td><label for="nome"><fmt:message key="Nome"/></label></td>
            <td><input type="text" id="nome" name="nome" size="45" required value="${paciente.nome}"/></td>
        </tr>
    
        <tr>
            <td><label for="email"><fmt:message key="Email"/></label></td>
            <td><input type="text" id="email" name="email" size="45" required value="${paciente.email}"></td>
        </tr>
    
        <tr>
            <td><label for="senha"><fmt:message key="Senha"/></label></td>
            <td><input type="text" id="senha" name="senha" size="45" required value="${paciente.senha}"/></td>
        </tr>
    
        <tr>
            <td><label for="cpf"><fmt:message key="CPF"/></label></td>
            <td><input type="text" id="cpf" name="cpf" size="45" required value="${paciente.cpf}"/></td>
        </tr>
    
        <tr>
            <td><label for="telefone"><fmt:message key="Telefone"/></label></td>
            <td><input type="text" id="telefone" name="telefone" size="45" required value="${paciente.telefone}"/></td>
        </tr>
    
        <tr>
            <td><label for="sexo"><fmt:message key="Sexo"/></label></td>
            <td>
                <select id="sexo" name="sexo" required>
                    <option value="M" ${paciente.sexo == 'F' ? 'selected' : ''}>M</option>
                    <option value="F" ${paciente.sexo == 'M' ? 'selected' : ''}>F</option>
                </select>
            </td>
        </tr>
    
        <tr>
            <td><label for="dataNascimento"> <fmt:message key="DataDeNascimento"/></label></td>
            <td><input type="date" id="dataNascimento" name="dataNascimento" size="45" required value="${paciente.dataNascimento}"/></td>
        </tr>
    
        <tr>
            <td colspan="2" align="center"><input type="submit" value="<fmt:message key='Salvar' />"/></td>
        </tr>
    </fmt:bundle>
</table>