<%--
    Document   : CreateIdea
    Created on : 27.02.2019, 12:07:55
    Author     : Viktoria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<template:base>
    <jsp:attribute name = "head">
        <link rel="stylesheet" href="<c:url value="/css/Error.css"/>" />
    </jsp:attribute>
    <jsp:attribute name="title">
        Fehler
    </jsp:attribute>

    <jsp:attribute name="menu">
        <a class="nav-item nav-link" href="<c:url value="/secure/RoomOverview"/>">Zurück zur Raumübersicht</a>
    </jsp:attribute>

    <jsp:attribute name="content">
        <c:choose>
            <c:when test = "${text == 'Sie haben keine Berechtigung auf diesen Raum!' or text == 'Sie haben keine Berechtigung auf diese Idee!'}">
                <p class="ml-4">${text}&nbsp;&nbsp;<i class="fa fa-lock" aria-hidden="true"></i></p>
            </c:when>
            <c:otherwise>
                <p class="ml-4">${text}&nbsp;&nbsp;<i class="fa fa-frown-o" aria-hidden="true"></i></p>
                </c:otherwise>
            </c:choose>
        </jsp:attribute>
    </template:base>
