<%-- 
    Document   : IdeaView
    Created on : 27.02.2019, 11:50:20
    Author     : Viktoria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:set var="base_url" value="<%=request.getContextPath()%>" />

<template:base>
    <jsp:attribute name="title">
        Raumansicht
    </jsp:attribute>
        
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/roomview.css"/>" />
    </jsp:attribute>
        
   <jsp:attribute name="menu">
       <form action="${pageContext.request.contextPath}/RoomView" method="post">
        <button type="submit" name="button" value="createIdea">Idee erstellen</button>
        <button type="submit" name="button" value="deleteIdea">Idee löschen</button>
        <button type="submit" name="button" value="deleteRoom">Raum löschen</button>
       </form>
    </jsp:attribute>
    
     <jsp:attribute name="content">
         <div class="container">
             //Idee dynamisch erstellen
         </div>
</template:base>
