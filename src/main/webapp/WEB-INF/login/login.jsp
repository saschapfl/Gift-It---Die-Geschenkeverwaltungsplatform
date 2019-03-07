<%-- 
    Document   : login
    Created on : 27.02.2019, 18:57:58
    Author     : sven
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="base_url" value="<%=request.getContextPath()%>" />

<template:base>
    <jsp:attribute name="title">
        Login
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="container">
            <form action="j_security_check" method="post" class="stacked">
                <div class="column">
                    <%-- Eingabefelder --%>
                    <div class ="justify-content-center">                      
                        <div class="form group">
                            <label for="j_username" required>Benutzername:</label>            
                        <div class = "input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-user"></i></span>
                            </div>
                            <input type="text" name="j_username" class ="form-control" id="benutzername" placeholder="Benutzername eingeben">
                        </div>
                        </div>
                        <div class ="form-group">
                            <label for="j_password" required>Passwort:</label>                        
                        <div class = "input-group form-group">
                                <div class="input-group-prepend">
                                     <span class="input-group-text"> <i class="fa fa-key"></i></span>
                                </div>
                            <input type="password" name="j_password" class = "form-control" placeholder="Passwort eingeben">
                        </div>
                        </div>
                        <div >
                            <button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
                        </div>
                    </div>
                </div>
            </form>
                <div class="d-flex justify-content-center links">Noch nicht registriert? Hier&nbsp;<a href="<c:url value="/signup/"/>">registrieren!</a></div>                            
        </div>
    </jsp:attribute>
</template:base>