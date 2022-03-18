package webApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class suppression
 */
@WebServlet("/suppression")
public class suppression extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public suppression() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String id= request.getParameter("id");
		//   String quantite= request.getParameter("quantite");
		  
		  
			
		Statement stmt;
		try {
			stmt = conn.createStatement();
		 
		String req= ("SELECT * FROM produit where id='"+id+"'  " );
		ResultSet rs = stmt.executeQuery(req);
		
		
		if(rs.next())
		{
			 
	        
		   out.println(" ce produit est supprimée" );}
		   else 
		   {
			   out.print("ce produit n'existe pas ");
		   
		}
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String id= request.getParameter("id");
	
		  
		   
			try {
			int st=0;
				PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM `produit` WHERE id=?; ");
			
			     stmt1.setString(1,id);
				
		

			
		    st=stmt1.executeUpdate();
		//ResultSet rs = st.executeQuery();
			
			//while(rs.next())
			//{
			out.print("suppression avec succée");	
			//}
			}
			catch (Exception c) { System.out.println ("problème SQL"+c);}
	}

}
