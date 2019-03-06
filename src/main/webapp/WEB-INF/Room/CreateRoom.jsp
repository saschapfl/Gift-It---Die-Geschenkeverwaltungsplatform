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
        <link rel="stylesheet" href="<c:url value="/css/createRoom.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
            <a class = "nav-item nav-link" href="<c:url value="/secure/RoomOverview"/>">Zurück zur Übersicht</a>    
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">
            <form method="post" class="stacked">
                <div class="column">
                    <label for="roomname">
                        Raumname:
                        <span class="required">*</span>
                    </label>
                    <div>
                    <input type="text" name="roomname"  value = "${form_data[0]}" required>
                    </div>
                    <label for="deadlineCollection">
                        Ideensammlungsdeadline:
                        <span class="required">*</span>
                    </label>
                    <div>
                    <input type="date" name="deadlineCollection" value = "${form_data[1]}">
                    </div>
                    <label for="deadlineRating">
                        Ideenbewertungsdeadline:
                        <span class="required">*</span>
                    </label>
                    <div>
                    <input type="date" name="deadlineRating" value = "${form_data[2]}">
                    </div>
                    <label for="participant">
                        Teilnehmer hinzufügen:
                    </label>
                    <div>
                        <input type ="text" name ="participant">
                        <button type ="submit" name="button" value="add_participant">hinzufügen</button>
                        <button type ="submit" name="button" value="remove_participant">löschen</button>
                    </div>
                    <div>
                        <ul>
                            <c:forEach items = "${participants}" var = "part">
                                    <li>${part.username}</li>
                            </c:forEach>
                        </ul>
                    </div>
                    <button type = "submit" name="button" value="add_room">
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
