package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginAdmin")
public class LoginAdmin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Administrateur admin = new Administrateur(login, password);
        
        
        
        boolean isAdmin = false;
 
        try {
            isAdmin = admin.verifAdmin();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Rediriger en fonction du résultat de l'authentification
        if (isAdmin) {
        	RequestDispatcher dispatch =request.getRequestDispatcher("AdminTrue.jsp");
        	dispatch.forward(request, response);
        }
        	else {
        		RequestDispatcher dispatch =request.getRequestDispatcher("clientFalse.jsp");
            	dispatch.forward(request, response);
        	}
        
        
    }
       
    

}
