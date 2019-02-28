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
    <jsp:attribute name="content">
        <div class="container">
            <form action="j_security_check" method="post" class="stacked">
                <div class="column">
                    <%-- Eingabefelder --%>
                    <label for="j_username">
                        Benutzername:*                       
                    </label>
                    <div>
                        <input type="text" name="j_username">
                    </div>
                    <label for="j_password">
                        Passwort:*
                    </label>
                    <div>
                        <input type="password" name="j_password">
                    </div>
                    <%-- Button zum Abschicken --%>
                    <div >
                        <button type="submit">
                            Login
                        </button>
                    </div>
                </div>
                </form>
                <div>
                    <a href="<c:url value="/signup/"/>">Registrieren</a>
                </div>
            
        </div>
    </jsp:attribute>
</template:base>