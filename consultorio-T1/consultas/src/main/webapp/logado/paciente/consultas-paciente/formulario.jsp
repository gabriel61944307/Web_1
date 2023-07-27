<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <fmt:bundle basename="message">
        <title><fmt:message key="Consultorio"/></title>
    </fmt:bundle>
</head>
<body>
    <fmt:bundle basename="message">
        <div align="center">
            <h1><fmt:message key="GerenciamentoDeConsultas"/></h1>
            <h2>
                <a href="lista"><fmt:message key="ListaDeConsultas"/></a>
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

    </fmt:bundle>
</body>
</html>
