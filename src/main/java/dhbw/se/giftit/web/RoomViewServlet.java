/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.web;

import dhbw.se.giftit.ejb.IdeaBean;
import dhbw.se.giftit.ejb.RoomBean;
import dhbw.se.giftit.ejb.UserBean;
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

/**
 *
 * @author Viktoria
 */
@WebServlet(name = "RoomView", urlPatterns = {"/secure/RoomView"})
public class RoomViewServlet extends HttpServlet {
    
    @EJB
    IdeaBean ideaBean;
    
    @EJB
    RoomBean roomBean;
    
    @EJB
    UserBean userbean;
    
    public static String warning;
   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Warning setzen
        request.setAttribute("warning", warning);
        
        // Room und Ideas zu Room holen
        String sid = request.getParameter("id");
        long id = Long.parseLong(sid);
        RoomEntry room = this.roomBean.findRoom(id);
        //Teilnehmer anzeigen
        request.setAttribute("participants", room.getUsers());
        UserEntry current_user = userbean.getUser();
        if(!current_user.getRaeume().contains(room)){
            response.sendRedirect(request.getContextPath() + "/secure/RoomOverview?accessdenied=true");
        }
        else{
            List<IdeaEntry> roomideas = room.getIdeas();

            // Session holen und Ideas an jsp weiterleiten
            HttpSession session = request.getSession();
            session.setAttribute("entries", roomideas);
            
            //Teilnehmerverwaltung für den Ersteller aktivieren, für alle anderen deaktivieren
            if(current_user.getUsername().equals(room.getUsers().get(0).getUsername())){
                String a = "true";
                request.setAttribute("owner", a);
            }
            session.setAttribute("id", id);
            request.getRequestDispatcher("/WEB-INF/Room/RoomView.jsp").forward(request, response);
            
        }
       
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        warning = "";
        String buttonname = request.getParameter("button");
        UserEntry user= userbean.getUserByUname(buttonname);
        long id = Long.parseLong(request.getParameter("id"));
        RoomEntry room = roomBean.findRoom(id);
        List<UserEntry> users = room.getUsers();
        if(buttonname.equals("add_user")){
            UserEntry userToAdd = userbean.getUserByUname(request.getParameter("new_part"));
            if(userToAdd == null){
                warning = "Dieser Benutzer ist nicht vorhanden!";
                response.sendRedirect(request.getRequestURI() + "?id=" + id);
            }
            else{
                for(UserEntry ue : users){
                    if(ue.getUsername().equals(userToAdd.getUsername())){
                        warning = "Dieser Benutzer ist bereits im Raum!";
                        response.sendRedirect(request.getRequestURI() + "?id=" + id);
                    }
                }
                //Wenn kein Fehler vorhanden ist, speichere den User im Raum
                if(warning.isEmpty()){
                    users.add(userToAdd);
                    room.setUsers(users);
                    roomBean.updateRoom(room);
                    response.sendRedirect(request.getRequestURI() + "?id=" + id);
                }
            }
        }
        else{
            if(users.get(0).getUsername().equals(user.getUsername())){
                warning = "Sie können sich selbst nicht aus dem Raum entfernen! Um den Raum zu löschen benutzen Sie bitte den Button 'Raum löschen'";              
                response.sendRedirect(request.getRequestURI() + "?id=" + id);
            }
            else{
                UserEntry deleted_user = null;
                for(UserEntry ue : users){
                    if(ue.getUsername().equals(user.getUsername())){
                        deleted_user = ue;
                    }
                }
                users.remove(deleted_user);
                room.setUsers(users);
                roomBean.updateRoom(room);

                response.sendRedirect(request.getRequestURI() + "?id=" + id);
            }
        }
    }

}
