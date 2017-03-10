package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import utils.Constant;
import utils.MessageProperties;
import dao.impl.HolDaoImpl;

/**
 * Servlet implementation class HolController
 */
@WebServlet("/jsp/holiday.htm")
public class HolController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		request.setAttribute("action", action);
		
		request.getRequestDispatcher("register_hol.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		JSONObject jsonObject = new JSONObject();
		ServletOutputStream out = response.getOutputStream();
		if ("cencle".equals(action)) {
			long id = Long.parseLong(request.getParameter("id"));
			if (!new HolDaoImpl().delete(id)) {
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("ERR06"));

			} else {
				jsonObject.put(Constant.STATUS, true);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("MSG02"));
			}
			out.write(jsonObject.toJSONString().getBytes());
			out.flush();
			return;
		}
	}

}
