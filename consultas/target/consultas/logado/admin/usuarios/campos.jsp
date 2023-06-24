<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
    <caption>
        <c:choose>
            <c:when test="${usuario != null}">
                Edição
            </c:when>
            <c:otherwise>
                Cadastro
            </c:otherwise>
        </c:choose>
    </caption>

    <c:if test="${usuario != null}">
        <input type="hidden" name="id" value="${usuario.id}" />
    </c:if>

    <tr>
        <td><label for="nome">Nome</label></td>
        <td><input type="text" id="nome" name="nome" size="45" required value="${usuario.nome}"/></td>
    </tr>

    <tr>
        <td><label for="email">Email</label></td>
        <td><input type="text" id="email" name="email" size="45" required value="${usuario.email}"></td>
    </tr>

    <tr>
        <td><label for="senha">Senha</label></td>
        <td><input type="text" id="senha" name="senha" size="45" required value="${usuario.senha}"/></td>
    </tr>

    <tr>
        <td colspan="2" align="center"><input type="submit" value="Salvar"/></td>
    </tr>
</table>