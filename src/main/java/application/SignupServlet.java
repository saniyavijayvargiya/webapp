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
import java.util.ResourceBundle;


@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zipcode = req.getParameter("zipcode");
		String phone = req.getParameter("phone");
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
			
			PreparedStatement st = con.prepareStatement("select * from details where email=?;");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			
			if (rs.next()) {
				out.println("<meta http-equiv='refresh' content='3;URL=login.jsp'>"); //redirects after 3 seconds
				out.println("<p>This email already exists. Please sign in. Redirecting... </p>");
				
			} else {
				PreparedStatement st1 = con.prepareStatement("insert into login values(?, ?);");
				st1.setString(1, email);
				st1.setString(2, password);
				int count1 = st1.executeUpdate();
				System.out.println(count1 + " row(s) affected");
				
				PreparedStatement st2 = con.prepareStatement("insert into details values (?, ?, ?, ?, ?, ?, ?, ?);");
				st2.setString(1, fname);
				st2.setString(2, lname);
				st2.setString(3, address);
				st2.setString(4, city);
				st2.setString(5, state);
				st2.setString(6, zipcode);
				st2.setString(7, phone);
				st2.setString(8, email);
				System.out.println(st2);
				int count2 = st2.executeUpdate();
				System.out.println(count2 + " row(s) affected");
				
				st1.close();
				st2.close();
				con.close();
				
				out.println("<meta http-equiv='refresh' content='3;URL=login.jsp'>"); //redirects after 3 seconds
				out.println("<p>Signed up successfully. Login to continue. Redirecting... </p>");
			}
			
		} catch (Exception exc) {
			System.out.println(exc);
		}	
	}
}
