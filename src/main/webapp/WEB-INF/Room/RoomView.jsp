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
        <link rel="stylesheet" href="<c:url value="/css/RoomView.css"/>" />
    </jsp:attribute>
   
    <jsp:attribute name="menu">
            <a class="nav-item nav-link" href="<c:url value="/secure/CreateIdea?id=${id}"/>">Idee erstellen</a>  
            <a class="nav-item nav-link" href="<c:url value="/secure/deleteRoom?id=${id}"/>">Raum löschen</a>
    </jsp:attribute>

    
    <jsp:attribute name="content">
        <div class="container h-100 w-100 m-0 p-0 d-flex">
                <div class ="w-25 h-100 m-0 p-4" id="room_participants">
                    <h2 class = "pl-4">Teilnehmer</h2>
                        <ul class = "list-group list-group-flush">
                            <c:forEach items = "${participants}" var = "part">
                                <li class = "list-group-item">${part.username}</li>
                            </c:forEach> 
                        </ul>
                
                 </div>
                <div class="w-75">
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
                                        <div class="card-group">
                                            <div class="card">
                                                <img scr= "<c:url value = "/secure/IdeaView?id=${entry.id}"/>">${entry.name}/>
                                                <div class="card-body">   
                                                    <h5 class="card-title"> <a href ="<c:url value = "/secure/IdeaView?id=${entry.id}"/>">${entry.name}</a></h5>
                                                    <p class="card-text"> <a href ="<c:url value = "/secure/IdeaView?id=${entry.id}"/>">${entry.description}</a></p>
                                                </div>
                                                <div class="card-footer">
                                                <small class="text-muted"><a href ="<c:url value = "/secure/IdeaView?id=${entry.id}"/>">${entry.like}</a></small>
                                                <small class=""test-muted><a href ="<c:url value = "/secure/IdeaView?id=${entry.id}"/>">${entry.dislike}</a></small>
                                                </div>
                                            </div>
                                        
                                    </li>
                                </c:forEach>
                            </ul> 
                        </c:otherwise>
                    </c:choose>
                </div>
        </div>
    </jsp:attribute>
</template:base>
