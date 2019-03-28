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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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

        request.setCharacterEncoding("UTF-8");
        //Warning setzen
        request.setAttribute("warning", warning);

        // aktuellen User holen
        String user = userbean.getUser().getUsername();
        request.setAttribute("user", user);
        // Room und Ideas zu Room holen
        String sid = request.getParameter("id");
        long id = Long.parseLong(sid);
        RoomEntry room = this.roomBean.findRoom(id);

        //Wenn es Raum nicht gibt, Weiterleitung an Fehlerseite
        if (room == null) {
            response.sendRedirect(request.getContextPath() + "/secure/Error?textId=3");
        } else {
            //Teilnehmer anzeigen
            request.setAttribute("participants", room.getUsers());
            UserEntry current_user = userbean.getUser();
            if (!current_user.getRaeume().contains(room)) {
                response.sendRedirect(request.getContextPath() + "/secure/Error?textId=1");
            } else {
                List<IdeaEntry> roomideas = room.getIdeas();
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY");

                Date now = new Date();
                Date date1 = room.getDeadlineCollection();
                Date date2 = room.getDeadlineRating();
                // Testdatum
                Date date3 = null;
                try {
                    date3 = format.parse("10.02.2019");
                } catch (ParseException ex) {
                    Logger.getLogger(RoomViewServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                long diff1 = date1.getTime() - now.getTime();
                long diff2 = date2.getTime() - now.getTime();

                long days1 = diff1 / (1000 * 60 * 60 * 24) + 1;
                long days2 = diff2 / (1000 * 60 * 60 * 24) + 1;


                //Unterschiedliche Fälle für die Weite der Timeline
                if (days2 <= 0) {
                    // Nur noch die best bewertete Idee anzeigen
                    IdeaEntry bestIdea = roomideas.get(0);
                    for (IdeaEntry entry : roomideas) {
                        if (entry.getLike() - entry.getDislike() > bestIdea.getLike() - bestIdea.getDislike()) {
                            IdeaEntry bestIdea2 = bestIdea;
                            bestIdea = entry;
                            ideaBean.deleteIdea(bestIdea2.getId());
                        }
                    }
                    roomideas.clear();
                    roomideas.add(bestIdea);
                    room.setIdeas(roomideas);
                    roomBean.updateRoom(room);
                    // Timeline anpassen
                    request.setAttribute("timeline1", "width: 100%;");
                    request.setAttribute("timeline2", "width: 100%;");
                    request.setAttribute("timelinetext1", "Sammlungsfrist erreicht");
                    request.setAttribute("timelinetext2", "Abstimmungsfrist erreicht");

                    //Check Symbol
                    request.setAttribute("deadline1check", "true");
                    request.setAttribute("deadline2check", "true");
                } else if (days1 <= 0) {
                    request.setAttribute("timeline1", "width: 100%;");
                    request.setAttribute("timeline2", "width: " + (100 - days2) + "%;");
                    if (days2 > 100) {
                        request.setAttribute("timeline2", "width: 0%");
                    }
                    request.setAttribute("timelinetext1", "Sammlungsfrist erreicht");
                    request.setAttribute("timelinetext2", "Noch " + days2 + " Tage");

                    //Check Symbol
                    request.setAttribute("deadline1check", "true");
                } else {
                    request.setAttribute("timeline1", "width: " + (100 - days1) + "%;");
                    request.setAttribute("timeline2", "width: 0%");
                    if (days1 > 100) {
                        request.setAttribute("timeline1", "width: 0%");
                    }
                    request.setAttribute("timelinetext1", "Noch " + days1 + " Tage");
                    request.setAttribute("timelinetext2", "");
                }

                request.setAttribute("deadline1", format.format(date1));
                request.setAttribute("deadline2", format.format(date2));
                // Session holen und Ideas an jsp weiterleiten
                HttpSession session = request.getSession();
                session.setAttribute("entries", roomideas);

                //Teilnehmerverwaltung für den Ersteller aktivieren, für alle anderen deaktivieren
                if (current_user.getUsername().equals(room.getUsers().get(0).getUsername())) {
                    String a = "true";
                    request.setAttribute("owner", a);
                }
                session.setAttribute("id", id);

                //Gesamtbudget ermitteln und anzeigen
                double budget = room.getEntireBudget();
                request.setAttribute("budget", budget);

                //Userbudget ermitteln und anzeigen
                double user_budget = 0.0;
                if (room.getBudget().keySet().contains(current_user.getUsername())) {
                    user_budget = room.getBudget().get(current_user.getUsername());
                }
                //Als Session-Attribut, da durch die Post Anfrage des Budget hinzufügen Buttons
                //der Request-Parameter nicht gespeichert bleibt
                request.setAttribute("user_budget", user_budget);

                //Keyset der Map setzen um in der Teilnehmerliste User anzuzeigen, die bezahlt haben
                request.setAttribute("users_payed", room.getBudget().keySet());

                //Zwei Listen ermitteln und setzen, die darüber informieren, welche Like/dislike buttons blau sein müssen
                String likeids = "";
                String dislikeids = "";
                Map<String, String> votes = new HashMap<>();
                for (IdeaEntry idea : room.getIdeas()) {
                    votes = idea.getVotes();
                    if (votes.keySet().contains(current_user.getUsername())) {
                        if (votes.get(current_user.getUsername()).equals("like")) {
                            likeids = likeids + idea.getId() + ";";
                        } else {
                            dislikeids = dislikeids + idea.getId() + ";";
                        }
                    }
                }
                request.setAttribute("ideasLiked", likeids);
                request.setAttribute("ideasDisliked", dislikeids);

                request.getRequestDispatcher("/WEB-INF/Room/RoomView.jsp").forward(request, response);
            }
        }

    }

    @Override
    @Transactional
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        warning = "";
        String buttonname = request.getParameter("button");
        UserEntry user = userbean.getUserByUname(buttonname);
        long id = Long.parseLong(request.getParameter("id"));
        RoomEntry room = roomBean.findRoom(id);
        List<UserEntry> users = room.getUsers();

        if (buttonname != null) {
            if (buttonname.equals("add_user")) {
                UserEntry userToAdd = userbean.getUserByUname(request.getParameter("new_part"));
                if (userToAdd == null) {
                    warning = "Dieser Benutzer ist nicht vorhanden!";
                    response.sendRedirect(request.getRequestURI() + "?id=" + id);
                } else {
                    for (UserEntry ue : users) {
                        if (ue.getUsername().equals(userToAdd.getUsername())) {
                            warning = "Dieser Benutzer ist bereits im Raum!";
                            response.sendRedirect(request.getRequestURI() + "?id=" + id);
                        }
                    }
                    //Wenn kein Fehler vorhanden ist, speichere den User im Raum
                    if (warning.isEmpty()) {
                        users.add(userToAdd);
                        room.setUsers(users);
                        roomBean.updateRoom(room);
                        response.sendRedirect(request.getRequestURI() + "?id=" + id);
                    }
                }
            } else if (buttonname.equals("add_budget")) {
                double budgetToAdd = Double.parseDouble(request.getParameter("budget"));
                UserEntry current_user = userbean.getUser();
                Map<String, Double> budget = room.getBudget();
                if (budget == null) {
                    budget = new HashMap<>();
                }
                //Wenn der User bereits ein Budget eingegeben hat, altes Budget abziehen und neues hinzufügen
                if (budget.keySet().contains(current_user.getUsername())) {
                    double entire_budget = room.getEntireBudget();
                    entire_budget = entire_budget - budget.get(current_user.getUsername());
                    entire_budget = entire_budget + budgetToAdd;
                    room.setEntireBudget(entire_budget);
                } else {
                    room.setEntireBudget(room.getEntireBudget() + budgetToAdd);
                }
                budget.put(current_user.getUsername(), budgetToAdd);
                room.setBudget((HashMap<String, Double>) budget);
                roomBean.updateRoom(room);

                userbean.updateUserByObject(current_user);
                response.sendRedirect(request.getRequestURI() + "?id=" + id);
            } else {
                if (users.get(0).getUsername().equals(user.getUsername())) {
                    warning = "Sie können sich selbst nicht aus dem Raum entfernen! Um den Raum zu löschen benutzen Sie bitte den Button 'Raum löschen'";
                    response.sendRedirect(request.getRequestURI() + "?id=" + id);
                } else {
                    UserEntry deleted_user = null;
                    for (UserEntry ue : users) {
                        if (ue.getUsername().equals(user.getUsername())) {
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

        String idea_id = request.getParameter("like");
        String action = "like";
        if (idea_id == null) {
            idea_id = request.getParameter("dislike");
            action = "dislike";
        }
        if (idea_id == null) {
            idea_id = request.getParameter("delete");
            action = "delete";
        }

        if (idea_id != null) {
            ideaBean.performAction(idea_id, action);
            response.sendRedirect(request.getRequestURI() + "?id=" + id);
        }

    }

}
