package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import utils.MessageProperties;
import dao.impl.LateDaoImpl;

/**
 * Servlet implementation class LateController
 */
@WebServlet("/jsp/late.htm")
public class LateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// get params
		String reason = request.getParameter("reason");

		long id = Long.parseLong(request.getParameter("id"));
		
		System.out.println("reason: " + reason);
		System.out.println("id: " + id);
		
		JSONObject jsonObject = new JSONObject();
		ServletOutputStream out = response.getOutputStream();
		// update
		if(!new LateDaoImpl().updateLate(reason, id)){
			jsonObject.put("data", MessageProperties.getData("ERR05"));
			jsonObject.put("status", false);
			out.write(jsonObject.toJSONString().getBytes("UTF-8")); 
			out.flush();
			return;
		}
		HttpSession session = request.getSession();
		session.removeAttribute("rangeMin");
		session.removeAttribute("lateId");
		
		jsonObject.put("status", true);
		jsonObject.put("data", MessageProperties.getData("MSG01"));
		out.write(jsonObject.toJSONString().getBytes("UTF-8")); 
		out.flush();
		return;
	}

}
