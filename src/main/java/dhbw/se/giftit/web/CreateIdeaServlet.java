/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.web;

import dhbw.se.giftit.ejb.IdeaBean;
import dhbw.se.giftit.ejb.RoomBean;
import dhbw.se.giftit.ejb.UserBean;
import dhbw.se.giftit.ejb.ValidationBean;
import dhbw.se.giftit.jpa.IdeaEntry;
import dhbw.se.giftit.jpa.RoomEntry;
import dhbw.se.giftit.jpa.UserEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Viktoria
 */
@WebServlet(name = "CreateIdeaServlet", urlPatterns = {"/secure/CreateIdea"})
public class CreateIdeaServlet extends HttpServlet {

    @EJB
    IdeaBean ideaBean;
    
    
    @EJB
    RoomBean roombean;
    
    @EJB
    ValidationBean validationBean;
            
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Anfrage an dazugerhörige JSP weiterleiten
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Idea/CreateIdea.jsp");
        dispatcher.forward(request, response);
        
        // Alte Formulardaten aus der Session entfernen
        HttpSession session = request.getSession();
        session.removeAttribute("Rform");  
        RoomViewServlet.warning = "";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String link = request.getParameter("link");
        String picture = request.getParameter("upload");
        String price = request.getParameter("price");
        String like = "0";
        String dislike = "0";
        List<UserEntry> users = new ArrayList<>();
        long id = Long.parseLong(request.getParameter("id"));
        RoomEntry room = roombean.findRoom(id);
        IdeaEntry idea = new IdeaEntry(like, dislike, name, price, description, link, picture, room, users);
        List<String> errors = this.validationBean.validate(idea);

        if (errors.isEmpty()) {
            // Keine Fehler: Raumansicht aufrufen
            this.ideaBean.CreateNewIdea(idea);
            response.sendRedirect(request.getContextPath() +  "/secure/RoomView?id=" + id);
        } else {
           
            response.sendRedirect(request.getContextPath() + "/secure/CreateIdea?id=" + id);
        }
        
    }
}
