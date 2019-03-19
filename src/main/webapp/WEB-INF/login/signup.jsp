<%-- 
    Document   : signup.jsp
    Created on : 26.02.2019, 16:09:53
    Author     : sven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="base_url" value="<%=request.getContextPath()%>" />

<template:base>
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>
    <jsp:attribute name="title">
        Registrieren
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="container border trans rounded">
            <form method="post" class="stacked">
                <div class="column">
                    <%-- Eingabefelder --%>
                    <div class="form group">
                        <label class="bolt" for="bname">Benutzername:*</label> 
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-user"></i></span>
                        </div>
                        <input type="text" class="form-control" name="bname" value="${Rforms.values["bname"][0]}" placeholder="Benutzername eingeben" required>
                    </div>
                    </div>
                    <div class="form group">
                         <label class="bolt" for="password1">Passwort:*</label>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-key"></i></span>
                        </div>
                        <input type="password" class="form-control" name="password1" value="${Rforms.values["password1"][0]}" placeholder="Passwort eingeben" required>
                    </div>
                    <div class="form group">   
                        <label class="bolt" for="password2">Passwort wiederholen:*</label>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-key"></i></span>
                        </div>
                        <input type="password" class="form-control" name="password2" value="${Rforms.values["password2"][0]}" placeholder="Passwort wiederholen" required>
                    </div>              
                    <%-- Button zum Abschicken --%>
                    <div>
                        <button type="submit" class="btn btn-primary btn-lg btn-block">Registrieren</button>
                    </div>
                </div>

                <%-- Fehlermeldungen --%>
                <c:if test="${!empty Rforms.errors}">
                    <ul class="errors">
                        <c:forEach items="${Rforms.errors}" var="error">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </c:if>
            </form>
        </div>
    </jsp:attribute>
</template:base>
