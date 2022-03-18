package webApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class connexion
 */
@WebServlet("/connexion")
public class connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	Connection conn;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		

    	try {
    		Class.forName ("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection ("jdbc:mysql://localhost:3307/tp2java?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root", "");
    		}
    		catch (Exception c) { System.out.println ("problème SQL"+c);
    		
    		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String login= request.getParameter("login");
		   String mdp= request.getParameter("mdp");
		  
		  
			
		Statement stmt;
		try {
			stmt = conn.createStatement();
		 
		String req= ("SELECT * FROM user where login='"+login+"' and password='"+mdp+"' " );
		ResultSet rs = stmt.executeQuery(req);
		if(rs.next())
		{
			 HttpSession session = request.getSession();
			    session.setAttribute("login", login);
	         session.setAttribute("conn",this.conn);
		   out.println(" Bonjour M. " + login + ".");out.print("<a href> deconnecter </a>");}
		   else 
		   {
			   out.print("vous n'etes pas authentifie");
		   
		}
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	
}
