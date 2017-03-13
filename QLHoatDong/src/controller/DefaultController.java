package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DefaultController
 */
@WebServlet("/DefaultController")
public class DefaultController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void setDefaultTime(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Timestamp timeDefault = new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh")).getTimeInMillis());
		int type = 3;
		request.setAttribute("timeDefault", timeDefault);
		request.setAttribute("type", type);
	}
}
