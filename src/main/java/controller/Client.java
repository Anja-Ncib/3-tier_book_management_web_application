package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Client extends Utilisateur{

	public Client(String l, String m) {
		super(l, m);
	}
	
	
	public String livresCommande() {
		String l=super.login;
		String dbName="TPArchi";
		String login="root";
		String mdp="0000";
		String url="jdbc:mysql://127.0.0.1:3306/"+dbName;
	 	Connection connection = null;
	 	PreparedStatement statement = null;
        ResultSet resultSet = null;
        String columnValue="";
        try {

        	Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,login,mdp);

			String query = "SELECT livres_commandes FROM Client WHERE login=?";
			statement = connection.prepareStatement(query);
	        statement.setString(1, l);
	        resultSet = statement.executeQuery();
            while (resultSet.next()) {
                
                 columnValue = resultSet.getString("livres_commandes");
                
                // Display the data
                
            }
	
        	} catch (ClassNotFoundException e) {
        			e.printStackTrace();
        	} catch (SQLException e) {
        			e.printStackTrace();
        	} finally {
        		// Close resources in the finally block
        	try {
        		if (resultSet != null) {
        			resultSet.close();
        		}
        		if (statement != null) {
        			statement.close();
        		}
        		if (connection != null) {
        			connection.close();
        		}
        	} catch (SQLException e) {
        		e.printStackTrace();
        }
    }
        return columnValue;
        
}
	
	public void commanderLivre(String isbnL) throws ClassNotFoundException {
		String livres=livresCommande()+" "+isbnL;
		String l=super.login;
		String dbName="TPArchi";
		String login="root";
		String mdp="0000";
		String url="jdbc:mysql://127.0.0.1:3306/"+dbName;
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,login,mdp);
			String updateQuery = "UPDATE Client SET livres_commandes = ? WHERE login='"+l+"'";
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			statement.setString(1, livres);
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
                System.out.println("Column updated successfully.");
            } else {
                System.out.println("Column update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		}
	}


