/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.web;

import dhbw.se.giftit.ejb.RoomBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sven
 */
@WebServlet(name = "DeleteRoomServlet", urlPatterns = {"/secure/deleteRoom"})
public class DeleteRoomServlet extends HttpServlet {

   @EJB
   RoomBean roomBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        roomBean.deleteRoom(Long.parseLong(request.getParameter("id")));
        response.sendRedirect(request.getContextPath() + "/secure/RoomOverview");
    }
}
    