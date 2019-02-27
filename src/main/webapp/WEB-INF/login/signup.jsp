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
    <%-- Registrierungsformular --%>
    <jsp:attribute name="RFormular">
        <div class="formular">
            <form method="post" class="stacked">
                <div class="column">
                    <%-- Eingabefelder --%>
                    <label for="bname">
                        Benutzername:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="bname" value="${Rforms.values["bname"][0]}" required>
                    </div>

                    <label for="password1">
                        Passwort:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="password" name="password1" value="${Rforms.values["password1"][0]}"required>
                    </div>

                    <label for="password2">
                        Passwort wiederholen:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="password" name="password2" value="${Rforms.values["password2"][0]}" required>
                    </div>

                    <%-- Button zum Abschicken --%>
                    <div class="side-by-side">
                        <button class="icon-pencil" type="submit">
                            Registrieren
                        </button>
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
