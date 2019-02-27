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
public class RoomViewServlet extends HttpServlet {
    
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String button = request.getParameter("button");
         switch (button) {
            case "createIdea":
               response.sendRedirect(request.getContextPath() + "/CreateIdeaServlet");
                break;
            case "deleteIdea":
                 response.sendRedirect(request.getContextPath() + "/IdeaViewServlet");
                break;
            case "deleteRoom":
                roomBean.deleteRoom(Long.parseLong(request.getParameter("id")));
                break;
            default:
                break;
        }

        request.getRequestDispatcher("/WEB-INF/some-result.jsp").forward(request, response);
    }
}

