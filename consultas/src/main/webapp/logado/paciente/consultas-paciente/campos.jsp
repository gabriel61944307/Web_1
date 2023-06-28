<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
    <caption>
        <c:choose>
            <c:when test="${paciente != null}">
                Edição
            </c:when>
            <c:otherwise>
                Cadastro
            </c:otherwise>
        </c:choose>
    </caption>

    <c:if test="${consulta != null}">
        <input type="hidden" name="id" value="${consulta.id}" />
    </c:if>

    <tr>
        <td><label for="sexo">Sexo</label></td>
        <td>
            <select id="sexo" name="sexo" required>
                <option value="M" ${paciente.sexo == 'F' ? 'selected' : ''}>M</option>
                <option value="F" ${paciente.sexo == 'M' ? 'selected' : ''}>F</option>
            </select>
        </td>
    </tr>

    <tr>
        <td colspan="2" align="center"><input type="submit" value="Salvar"/></td>
    </tr>
</table>