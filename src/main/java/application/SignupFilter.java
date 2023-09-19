package application;

import jakarta.servlet.Filter;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;


@WebFilter("/signup")
public class SignupFilter extends HttpFilter implements Filter {
    
	public SignupFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirm = req.getParameter("confirm");
		String zip = req.getParameter("zipcode");
		String ph = req.getParameter("phone");
		
		if (fname == "") {
			out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); //redirects after 3 seconds
			out.println("<p>Please provide first name. Redirecting again... </p>");
		}
		else if (lname == "") {
			out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
			out.println("<p>Please provide last name. Redirecting again... </p>");
		}
		else if (email == "") {
			out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
			out.println("<p>Please provide email. Redirecting again... </p>");
		}
		else if (password == "") {
			out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
			out.println("<p>Please provide password. Redirecting again... </p>");
		}
		else if (confirm == "") {
			out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
			out.println("<p>Please provide confirm password. Redirecting again... </p>");
		}
		else if (!confirm.equals(password)) {
			out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>");
			out.println("<p>Passwords do not match. Please re-enter. Redirecting again... </p>");
		}
		else if (zip != "" && ph != "") {
			try {
				int zipcode = Integer.parseInt(zip);
				if (zipcode < 100000 || zipcode > 999999) {
					out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
					out.println("<p>Invalid input. Zipcode must be a 6-digit number only. Please re-enter. Redirecting again... </p>");
				} 
			} catch (Exception exc) {
				out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
				out.println("<p>Invalid input. Zipcode must be a numeric value only. Please re-enter. Redirecting again... </p>");
			}
			
			try {
				long phone = Long.parseLong(ph);
				if (phone < 1000000000 || phone > 9999999999L) {
					out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
					out.println("<p>Invalid input. Phone number must be a 10-digit number only. Please re-enter. Redirecting again... </p>");
				}
			} catch (Exception exc) {
				out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>");
				out.println("<p>Invalid input. Phone number must be a numeric value only. Please re-enter. Redirecting again... </p>");
			}
			
			chain.doFilter(request, response);
		}
		else if (zip != "") {
			try {
				int zipcode = Integer.parseInt(zip);
				if (zipcode < 100000 || zipcode > 999999) {
					out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
					out.println("<p>Invalid input. Zipcode must be a 6-digit number only. Please re-enter. Redirecting again... </p>");
				} 
				else {
					chain.doFilter(request, response);
				}
			} catch (Exception exc) {
				out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
				out.println("<p>Invalid input. Zipcode must be a numeric value only. Please re-enter. Redirecting again... </p>");
			}
		}
		else if (ph != "") {
			try {
				long phone = Long.parseLong(ph);
				if (phone < 1000000000 || phone > 9999999999L) {
					out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>");
					out.println("<p>Invalid input. Phone number must be a 10-digit number only. Please re-enter. Redirecting again... </p>");
				} else {
					chain.doFilter(request, response);
				}
			} catch (Exception exc) {
				out.println("<meta http-equiv='refresh' content='3;URL=signup.jsp'>"); 
				out.println("<p>Invalid input. Phone number must be a numeric value only. Please re-enter. Redirecting again... </p>");
			}
		}
		else {
			chain.doFilter(request, response);
		}		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
