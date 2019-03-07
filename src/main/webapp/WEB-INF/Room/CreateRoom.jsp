<%-- 
    Document   : CreateRoom
    Created on : 27.02.2019, 11:56:31
    Author     : spfli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Raumerstellung
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/CreateRoom.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
            <a class = "nav-item nav-link" href="<c:url value="/secure/RoomOverview"/>">Zurück zur Übersicht</a>    
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">
            <form method="post" class="stacked">
                <div class="column">
                    <div class = "form-group">
                        <label for="roomname">
                            Raumname:
                            <span class="required">*</span>
                        </label>                   
                        <input type="text" name="roomname" class ="form-control" value = "${form_data[0]}">
                    </div>
                    
                    <div class = "form-group">
                        <label for="deadlineCollection">
                            Ideensammlungsdeadline:
                            <span class="required">*</span>
                        </label>                  
                        <input type="date" name="deadlineCollection" class="form-control" value = "${form_data[1]}">
                    </div>
                    
                    <div class = "form-group">
                        <label for="deadlineRating">
                            Ideenbewertungsdeadline:
                            <span class="required">*</span>
                        </label>                  
                        <input type="date" name="deadlineRating" class="form-control" value = "${form_data[2]}">
                    </div>
                    <div class = "form-group">
                        <label for="participant" class = "mr-4">
                            Teilnehmer:
                        </label>
                        <div class="form-inline">
                            <input id = "participants" type ="text" name ="participant" class = "form-control mr-4">
                            <button type ="submit" name="button" value="add_participant" class="btn btn-outline-success btn-sm mr-2">hinzufügen</button>
                            <button type ="submit" name="button" value="remove_participant" class = "btn btn-outline-danger btn-sm">löschen</button>
                        </div>    
                    </div>
                    <div>
                        <ul class ="list-group-inline">
                            <c:forEach items = "${participants}" var = "part">
                                    <li class = "list-group-item">${part.username}</li>
                            </c:forEach>
                        </ul>
                    </div>
                    <button type = "submit" name="button" value="add_room" class = "btn btn-outline-primary">
                        Raum speichern
                    </button>
                    <div>
                        <p>${error}</p>
                    </div>
                </div>
            </form>
        </div>
    </jsp:attribute>
</template:base>
