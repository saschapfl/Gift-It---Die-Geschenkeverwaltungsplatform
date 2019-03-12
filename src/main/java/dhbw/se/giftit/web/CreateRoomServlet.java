/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.web;

import dhbw.se.giftit.ejb.RoomBean;
import dhbw.se.giftit.ejb.UserBean;
import dhbw.se.giftit.jpa.RoomEntry;
import dhbw.se.giftit.jpa.UserEntry;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CreateRoomServlet", urlPatterns = {"/secure/createRoom"})
public class CreateRoomServlet extends HttpServlet {


    @EJB
    UserBean userbean;
    
    @EJB
    RoomBean roombean;

    public static List<UserEntry> allParticipants = new ArrayList<UserEntry>();
    public static String error;
    public static List<String> form_data = new ArrayList<String>();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        request.setCharacterEncoding("UTF-8");
         // Anfrage an dazugerhörige JSP weiterleiten
        request.setAttribute("participants", allParticipants);
        request.setAttribute("error", error);
        request.setAttribute("form_data", form_data);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Room/CreateRoom.jsp");
        dispatcher.forward(request, response);
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        //Grundsätzliches (gedrückten button abfragen und Formulardaten speichern)
       String buttonname = request.getParameter("button");
       form_data.clear();
       form_data.add(request.getParameter("roomname"));
       form_data.add(request.getParameter("deadlineCollection"));
       form_data.add(request.getParameter("deadlineRating"));
       
       //<editor-fold defaultstate="collapsed" desc="Teilnehmer adden">
       if(buttonname.equals("add_participant")){
           error = "";
           String uname = request.getParameter("participant");
           if(uname.equals(userbean.getUser().getUsername())){
               error = "Sie als Ersteller sind automatisch im Raum";
           }
           else{
               UserEntry new_participant = userbean.getUserByUname(uname);
               if(new_participant == null){
                   error = "Dieser Benutzer ist nicht vorhanden";
               }
               else{
                   
                   //Alogrithmus zum prüfen ob username gleich ist
                   boolean twin = false;
                   if(allParticipants != null){
                    for(UserEntry e:allParticipants){
                        if(e.getUsername().equals(new_participant.getUsername())){
                            twin = true;
                        }
                    }
                   }
                   else{
                       allParticipants = new ArrayList<UserEntry>();
                   }
                   if(!twin){
                   allParticipants.add(new_participant);
                   
                   }
                   else{
                       error = "Sie haben diesen Teilnehmer bereits hinzugefügt";
                   }
               }
           }
           response.sendRedirect(request.getContextPath() + "/secure/createRoom");
       }
//</editor-fold>
       

       
       //<editor-fold defaultstate="collapsed" desc="Raum erstellen">
       else if(buttonname.equals("add_room")){
           if(request.getParameter("roomname").trim().equals("")){
               error = "Bitte geben Sie einen Raumnamen ein";
               response.sendRedirect(request.getContextPath() + "/secure/createRoom");
           }
           else{
            String date = request.getParameter("deadlineCollection");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try{
                Date deadline1 = format.parse(date);
                date = request.getParameter("deadlineRating");
                Date deadline2 = format.parse(date);
                Date now = new Date();

                if(deadline1.compareTo(deadline2) >= 0){
                    error = "Die Deadline zum Sammeln von Ideen muss mindestens einen Tag vor der Bewertung stattfinden!";
                    response.sendRedirect(request.getContextPath() + "/secure/createRoom");
                }
                else if(deadline1.compareTo(now) < 0 || deadline2.compareTo(now) < 0){
                    error = "Die Deadlines dürfen frühestens einen Tag nach Erstellung des Raumes erfolgen";
                    response.sendRedirect(request.getContextPath() + "/secure/createRoom");
                }
                else{
                 String name = request.getParameter("roomname");
                 allParticipants.add(0, userbean.getUser());
                 RoomEntry new_room = new RoomEntry(name, deadline1, deadline2, 0, null, allParticipants);
                 roombean.createNewRoom(new_room);
                 allParticipants.clear();
                 error = "";
                 form_data.clear();
                response.sendRedirect(request.getContextPath() + "/secure/RoomOverview");
               }
            }
            catch(ParseException pe){
                error = "Bitte geben Sie gültige Deadlines an!";
                response.sendRedirect(request.getContextPath() + "/secure/createRoom");
            }
           
           }
       }
//</editor-fold>

               //<editor-fold defaultstate="collapsed" desc="Teilnehmer entfernen">
       else{
           String uname = buttonname;
           if(allParticipants != null){
                for(UserEntry user:allParticipants){
                    if(uname.equals(user.getUsername())){
                        allParticipants.remove(user);                       
                        break;
                    }
                }
           }
           response.sendRedirect(request.getContextPath() + "/secure/createRoom");
       }
//</editor-fold>
    }


}
