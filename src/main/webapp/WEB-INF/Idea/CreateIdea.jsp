<%-- 
    Document   : CreateIdea
    Created on : 27.02.2019, 12:07:55
    Author     : Viktoria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<template:base>
    <jsp:attribute name="title">
        Idee erstellen
    </jsp:attribute>
        
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/createIdea.css"/>" />
    </jsp:attribute>
    
     <jsp:attribute name="content">
          <div class="formular">
            <form method="post" class="stacked">
                <div class="column">
                    <%-- Eingabefelder --%>
                    <div>
                     <label for="name">
                        Ideen Name:
                        <span class="required">*</span>
                     </label>
                         <input type="text" name="name" value="${Rforms.values["name"][0]}" required>
                    </div>
                   
                    <div>
                     <label for="price">
                        Preis:
                     </label>
                       <input type="text" name="price" value="${Rforms.values["price"][0]}">
                    </div>
                    
                    <div>
                     <label for="link">
                        Link:
                     </label>
                         <input type="text" name="link" value="${Rforms.values["link"][0]}">
                    </div>
                    
                    <div>
                     <label for="picture">
                        Bildpfad:
                     </label>
                         <input type="picture" name="picture" value="${Rforms.values["picture"][0]}">
                    </div>
                    
                    <div>
                     <label for="description">
                        Beschreibung:
                     </label>
                       <input type="text" name="description" value="${Rforms.values["description"][0]}">
                    </div>
                    
                    <%-- Button zum Abschicken --%>
                    <div>
                        <button class="icon-pencil" type="submit">
                            Speichern
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
