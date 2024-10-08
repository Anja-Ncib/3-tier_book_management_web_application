package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ClientLoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
		String buttonClicked = request.getParameter("button");
		
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Client client = new Client(login, password);
        if ("button1".equals(buttonClicked)) {
        
        
        boolean isClient = false;
 
        try {
            isClient = client.verifClient();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Rediriger en fonction du résultat de l'authentification
        if (isClient) {
        	RequestDispatcher dispatch =request.getRequestDispatcher("clientTrue.jsp");
        	dispatch.forward(request, response);
        }
        	else {
        		RequestDispatcher dispatch =request.getRequestDispatcher("clientFalse.jsp");
            	dispatch.forward(request, response);
        	}
        }
        if ("button2".equals(buttonClicked)) {
        	try {
				if (client.addNewUser(login, password)) {
				RequestDispatcher dispatch =request.getRequestDispatcher("new.jsp");
				dispatch.forward(request, response);}
				else {
					RequestDispatcher dispatch =request.getRequestDispatcher("NewFalse.jsp");
					dispatch.forward(request, response);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
    }

}
