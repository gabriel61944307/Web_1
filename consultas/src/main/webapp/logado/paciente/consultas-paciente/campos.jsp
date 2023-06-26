<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
    .hidden {
        display: none;
    }
</style>

<table border="1">
    <caption>
        <c:choose>
            <c:when test="${consulta != null}">
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
        <td><label for="listaMedicos">Médicos</label></td>
        <td>
            <select id="listaMedicos" name="listaMedicos" required>
                <c:forEach var="medico" items="${requestScope.listaMedicos}">
                    <option value="${medico.crm}">${medico.nome} - ${medico.especialidade}</option>
                </c:forEach>
            </select>
        </td>
    </tr>

    <div>
        <tr>
            <label for="data">Data:</label>
            <input type="date" id="data" name="data">
        </tr>
        
        <tr>
            <label for="hora">Hora:</label>
            <input type="time" id="hora" name="hora">
        </tr>
        <button id="botao1" onclick="toggleVisibility('botao2')">Verificar Disponibilidade</button>
        <button id="botao2" onclick="toggleVisibility('botao1')" class="hidden">Salvar</button>
    </div>

    <tr>
        <td colspan="2" align="center">
            <input type="submit" value="Salvar" id="botaoSalvar"/>
        </td>
    </tr>
</table>