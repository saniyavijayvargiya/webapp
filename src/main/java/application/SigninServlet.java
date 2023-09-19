package application;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;


@WebServlet("/signin")
public class SigninServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			Properties prop = new Properties();
			InputStream input = new FileInputStream("C:\\Users\\Saniya\\eclipse-workspace\\application\\resources\\system.properties");
			prop.load(input);
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String pass = prop.getProperty("password");
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, pass);
			
			PreparedStatement st = con.prepareStatement("select password from login where email=?;");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
		
			if (rs.next()) {
				String db_pass = rs.getString("password");
				
				if (db_pass.equals(password)) {
					System.out.println("you are logged in");
					
					HttpSession session = req.getSession(); //creates a session
					session.setAttribute("email", email);
					session.setMaxInactiveInterval(30*60); //timeout after 30 minutes
					
				} else {
					out.println("<meta http-equiv='refresh' content='3;URL=login.jsp'>"); //redirects after 3 seconds
					out.println("<p>Password is incorrect. Please try again! Redirecting again... </p>");
				}
			} else {
				out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
				out.println("<p>Given email address not found. Please sign up! Redirecting... </p>");
			}
				
			st.close();
			con.close();
		} catch (Exception exc) {
			System.out.println(exc);
		}	
	}

}
