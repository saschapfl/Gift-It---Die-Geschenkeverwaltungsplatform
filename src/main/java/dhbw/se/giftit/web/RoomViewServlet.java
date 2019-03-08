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
   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
  
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
            request.getRequestDispatcher("/WEB-INF/Room/RoomView.jsp").forward(request, response);
            session.setAttribute("id", id);
        }
       
    }    

}
