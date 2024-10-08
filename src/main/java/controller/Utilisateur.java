package controller;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utilisateur {
	protected String mdp;
	protected String login;
	
	public Utilisateur(String l,String m) {
		mdp=m;
		login=l;
	}
	
	public boolean verifClient() throws SQLException, ClassNotFoundException {
		String dbName="TPArchi";
		String login="root";
		String mdp="0000";
		String url="jdbc:mysql://127.0.0.1:3306/"+dbName;
	 	Connection connection = null;
	 	PreparedStatement statement = null;
        ResultSet resultSet = null;
        int rowCount = 0;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,login,mdp);
			String query = "SELECT id FROM Client WHERE login=? and password=?";
			statement = connection.prepareStatement(query);
	        statement.setString(1,this.login);
	        statement.setString(2,this.mdp);
	        resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            rowCount++;
	        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return (rowCount>0);
	}
	public boolean addNewUser(String loginU,String password) throws ClassNotFoundException, SQLException {
		String dbName="TPArchi";
		String login="root";
		String mdp="0000";
		String url="jdbc:mysql://127.0.0.1:3306/"+dbName;
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        
		boolean added=false;
		if(verifClient()==false) {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,login,mdp);

            // Prepare the SQL statement
            String sql = "INSERT INTO Client (login,password) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sql);

            // Set values for parameters
            preparedStatement.setString(1, loginU); 
            preparedStatement.setString(2, password); 
            
            // Execute the SQL statement
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }} catch (ClassNotFoundException e) {
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
        added=true;
		}
		return added;}
	

	
	
	public void consulterLivres() {
			String dbName="TPArchi";
			String login="root";
			String mdp="0000";
			String url="jdbc:mysql://127.0.0.1:3306/"+dbName;
		 	Connection connection = null;
	        Statement statement = null;
	        ResultSet resultSet = null;

	        try {

	        	Class.forName("com.mysql.cj.jdbc.Driver");
				connection=DriverManager.getConnection(url,login,mdp);

	            // Create a SQL statement
	            statement = connection.createStatement();

	            // Execute the query
	            resultSet = statement.executeQuery("SELECT * FROM Livre");

	            // Process the result set
	            while (resultSet.next()) {
	                
	                String column1Value = resultSet.getString("isbn");
	                String column2Value = resultSet.getString("auteur");
	                String column3Value = resultSet.getString("titre");
	                String column4Value = resultSet.getString("annee");
	                String column5Value = resultSet.getString("prix");
	                // Display the data
	                System.out.println("isbn: " + column1Value + ", auteur: " + column2Value+ ", titre: " + column3Value+ ", annee: " + column4Value+ ", prix: " + column5Value);
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
	    }
	public void consulterLivre(String isbnL) {
		String dbName="TPArchi";
		String login="root";
		String mdp="0000";
		String url="jdbc:mysql://127.0.0.1:3306/"+dbName;
	 	Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

        	Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,login,mdp);

            // Create a SQL statement
            statement = connection.createStatement();

            // Execute the query
            resultSet = statement.executeQuery("SELECT * FROM Livre where isbn="+isbnL);

            // Process the result set
            while (resultSet.next()) {
                
                String column1Value = resultSet.getString("isbn");
                String column2Value = resultSet.getString("auteur");
                String column3Value = resultSet.getString("titre");
                String column4Value = resultSet.getString("annee");
                String column5Value = resultSet.getString("prix");
                // Display the data
                System.out.println("isbn: " + column1Value + ", auteur: " + column2Value+ ", titre: " + column3Value+ ", annee: " + column4Value+ ", prix: " + column5Value);
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
    }
	
}
	

