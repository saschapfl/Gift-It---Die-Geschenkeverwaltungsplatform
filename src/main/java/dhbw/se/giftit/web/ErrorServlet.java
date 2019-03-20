/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author spfli
 */
@WebServlet(name = "ErrorServlet", urlPatterns = {"/secure/Error"})
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String textId = request.getParameter("textId");
        String text = "";
        switch (textId) {
            case "1":
                text = "Sie haben keine Berechtigung auf diesen Raum!";
                break;
            case "2":
                text = "Sie haben keine Berechtigung auf diese Idee!";
                break;
            case "3":
                text = "Die von Ihnen angefragte Ressource kann leider nicht gefunden werden!";
                break;
        }
        request.setAttribute("text", text);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
        dispatcher.forward(request, response);
    }
}
