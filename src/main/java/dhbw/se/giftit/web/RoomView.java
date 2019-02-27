/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.web;

import dhbw.se.giftit.ejb.IdeaBean;
import dhbw.se.giftit.ejb.RoomBean;
import dhbw.se.giftit.jpa.IdeaEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
@WebServlet(name = "RoomView", urlPatterns = {"/RoomView"})
public class RoomView extends HttpServlet {
    
    @EJB
    IdeaBean ideaBean;
    
    @EJB
    RoomBean roomBean;
    
     
   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Dashboard-Rubriken und Kacheln erzeugen und im Request Context ablegen
        List<IdeaEntry> sections = new ArrayList<>();
        request.setAttribute("sections", sections);
        
        

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/dashboard/dashboard.jsp").forward(request, response);
    }
    
     @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Neuen Eintrag speichern
      //  if (fehler.isEmpty()) {
     //       this.ideaBean.createNewIdea(name);
        }
        
        // Browser auffordern, die Seite neuzuladen
    //    response.sendRedirect(request.getContextPath());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
