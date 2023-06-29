<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu do Sistema</title>
    </head>
    <body>
        <h1>Página do Administrador</h1>
        <p>Olá, ${sessionScope.usuarioLogado.nome}</p>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/pacientes/lista.jsp">Administrar Pacientes</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/medicos/lista.jsp">Administrar Médicos</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/usuarios/lista.jsp">Administrar Usuários do Sistema</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
            </li>
        </ul>
    </body>
</html>