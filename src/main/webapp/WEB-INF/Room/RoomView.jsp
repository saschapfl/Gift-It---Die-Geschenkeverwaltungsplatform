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

        <div class ="container w-80 mt-2 p-0 d-flex" id = "timeline">
            <button type="button" class="ghost_btn button" data-toggle="modal" data-target="#exampleModal" data-whatever="@money" style="height: 100%;">Aktuelles Budget: ${budget}€</button>
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style=" height: 65%;">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Budget eingeben</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form method = "post" class = "stacked">
                            <div class="modal-body">
                                <label for="budget" class="col-form-label">Individuelles Budget:</label>
                                <input type="number" step ="0.01" name ="budget" id="budget" value = "${user_budget}" oninput="check(this)">
                                <br>
                                <p>${budget_error}</p>
                            </div>
                            <div class="modal-footer">

                                <button type="submit" value = "add_budget" name = "button" class="btn btn-primary">Speichern</button>
                            </div>
                        </form>
                        <script>
                            function check(input) {
                                if (input.value <= 0) {
                                    input.setCustomValidity('Ihr Budget muss größer als 0 sein!');
                                } else {
                                    // input is fine -- reset the error message
                                    input.setCustomValidity('');
                                }
                            }
                        </script>
                    </div>
                </div>
            </div>
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
                                <c:forEach items = "${users_payed}" var = "user_payed">
                                    <c:if test = "${user_payed eq part.username}">
                                        <i class="fa fa-usd" style = "color: lightgreen;"></i>
                                    </c:if>
                                </c:forEach>
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
                                <div class="card-body">
                                    <div class="inline-block">
                                        <a class="card-title" href ="<c:url value = "/secure/IdeaView?id=${entry.id}"/>">${entry.name}</a>
                                        <c:if test = "${entry.user==user}">
                                            <button id="delete" data-toggle="modal" data-target="#deleteModal" title="Löschen" class="btn btn-light btn-sm"> <i class="fa fa-trash"></i></button>
                                            </c:if>
                                    </div>
                                    <a class="card-text"href ="<c:url value = "/secure/IdeaView?id=${entry.id}"/>">${entry.description}</a>
                                </div>
                                <div class="card-footer row mr-0 ml-0">
                                    <c:if test = "${deadline1check && !deadline2check}">
                                        <form method="post">
                                            <c:choose>
                                                <c:when test = "${fn:contains(ideasLiked, entry.id)}">
                                                    <small class="text-muted"><a>${entry.like}</a>&nbsp;<button type="submit" name="like" value="${entry.id}" class="btn btn-light btn-sm"> <i class="fa fa-thumbs-up" style = "color: blue;"></i></button></small>
                                                    <small class="text-muted"><a>${entry.dislike}</a>&nbsp;<button type="submit" name="dislike" value="${entry.id}" class="btn btn-light btn-sm"> <i class="fa fa-thumbs-down"></i></button></small>
                                                        </c:when>
                                                        <c:when test = "${fn:contains(ideasDisliked, entry.id)}">
                                                    <small class="text-muted"><a>${entry.like}</a>&nbsp;<button type="submit" name="like" value="${entry.id}" class="btn btn-light btn-sm"> <i class="fa fa-thumbs-up"></i></button></small>
                                                    <small class="text-muted"><a>${entry.dislike}</a>&nbsp;<button type="submit" name="dislike" value="${entry.id}" class="btn btn-light btn-sm"> <i class="fa fa-thumbs-down" style = "color: blue;"></i></button></small>
                                                        </c:when>
                                                        <c:otherwise>
                                                    <small class="text-muted"><a>${entry.like}</a>&nbsp;<button type="submit" name="like" value="${entry.id}" class="btn btn-light btn-sm"> <i class="fa fa-thumbs-up"></i></button></small>
                                                    <small class="text-muted"><a>${entry.dislike}</a>&nbsp;<button type="submit" name="dislike" value="${entry.id}" class="btn btn-light btn-sm"> <i class="fa fa-thumbs-down"></i></button></small>
                                                        </c:otherwise>
                                                    </c:choose>
                                        </form>
                                    </c:if>
                                </div>
                                <small class="text-muted"> ${error} </small>

                                <form method="post">
                                    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLongTitle">Löschen</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    Möchten Sie die Idee wirklich löschen?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Schließen</button>
                                                    <button type="submit" class="btn btn-primary" name="delete" value="${entry.id}">Löschen</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <%-- Modal für löschen --%>
                <%-- Modal ende --%>
            </jsp:attribute>
        </template:base>
