package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Administrateur extends Utilisateur{
	
	public Administrateur(String login,String mdp) {
		super(login,mdp);
	}
public boolean verifAdmin() {
		
		if ((this.login.equals("Admin") && this.mdp.equals("abc123"))) return true;
		return false;
	}
	public void ajouterLivre(Livre l) {
		String dbName="TPArchi";
		String login="root";
		String mdp="0000";
		String url="jdbc:mysql://127.0.0.1:3306/"+dbName;
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,login,mdp);

            // Prepare the SQL statement
            String sql = "INSERT INTO Livre (isbn,auteur,titre,annee,prix) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            // Set values for parameters
            preparedStatement.setString(1, l.isbn); 
            preparedStatement.setString(2, l.auteur); 
            preparedStatement.setString(3, l.titre);
            preparedStatement.setInt(4, l.annee);
            preparedStatement.setFloat(5, l.prix);
            // Execute the SQL statement
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	public void supprimerLivre(String isbnL) {
		String dbName="TPArchi";
		String login="root";
		String mdp="0000";
		String url="jdbc:mysql://127.0.0.1:3306/"+dbName;
		Connection connection = null;
        Statement statement = null;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,login,mdp);

            // Create a SQL statement
            statement = connection.createStatement();

            // Execute the delete query
            int rowsAffected = statement.executeUpdate("DELETE FROM Livre WHERE isbn="+isbnL);

            // Display the number of rows affected
            System.out.println("Rows affected: " + rowsAffected);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block
            try {
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
	}
}
