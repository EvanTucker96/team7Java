package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class AgentLogin
 */
@WebServlet("/AgentLogin")
public class AgentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/travelexperts";
	private String dbUser = "root";
	private String dbPass = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgentLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String UserId = request.getParameter("UserId");
		String Password = request.getParameter("Password");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
			PreparedStatement stmt = conn.prepareStatement("select password from agents where UserId=?");
			stmt.setString(1, UserId);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			if(rs.next()) {
				if(Password.contentEquals(rs.getString(1)))
				{
					session.setAttribute("loginStatus", true);
					response.sendRedirect("Welcome.jsp");
				}
				else
				{
					session.setAttribute("message", "User ID or Password is Incorrect");
					response.sendRedirect("AgentLogin.jsp");
				}
			}
			else
			{
				session.setAttribute("message", "User ID or Password is Incorrect");
				response.sendRedirect("AgentLogin.jsp");
			}
		}catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
