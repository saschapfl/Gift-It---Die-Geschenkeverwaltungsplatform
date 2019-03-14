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
        <c:choose>
            <c:when test = "${deadline2check}">
                Raumansicht - Geschenk gefunden!
            </c:when>
            <c:when test = "${deadline1check}">
                Raumansicht - Bewertungsphase
            </c:when>
            <c:otherwise>
                Raumansicht - Sammlungsphase
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
        
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/RoomView.css"/>" />
    </jsp:attribute>
   
    <jsp:attribute name="menu">
            <a class="nav-item nav-link" href="<c:url value="/secure/RoomOverview"/>">Zurück zur Übersicht</a>
            <c:if test = "${!deadline1check}">
                <a class="nav-item nav-link" href="<c:url value="/secure/CreateIdea?id=${id}"/>">Idee erstellen</a>  
            </c:if>
            <c:if test = "${owner}"> 
                <a class="nav-item nav-link" href="<c:url value="/secure/deleteRoom?id=${id}"/>">Raum löschen</a>
            </c:if>
    </jsp:attribute>

    
    <jsp:attribute name="content">
        <div class ="container w-100 mt-2 p-0 d-flex" id = "timeline">
            <div class = "d-flex m-2">
                <p>Heute</p>
                <i class ="fa fa-check ml-2 mt-1"></i>              
            </div>
            <div class="progress w-50 m-2" style="height: 65%;">
                <div  id="t1" class="progress-bar progress-bar-striped progress-bar-animated bg-success" role="progressbar" style="${timeline1}"  aria-valuemin="0" aria-valuemax="100">${timelinetext1}</div>
            </div>
            <div class = "d-flex m-2">
                <p>${deadline1}</p>
                <c:choose>
                    <c:when test = "${deadline1check}">
                        <i class="fa fa-check ml-2 mt-1"></i>    
                    </c:when>
                    <c:otherwise>
                        <i class="fa fa-lightbulb-o ml-2 mt-1"></i>   
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="progress w-50 m-2" style="height: 65%;">
                <div  id="t2" class="progress-bar progress-bar-striped progress-bar-animated bg-success" role="progressbar" style="${timeline2}"  aria-valuemin="0" aria-valuemax="100">${timelinetext2}</div>
            </div>
            <div class = "d-flex m-2">
                <p>${deadline2}</p>
                <c:choose>
                    <c:when test = "${deadline2check}">
                        <i class="fa fa-check ml-2 mt-1"></i>    
                    </c:when>
                    <c:otherwise>
                        <i class="fa fa-bullseye ml-2 mt-1"></i>   
                    </c:otherwise>
                </c:choose>              
            </div>
        </div>
        <hr class ="grey_row">
        <div class="container h-100 w-100 m-0 p-0 d-flex">
                <div class ="w-25 h-100 ml-2 p-4" id="room_participants">
                    <form method ="post" class ="stacked">
                    <h2 class = "pl-4">Teilnehmer</h2>
                        <ul class = "list-group list-group-flush">
                            <c:forEach items = "${participants}" var = "part">
                                <li class = "list-group-item mb-2">
                                    ${part.username}
                                    <c:if test = "${owner}">
                                        <button type="submit" name = "button" value = "${part.username}" class="close ml-2" aria-label="Close">
                                                   <span aria-hidden="true">&times;</span>
                                        </button> 
                                    </c:if>
                                </li>
                            </c:forEach> 
                            <c:if test = "${owner==true}">
                                <li class ="d-flex mt-4">
                                    <input id = "add_user" name ="new_part" class = "w-75 mr-4"/>
                                    <button type = "submit" id = "add_user_btn" class = "btn btn-primary" name = "button" value = "add_user"><i class = "fa fa-plus-circle"></i></button>
                                </li>
                            </c:if>    
                        </ul>
                    <p>${warning}</p>
                    </form>
                 </div>
                <div class="w-75 m-2 ">
                    <c:choose>
                        <c:when test = "${empty entries && deadline1check}">
                                Leider wurde keine Idee gesammelt. Löschen Sie diesen Raum und erstellen Sie einen neuen, um erneut nach einem Geschenk zu suchen!
                        </c:when>
                        <c:when test = "${empty entries}">
                            <p>
                                Der Raum hat keine Ideen, legen Sie Ideen an, um mit Ihren Freunden über ein Geschenk abstimmen zu können!
                            </p>
                        </c:when>
                        <c:otherwise>
                                <c:forEach items = "${entries}" var = "entry">
                                    <div class="card">
                                                <img scr= />
                                                <div class="card-body">   
                                                    <h5 class="card-title"> <a href ="<c:url value = "/secure/IdeaView?id=${entry.id}"/>">${entry.name}</a></h5>
                                                    <p class="card-text"> <a href ="<c:url value = "/secure/IdeaView?id=${entry.id}"/>">${entry.description}</a></p>
                                                </div>
                                                <c:if test = "${deadline1check && !deadline2check}">
                                                    <div class="card-footer">
                                                        <form method="post">
                                                           <small class="text-muted"><a>${entry.like}</a>&nbsp;<button type="submit" name="like" value="${entry.id}" class="btn btn-light btn-sm"> <i class="fa fa-thumbs-up"></button></i></small>
                                                           <small class="text-muted"><a>${entry.dislike}</a>&nbsp;<button type="submit" name="dislike" value="${entry.id}" class="btn btn-light btn-sm"> <i class="fa fa-thumbs-down"></button></i></small>
                                                        </form>
                                                    </div>
                                                
                                                    <small class="text-muted"> ${error} </small> 
                                                 </c:if>
                                    </div>
                                </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
        </div>
    </jsp:attribute>
</template:base>
