<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Consultório</title>
</head>
<body>
    <div align="center">
        <h1>Gerenciamento de Consultas</h1>
        <h2>
            <a href="lista">Lista de Consultas</a>
        </h2>
    </div>

    <div align="center">
        <c:choose>
            <c:when test="${consulta != null}">
                <form action="atualizacao" method="post">
                    <%@include file="campos-edicao.jsp"%>
                </form>
            </c:when>
            <c:otherwise>
                <form action="insercao" method="post">
                    <%@include file="campos-insercao.jsp"%>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>