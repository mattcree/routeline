package RouteLine1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignInServeLet
 */
@WebServlet("/SignInServLet")
public class SignInServLet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServLet() {
        super();
    }

	/**
	 * When ID and password are submitted and have passed the AdminSignIn validation this method verifies that the ID and password are correct
	 * and then creates a session object with ID as Attribute and redirects to the welcome page. 
	 * If not the login page is called up again.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empID = request.getParameter("empID");
		String password = request.getParameter("password");
		String url = "jdbc:mysql://localhost:3306/routeline?verifyServerCertificate=false&useSSL=true";
		String user = "root"; // Standard user name for MySql databases. 
		String pass = "Morpeth01"; // Password for DB instance
		String query = "select * from admins";
		boolean match = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//This loads the driver. Requires provision of qualified driver name. Throws classNotFound Exception.
			// This creates the connection object. Connection is an Interface though and requires a separate class or method to create the object.
			// The static getConnection method requires three parameters, url, username and password of the relevant DB instance.
			Connection con = DriverManager.getConnection(url, user, pass);
			// Statement is also an Interface 
			Statement st = con.createStatement();
			// Here we store the results, including the table structure in a ResultSet object.
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				// For testing results in console
				System.out.println("Input: " + empID + " : "+ password);
				System.out.println(match + ":" + rs.getString(1) + ":" + rs.getString(5));
				
				if(rs.getString(1).equals(empID) && rs.getString(5).equals(password)){
					match = true;
					break;
				}else {
					match = false;
				}
			}
			
		} catch (SQLException e) {
			System.out.println(e + e.getMessage());
			
		} catch (ClassNotFoundException e) {
			System.out.println(e + e.getMessage());
		}
		
		System.out.println(match);
		if(match)
		{
			HttpSession session = request.getSession();
			session.setAttribute("ID", empID);
			System.out.println("Seems to be true"); // For testing results in console
			response.sendRedirect("AdminWelcome.jsp");
			
		}else{
			System.out.println("Seems to be false"); // For testing results in console
			response.sendRedirect("AdminSignIn.jsp");
		}
	}
}
