<%-- 
    Document   : RoomView
    Created on : 26.02.2019, 16:07:21
    Author     : Viktoria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Raumansicht
    </jsp:attribute>
        
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/roomview.css"/>" />
    </jsp:attribute>
   
    <jsp:attribute name="menu">
            <a class="nav-item nav-link" href="<c:url value="/secure/CreateIdea"/>">Idee erstellen</a>  
            <a class="nav-item nav-link" href="<c:url value="/secure/DeleteRoom"/>">Raum löschen</a>
    </jsp:attribute>

        

    
    <jsp:attribute name="content">
        <div class="container">
                <div class="column">
                    <c:choose>
                        <c:when test = "${empty entries}">
                            <p>
                                Der Raum hat keine Ideen, legen Sie Ideen an, um mit Ihren Freunden über ein Geschenk abstimmen zu können!
                            </p>
                        </c:when>
                        <c:otherwise>
                            <ul>
                                <c:forEach items = "${entries}" var = "entry">
                                    <li>
                                        <a href ="<c:url value = "/secure/IdeaView?id=${entry.id}"/>">${entry.name}</a>
                                    </li>
                                </c:forEach>
                            </ul> 
                        </c:otherwise>
                    </c:choose>
                </div>
        </div>
    </jsp:attribute>
</template:base>
