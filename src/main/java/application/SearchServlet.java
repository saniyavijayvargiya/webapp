package application;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;


@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
    	String searchname = req.getParameter("searchname").toLowerCase();
    	
    	Properties prop = new Properties();
		InputStream input = new FileInputStream("C:\\Users\\Saniya\\eclipse-workspace\\application\\resources\\system.properties");
		prop.load(input);
		
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String pass = prop.getProperty("password");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, pass);
			
			PreparedStatement st = con.prepareStatement("select * from details where lower(fname)=? or lower(lname)=?;");
			st.setString(1, searchname);
			st.setString(2, searchname);
			ResultSet rs = st.executeQuery();
			
			
			ArrayList<ArrayList<String>> rows = new ArrayList();
			
			while (rs.next()) {
				ArrayList<String> row = new ArrayList();
				for (int i = 1; i <= 8; i++) {
					row.add(rs.getString(i));
				}
				rows.add(row);
			}
			
			req.getSession().setAttribute("searchlist", rows);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/searchresults.jsp");
			rd.forward(req,res);	
			
			
//			res.setContentType("text/html");
//			PrintWriter out = res.getWriter();
//			
//			if (rs.next()) {
//				out.println("<table>");
//				out.println("<tr>");
//				out.println("<th>First Name</th>");
//				out.println("<th>Last Name</th>");
//				out.println("<th>Address</th>");
//				out.println("<th>City</th>");
//				out.println("<th>State</th>");
//				out.println("<th>Zipcode</th>");
//				out.println("<th>Phone Number</th>");
//				out.println("<th>Email</th>");
//				
//				do {
//					out.println("<tr>");
//					out.println("<td>" + rs.getString(1) + "</td>");
//					out.println("<td>" + rs.getString(2) + "</td>");
//					out.println("<td>" + rs.getString(3) + "</td>");
//					out.println("<td>" + rs.getString(4) + "</td>");
//					out.println("<td>" + rs.getString(5) + "</td>");
//					out.println("<td>" + rs.getString(6) + "</td>");
//					out.println("<td>" + rs.getString(7) + "</td>");
//					out.println("<td>" + rs.getString(8) + "</td>");
//					
//				} while (rs.next());
//				
//			} else {
//				out.println("No matching records found.");
//			}
			
			
		} catch (Exception exc) {
			System.out.println(exc);
		}
		
		
    	
	}

}
