<%-- 
    Document   : loginfailed
    Created on : 28.02.2019, 12:59:28
    Author     : sven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="base_url" value="<%=request.getContextPath()%>" />

<template:base>
    <jsp:attribute name="title">
            Login fehlerhaft!
    </jsp:attribute>
    <jsp:attribute name="head">
        <div>
            Login ist fehlgeschlagen!
        </div>
    </jsp:attribute>
    <jsp:attribute name="content">
         <a href="<c:url value="/secure/RoomOverview"/>">Zurück zur Anmeldung</a>
    </jsp:attribute>    
</template:base>
