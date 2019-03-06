<%-- 
    Document   : IdeaView
    Created on : 27.02.2019, 11:50:20
    Author     : Viktoria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<template:base>
    <jsp:attribute name="title">
        Idee
    </jsp:attribute>
        
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/ideadetail.css"/>" />
    </jsp:attribute>
    
     <jsp:attribute name="content">
         <form method ="post">
             <div class="column">
                 <div>
                 <label for="idea_name">
                     Neue Kategorie:
                 </label>
                <input type="text" name="idea_name" value="${idea_name}" readonly="readonly">
                </div>
                   
                    <div>
                     <label for="price">
                        Preis:
                     </label>
                        <input type="text" name="price" value="${price}" readonly="readonly">
                    </div>
                    
                    <div>
                     <label for="link">
                        Link:
                     </label>
                         <input type="text" name="link" value="${link}" readonly="readonly">
                    </div>
                    
                    <div>
                     <label for="picture">
                        Bildpfad:
                     </label>
                      <input type="text" name="picture" value="${picture}" readonly="readonly">
                    </div>
                    
                    <div>
                     <label for="description">
                        Beschreibung:
                     </label>
                      <input type="text" name="description" value="${description}" readonly="readonly">
                    </div>
                    
                    <%-- Button zum Abschicken --%>
                    <div>
                        <button type="submit"  name="button" value="deleteIdea">Löschen</button>
                    </div>  
               </div>                          
         </form>
 </jsp:attribute>
</template:base>
