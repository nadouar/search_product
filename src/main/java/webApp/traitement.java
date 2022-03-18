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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class traitement
 */
@WebServlet("/traitement")
public class traitement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public traitement() {
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
				
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String id= request.getParameter("id");
		   String quantite= request.getParameter("quantite");
		  
		  
			
		Statement stmt;
		try {
			stmt = conn.createStatement();
		 
		String req= ("SELECT * FROM produit where id='"+id+"'  " );
		ResultSet rs = stmt.executeQuery(req);
		
		
		if(rs.next())
		{
			 
	        
		   out.println(" ce produit est existe" );}
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String id= request.getParameter("id");
		   String nom= request.getParameter("nom");
		   String quantite= request.getParameter("quantite");
		  
		   
			try {
			int st=0;
				PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO `produit`(`id`, `nom`, `quantite`) VALUES (?,?,?)");
			
			     stmt1.setString(1,id);
				 stmt1.setString(2,nom);
				 stmt1.setString(3,quantite);
		

			
		    st=stmt1.executeUpdate();
		//ResultSet rs = st.executeQuery();
			
			//while(rs.next())
			//{
			out.print("ajout succe");	
			//}
			}
			catch (Exception c) { System.out.println ("problème SQL"+c);}
			


		}
	}
		