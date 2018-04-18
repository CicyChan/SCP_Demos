package tenantcontext.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.cloud.account.*;

/**
 * Servlet implementation class TenantContextServlet
 */
@WebServlet("/TenantContext")
public class TenantContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TenantContextServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		try {
			InitialContext ctx = new InitialContext();
			Context envCtx = (Context)ctx.lookup("java:comp/env");
			TenantContext tenantContext = (TenantContext) envCtx.lookup("TenantContext");

			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();

			writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>SAP Cloud Platform - Tenant Context Demo Application</title>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<h2> Welcome to the SAP Cloud Platform Tenant Context demo application</h2>");
			writer.println("<br></br>");

			String currentTenantId = tenantContext.getTenant().getId();
			String accountName 	  = tenantContext.getTenant().getAccount().getName();
			String accountId 	  = tenantContext.getTenant().getAccount().getId();
			writer.println("<p><font size=\"5\"> The application was accessed on behalf of a tenant with an ID: <b>" + currentTenantId + "</b></font></p>");
			writer.println("<p><font size=\"5\"> The application was accessed on behalf of a tenant with an accountName: <b>" + accountName + "</b></font></p>");
			writer.println("<p><font size=\"5\"> The application was accessed on behalf of a tenant with an accountID: <b>" + accountId + "</b></font></p>");

			writer.println("</body>");
			writer.println("</html>");

		} catch (Exception e) {
			throw new ServletException(e.getCause());
		}

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
