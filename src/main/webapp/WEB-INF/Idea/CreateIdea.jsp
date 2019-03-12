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
       <form class="post">
        <link rel="stylesheet" href="<c:url value="/css/createIdea.css"/>" />
       </form>
    </jsp:attribute>
    
     <jsp:attribute name="content">
          <div class="formular">
            <form method="post" class="stacked">
                <div class="column">
                    <%-- Eingabefelder --%>
                    
                     <div class="form-group col-md-6">
                     <label for="name">
                         Name:
                        <span class="required">*</span>
                     </label>
                         <input type="text" class="form-control" name="name" value="${Rforms.values["name"][0]}" required>
                    </div>
                   
                     <div class="form-group col-md-6">
                     <label for="price">
                        Preis:
                     </label>
                       <input type="number" step="0.01" class="form-control" name="price" value="${Rforms.values["price"][0]}">
                    </div>
                    
                     <div class="form-group col-md-6">
                     <label for="link">
                        Link:
                     </label>
                         <input type="url" class="form-control" name="link" value="${Rforms.values["link"][0]}">
                    </div>
                    
                     <div class="form-group col-md-6">
                     <label for="picture">
                        Bildpfad:
                     </label>
                         <input type="file" class="picture" name="upload" value="${Rforms.values["picture"][0]}">
                    </div>
                    
                     <div class="form-group col-md-6">
                     <label for="description">
                        Beschreibung:
                         <span class="required">*</span>
                     </label>
                       <input type="text" class="form-control" name="description" value="${Rforms.values["description"][0]}" required>
                    </div>
                    
                    <%-- Button zum Abschicken --%>
                    <div>
                        <button class="btn btn-primary" type="submit">
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
