<%-- 
    Document   : RoomOverview
    Created on : 27.02.2019, 10:11:31
    Author     : spfli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Raumübersicht
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/overview.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
            <a class="nav-item nav-link" href="<c:url value="/secure/createRoom"/>">Raum erstellen</a>        
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">
                <div class="column">
                    <c:choose>
                        <c:when test = "${empty entries}">
                            <p>
                                Sie sind in keinem Geschenkraum!
                            </p>
                        </c:when>
                        <c:otherwise>
                            <ul class="list-group list-group-flush">
                                <c:forEach items = "${entries}" var = "entry">
                                    <li class="list-group-item">
                                        <a href ="<c:url value = "RoomView?id=${entry.id}"/>">${entry.name}</a>
                                    </li>
                                </c:forEach>
                            </ul> 
                        </c:otherwise>
                    </c:choose>
                </div>
        </div>
    </jsp:attribute>
</template:base>
