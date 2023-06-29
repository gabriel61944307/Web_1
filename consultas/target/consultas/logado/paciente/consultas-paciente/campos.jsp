<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <td><label for="crm">Médicos</label></td>
        <td>
            <select id="crm" name="crm">
                <c:forEach items="${listaMedicos}" var="medico">
                    <option value="${medico.crm}">${medico.nome} - ${medico.especialidade}</option>
                </c:forEach>
            </select>
        </td>
    </tr>

    <tr>
        <td><label for="data">Data</label></td>
        <td><input type="date" id="data" name="data" required /></td>
    </tr>

    <tr>
        <label for="hora">Hora:</label>
        <select id="hora" name="hora">
            <script>
                var selectHora = document.getElementById("hora");
    
                var horaInicial = 8; // Hora inicial (8:00 da manhã)
                var horaFinal = 18; // Hora final (18:00)
                var intervaloMinutos = 30; // Intervalo de 30 minutos
    
                for (var hora = horaInicial; hora <= horaFinal; hora++) {
                    for (var minuto = 0; minuto < 60; minuto += intervaloMinutos) {
                        var horaFormatada = hora.toString().padStart(2, '0');
                        var minutoFormatado = minuto.toString().padStart(2, '0');
                        var option = document.createElement("option");
                        option.value = horaFormatada + ":" + minutoFormatado;
                        option.text = horaFormatada + ":" + minutoFormatado;
                        selectHora.appendChild(option);
                    }
                }
            </script>
        </select>
    </tr>

    <tr>
        <td colspan="2" align="center"><input type="submit" value="Agendar" /></td>
    </tr>
</table>
