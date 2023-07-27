<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:bundle basename="message">
    <table border="1">
        <caption>
            <fmt:message key="Cadastro"/>
        </caption>

        <c:if test="${consulta != null}">
            <input type="hidden" name="id" value="${consulta.id}" />
        </c:if>

        <tr>
            <td><label for="crm"><fmt:message key="Medicos"/></label></td>
            <td>
                <select id="crm" name="crm">
                    <c:forEach items="${listaMedicos}" var="medico">
                        <option value="${medico.crm}" <c:if test="${medico.crm == requestScope.medicoSelecionado}">selected</c:if>>
                            ${medico.nome} - ${medico.especialidade}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td><label for="data"><fmt:message key="Data"/></label></td>
            <td>
                <input type="date" id="data" name="data" required value="${requestScope.dataSelecionada}" />
            </td>        
        </tr>
        
        <tr>
            <td><label for="hora"><fmt:message key="Hora"/></label></td>
            <td>
                <select id="hora" name="hora">
                    <script>
                        var selectHora = document.getElementById("hora");
                
                        var horaInicial = 8;
                        var horaFinal = 18;
                        var intervaloMinutos = 30;
                        var horaSelecionada = "${requestScope.horaSelecionada}"; // Valor selecionado anteriormente
                
                        for (var hora = horaInicial; hora <= horaFinal; hora++) {
                            for (var minuto = 0; minuto < 60; minuto += intervaloMinutos) {
                                var horaFormatada = hora.toString().padStart(2, '0');
                                var minutoFormatado = minuto.toString().padStart(2, '0');
                                var option = document.createElement("option");
                                option.value = horaFormatada + ":" + minutoFormatado;
                                option.text = horaFormatada + ":" + minutoFormatado;
                
                                // Verifica se o valor atual do loop corresponde ao valor selecionado anteriormente
                                if (option.value === horaSelecionada) {
                                    option.selected = true;
                                }
                
                                selectHora.appendChild(option);
                            }
                        }
                    </script>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <c:if test="${requestScope.disponibilidade == false}">
                    <h3 style="color:red;">
                        <fmt:bundle basename="message">
                            <fmt:message key="DisponibilidadeIndisponivel"/>
                        </fmt:bundle>
                    </h3>
                </c:if>
                <input type="submit" value="<fmt:message key='Agendar'/>" />
            </td>
        </tr>
    </table>
</fmt:bundle>