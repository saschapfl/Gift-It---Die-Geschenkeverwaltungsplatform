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
        <div class="menuitem">
            <a href="<c:url value="/secure/RoomOverview"/>">Zurück zur Übersicht</a>
        </div>
        <div class ="menuitem">
            <p>Räumchen wär ein Träumchen, ${user_name}</p>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">
            <form action="create_room" method="post" class="stacked">
                <div class="column">
                    <label for="j_roomname">
                        Raumname:
                        <span class="required">*</span>
                    </label>
                    <div>
                    <input type="text" name="j_roomname">
                    </div>
                    <label for="j_deadlineCollection">
                        Ideensammlungsdeadline:
                        <span class="required">*</span>
                    </label>
                    <div>
                    <input type="text" name="j_deadlineCollection">
                    </div>
                    <label for="j_deadlineRating">
                        Ideenbewertungsdeadline:
                        <span class="required">*</span>
                    </label>
                    <div>
                    <input type="text" name="j_deadlineRating">
                    </div>
                    <label for="j_participant">
                        Teilnehmer hinzufügen:
                    </label>
                    <div>
                        <input type ="text" name ="j_participant">
                    </div>
                    <button type = "submit">
                        Raum speichern
                    </button>
                </div>
            </form>
        </div>
    </jsp:attribute>
</template:base>
