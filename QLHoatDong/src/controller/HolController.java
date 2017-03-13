package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import utils.Common;
import utils.Constant;
import utils.MessageProperties;
import validate.ValidateHol;
import dao.impl.HolDaoImpl;
import entity.Hol;
import entity.User;

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

		// set default
		DefaultController.setDefaultTime(request, response);

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
		HttpSession session = request.getSession();
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
		} else if ("add".equals(action)) {
			// get params
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			String reason = request.getParameter("reason");
			String type = request.getParameter("type");

			Hol hol = new Hol();
			Map<String, Integer> map = Common.parseTime(start);
			hol.setStart(new Timestamp(new GregorianCalendar(map
					.get(Constant.YEAR), map.get(Constant.MONTH) - 1, map
					.get(Constant.DAY)).getTimeInMillis()));
			map = Common.parseTime(end);
			hol.setEnd(new Timestamp(new GregorianCalendar(map
					.get(Constant.YEAR), map.get(Constant.MONTH) - 1, map
					.get(Constant.DAY)).getTimeInMillis()));
			hol.setReason(reason);
			hol.setType(Integer.parseInt(type));
			hol.setId(System.currentTimeMillis());

			// lấy listAddHol để kiểm tra ngày nghỉ đã đăng ký hay chưa.
			Map<Long, Hol> listAddHol = (Map<Long, Hol>) session.getAttribute("listAddHol");
			if (listAddHol == null) {
				listAddHol = new HashMap<>();
			}
			// lấy danh sách những ngày chưa nghỉ trong db để kiểm tra ngày nghỉ đã đăng ký hay chưa.
			User user = (User) session.getAttribute("user");
			List<Hol> hols = new HolDaoImpl().getListHolByStatus(false, user.getUserId());
			List<String> listError = new ValidateHol().validateHol(hol, listAddHol, hols);
			if (listError.size() != 0) {
				jsonObject.put(Constant.STATUS, false);
				for (String string : listError) {
					jsonObject.put(Constant.DATA, string + "\n");
				}
			} else {
				Common.autoSetTimeHol(hol);
				
				listAddHol.put(hol.getId(), hol);
				session.setAttribute("listAddHol", listAddHol);
				jsonObject.put(Constant.STATUS, true);
			}
			out.write(jsonObject.toJSONString().getBytes());
			out.flush();
			return;
		} else if ("del".equals(action)) {
			// get params
			Long id = Long.parseLong(request.getParameter("id"));
			Map<Long, Hol> listAddHol = (Map<Long, Hol>) session
					.getAttribute("listAddHol");
			if (listAddHol != null && listAddHol.containsKey(id)) {
				listAddHol.remove(id);
			}
			return;
		}else if("register".equals(action)){
			// insert ngày nghỉ vào db
			Map<Long, Hol> listAddHol = (Map<Long, Hol>) session.getAttribute("listAddHol");
			// set userId và set phep
			User user = (User) session.getAttribute("user");
			for (Entry<Long, Hol> item : listAddHol.entrySet()) {
				item.getValue().setUser(user);
				item.getValue().setPhep(true);
			}
			// add to db
			if(!new HolDaoImpl().insertByTrans(listAddHol)){
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, MessageProperties.getData("MSG03"));
			}else{
				session.removeAttribute("listAddHol");
				jsonObject.put(Constant.STATUS, true);
				jsonObject.put(Constant.DATA, MessageProperties.getData("MSG04"));
			}
			out.write(jsonObject.toJSONString().getBytes());
			out.flush();
			return;
		}
	}

}
