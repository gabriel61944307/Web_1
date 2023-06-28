<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Login</title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Página de Login</h1>
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
                    <th>Email:</th>
                    <td><input type="text" name="email" value="admin@email.com"/></td>
                </tr>
                <tr>
                    <th>Senha:</th>
                    <td><input type="password" name="senha" value="admin" /></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="bOK" value="Entrar">
                    </td>
                </tr>
            </table>
        </form>

        <form method="post" action="index.jsp">
            <table>
                <tr>
                    <th>Email:</th>
                    <td><input type="text" name="email" value="paciente@email.com"/></td>
                </tr>
                <tr>
                    <th>Senha:</th>
                    <td><input type="password" name="senha" value="senha"/></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="bOK" value="Entrar">
                    </td>
                </tr>
            </table>
        </form>

        <form method="post" action="index.jsp">
            <table>
                <tr>
                    <th>Email:</th>
                    <td><input type="text" name="email" value="medico@email.com"/></td>
                </tr>
                <tr>
                    <th>Senha:</th>
                    <td><input type="password" name="senha" value="senha"/></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="bOK" value="Entrar">
                    </td>
                </tr>
            </table>
        </form>

        <form method="get" action="noAuth/consulta-medicos.jsp">
            <input type="submit" name="bListarMedicos" value="Listar Médicos">
        </form>

        <!-- <%
            // Verificar qual botão foi clicado
            String listarMedicos = request.getParameter("bListarMedicos");
            if (listarMedicos != null) {
                response.sendRedirect("/noAuth/consulta-medicos.jsp");
            }
        %> -->
    </body>
</html>
