/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.web;

import dhbw.se.giftit.ejb.UserBean;
import dhbw.se.giftit.ejb.ValidationBean;
import dhbw.se.giftit.exc.UserExsistsException;
import dhbw.se.giftit.jpa.UserEntry;
import java.io.IOException;
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
 * Servlet für die Registrierungsseite. Hier kann sich ein neuer Benutzer
 * registrieren. Anschließend wird der auf die Startseite weitergeleitet.
 */
@WebServlet(urlPatterns = {"/signup/"})
public class SignUpServlet extends HttpServlet {
    
    @EJB 
    ValidationBean valBean;
    
    @EJB
    UserBean userBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        // Anfrage an dazugerhörige JSP weiterleiten
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login/signup.jsp");
        dispatcher.forward(request, response);
        
        // Alte Formulardaten aus der Session entfernen
        HttpSession session = request.getSession();
        session.removeAttribute("Rform");       
    }
    
      @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        // Formulareingaben auslesen        
        String uname = request.getParameter("bname");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        
        
        // Eingaben prüfen
        UserEntry user = new UserEntry(uname, password1);
        List<String> errors = this.valBean.validate(user);
        
        this.valBean.validate(user.getPassword(), errors);
        
        if (password1 != null && password2 != null && !password1.equals(password2)) {
            errors.add("Überprüfe die Eingabe beider Passwörter!");
        }
        
        // Neuen Benutzer anlegen
        if (errors.isEmpty()) {
            try {
                this.userBean.registerUser(user);
            } catch (UserExsistsException ue) {
                errors.add(ue.getMessage());
            }
        }
       
        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            response.sendRedirect(request.getContextPath() +  "/secure/RoomOverview");
        } else {
            // Fehler: Formuler erneut anzeigen
            FormData formValues = new FormData();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);
            
            HttpSession session = request.getSession();
            session.setAttribute("Rforms", formValues);
            
            response.sendRedirect(request.getContextPath() + "/signup/");
        }
    }
}
