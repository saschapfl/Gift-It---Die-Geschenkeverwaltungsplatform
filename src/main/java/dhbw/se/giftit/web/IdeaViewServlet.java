/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.web;

import dhbw.se.giftit.ejb.IdeaBean;
import dhbw.se.giftit.ejb.RoomBean;
import dhbw.se.giftit.jpa.IdeaEntry;
import dhbw.se.giftit.jpa.RoomEntry;
import java.io.IOException;
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
@WebServlet(name = "IdeaViewServlet", urlPatterns = {"/secure/IdeaView"})
public class IdeaViewServlet extends HttpServlet {

 @EJB
 IdeaBean ideaBean;
 @EJB
RoomBean roomBean;
 
 IdeaEntry idea = new IdeaEntry();
 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
        long id = Long.parseLong(request.getParameter("id"));
        idea = ideaBean.findIdea(id);
        
        HttpSession session = request.getSession();
        session.setAttribute("idea_name", idea.getName());
        session.setAttribute("price", idea.getPrice());
        session.setAttribute("link", idea.getLink());
        session.setAttribute("description", idea.getDescription());
       // Anfrage an dazugerhörige JSP weiterleiten
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Idea/IdeaView.jsp");
        dispatcher.forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RoomEntry room = new RoomEntry();
        long idr = idea.getRoom().getId();
        String button = request.getParameter("button");
        
        long id = Long.parseLong(request.getParameter("id"));
        idea = ideaBean.findIdea(id);
       
        switch (button) {
            case "deleteIdea":
                ideaBean.deleteIdea(Long.parseLong(request.getParameter("id")));
                 response.sendRedirect(request.getContextPath() +  "/secure/RoomView?id=" + idr);
                break;
        }

    }
    
    }




