package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.OnlLogic;

import org.json.simple.JSONObject;

import utils.Common;
import utils.Constant;
import utils.MessageProperties;
import validate.ValidateOnl;
import dao.OnlDao;
import dao.WeekDao;
import dao.impl.HocKyDaoImpl;
import dao.impl.OnlDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.WeekDaoImpl;
import entity.HocKy;
import entity.Onl;
import entity.User;
import entity.Week;

/**
 * Servlet implementation class OnlAdminController
 */
@WebServlet("/admin/onl.ad")
public class OnlAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(Constant.ACTION);
		if ("add".equals(action)) {
			// get params
			int userId = 0;
			int weekId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("user"));
				weekId = Integer.parseInt(request.getParameter("weekId"));
			} catch (NumberFormatException e) {
				request.setAttribute("error",
						MessageProperties.getData("ERR20"));
				request.getRequestDispatcher("page_error.jsp").forward(request,
						response);
				return;
			}
			String hocKy = request.getParameter("hocKy");

			// get user
			User user = new UserDaoImpl().getUserById(userId);
			// get Week
			Week week = new WeekDaoImpl().getWeekById(weekId);
			// forward
			request.setAttribute("week", week);
			request.setAttribute("hocKy", hocKy);
			request.setAttribute(Constant.ACTION, "onl");
			request.setAttribute("user", user);

			request.getRequestDispatcher("add_update_onl.jsp").forward(request,
					response);
			return;
		} else if ("cencel".equals(action)) {
			HttpSession session = request.getSession();
			if (session.getAttribute("listOnl") != null) {
				session.removeAttribute("listOnl");
			}
			// forward
			response.sendRedirect("onl.ad");
			return;
		} else if ("edit".equals(action)) {
			// get params
			long onlId = 0;
			try {
				onlId = Long.parseLong(request.getParameter("onlId"));
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				request.setAttribute("error",
						MessageProperties.getData("ERR20"));
				request.getRequestDispatcher("page_error.jsp").forward(request,
						response);
				return;
			}

			// get Onl
			Onl onl = new OnlDaoImpl().getOnlById(onlId);
			// get user
			User user = new UserDaoImpl()
					.getUserById(onl.getUser().getUserId());
			onl.setUser(user);

			request.setAttribute(Constant.ACTION, "onl");
			request.setAttribute("onl", onl);
			request.setAttribute("event", action);
			request.getRequestDispatcher("add_update_onl.jsp").forward(request,
					response);
			return;
		}
		// get list nhân viên
		List<User> listUser = new UserDaoImpl().getListUserByRole(false);
		// get list học kỳ
		List<HocKy> listHocKy = new HocKyDaoImpl().getListHocKy();

		request.setAttribute("listUser", listUser);
		request.setAttribute("listHocKy", listHocKy);

		request.setAttribute(Constant.ACTION, "onl");
		request.getRequestDispatcher("ql_truc.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(Constant.ACTION);
		if ("loadWeek".equals(action)) {
			// get hocKy
			String hocKy = request.getParameter("hocKy");
			if ("none".equals(hocKy)) {
				request.getRequestDispatcher("load/listWeek.jsp").forward(
						request, response);
				return;
			}
			// get listWeek theo học kỳ
			WeekDao weekDao = new WeekDaoImpl();
			List<Week> listWeek = weekDao.getListWeek(hocKy);
			// get current week
			Week currentWeek = weekDao.getCurrentWeek(Common.getNow());

			request.setAttribute("listWeek", listWeek);
			request.setAttribute("currentWeek", currentWeek);
			request.getRequestDispatcher("load/listWeek.jsp").forward(request,
					response);
			return;
		} else if ("search".equals(action)) {
			// get params
			String tmpUserId = request.getParameter("userId");
			String tmpWeekId = request.getParameter("weekId");
			String hocKy = request.getParameter("hocKy");
			// add to map
			Map<String, Object> searchValue = new HashMap<String, Object>();
			if (!"none".equals(tmpUserId)) {
				int userId = Integer.parseInt(tmpUserId);
				searchValue.put("user_id", userId);
			}
			if (!"none".equals(tmpWeekId)) {
				int weekId = Integer.parseInt(tmpWeekId);
				searchValue.put(Constant.TBL_WEEK + ".week_id", weekId);
			}
			if (!"none".equals(hocKy)) {
				searchValue.put(Constant.TBL_HOC_KY + ".name", hocKy);
			}

			// gọi hàm search
			List<Onl> listOnl = new OnlDaoImpl().getListOnl(searchValue);

			// add request
			request.setAttribute("listOnl", listOnl);
			request.getRequestDispatcher("load/load_search_onl.jsp").forward(
					request, response);
			return;
		} else if ("add".equals(action)) {
			// get params
			int userId = Integer.parseInt(request.getParameter("userId"));
			int weekId = Integer.parseInt(request.getParameter("weekId"));
			String timeStart = request.getParameter("timeStart");
			String timeEnd = request.getParameter("timeEnd");
			int dateOfweek = Integer.parseInt(request
					.getParameter("dateOfweek"));
			int caTruc = Integer.parseInt(request.getParameter("caTruc"));

			// set to Onl
			Onl onl = new Onl();
			onl.setTimeStart(new Time(Common.getHourByString(timeStart), Common
					.getMinByString(timeStart), 0));
			onl.setTimeEnd(new Time(Common.getHourByString(timeEnd), Common
					.getMinByString(timeEnd), 0));
			onl.setCaTruc(caTruc);

			User user = new UserDaoImpl().getUserById(userId);
			onl.setUser(user);

			WeekDao weekDao = new WeekDaoImpl();
			Week week = weekDao.getWeekById(weekId);
			onl.setWeek(week);

			onl.setDateOfWeek(dateOfweek);
			onl.setHol(true);
			onl.setLate(false);
			onl.setLateMin(0);
			onl.setReason("");

			// validate
			List<String> listError = new ValidateOnl().validateOnl(onl);
			OutputStream out = response.getOutputStream();
			JSONObject jsonObject = new JSONObject();
			if (listError.size() != 0) {
				StringBuilder data = new StringBuilder();
				data.append("<div class=\"alert alert-danger\">");
				data.append("<span class=\"small\">");
				int i = 0;
				for (String string : listError) {
					if (i != 0) {
						data.append("<br>");
						data.append(string);
					} else {
						data.append(string);
					}
					i++;
				}
				data.append("</span></div>");
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, data.toString());
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}

			// add to session
			HttpSession session = request.getSession();
			List<Onl> listOnl = null;
			if (session.getAttribute("listOnl") == null) {
				listOnl = new ArrayList<>();
				listOnl.add(onl);
			} else {
				listOnl = (List<Onl>) session.getAttribute("listOnl");
				if (!Common.checkData(onl, listOnl)) {
					listOnl.add(onl);
				} else {
					jsonObject.put(Constant.STATUS, false);
					jsonObject.put(Constant.DATA,
							"<div class=\"alert alert-danger\"><span class=\"small\">"
									+ MessageProperties.getData("ERR24")
									+ "</span></div>");
					out.write(jsonObject.toJSONString().getBytes("UTF-8"));
					out.flush();
					return;
				}
			}
			session.setAttribute("listOnl", listOnl);

			jsonObject.put(Constant.STATUS, true);
			out.write(jsonObject.toJSONString().getBytes("UTF-8"));
			out.flush();
			return;
		} else if ("insert".equals(action)) {
			HttpSession session = request.getSession();
			List<Onl> listOnl = (List<Onl>) session.getAttribute("listOnl");

			OutputStream out = response.getOutputStream();
			JSONObject jsonObject = new JSONObject();
			if (listOnl == null) {
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("ERR21"));
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			// add to db
			if (!new OnlLogic().insertOnl(listOnl)) {
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("MSG11"));
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			// remove session
			session.removeAttribute("listOnl");

			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG10"));
			out.write(jsonObject.toJSONString().getBytes("UTF-8"));
			out.flush();
			return;
		} else if ("edit".equals(action)) {
			// get params
			long onlId = Long.parseLong(request.getParameter("onlId"));
			String timeStart = request.getParameter("timeStart");
			String timeEnd = request.getParameter("timeEnd");
			int dateOfweek = Integer.parseInt(request
					.getParameter("dateOfweek"));
			int caTruc = Integer.parseInt(request.getParameter("caTruc"));
			// get Onl
			OnlDao onlDao = new OnlDaoImpl();
			Onl onl = onlDao.getOnlById(onlId);

			onl.setTimeStart(new Time(Common.getHourByString(timeStart), Common
					.getMinByString(timeStart), 0));
			onl.setTimeEnd(new Time(Common.getHourByString(timeEnd), Common
					.getMinByString(timeEnd), 0));
			onl.setCaTruc(caTruc);
			onl.setDateOfWeek(dateOfweek);

			// validate
			List<String> listError = new ValidateOnl().validateOnl(onl);
			OutputStream out = response.getOutputStream();
			JSONObject jsonObject = new JSONObject();
			if (listError.size() != 0) {
				StringBuilder data = new StringBuilder();
				data.append("<div class=\"alert alert-danger\">");
				data.append("<span class=\"small\">");
				int i = 0;
				for (String string : listError) {
					if (i != 0) {
						data.append("<br>");
						data.append(string);
					} else {
						data.append(string);
					}
					i++;
				}
				data.append("</span></div>");
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, data.toString());
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			// update
			if(!onlDao.update(onl, false)){
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("MSG12"));
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			
			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG13"));
			out.write(jsonObject.toJSONString().getBytes("UTF-8"));
			out.flush();
			return;
		}else{
			// delete
			// get params
			long onlId = Long.parseLong(request.getParameter("onlId"));
			OutputStream out = response.getOutputStream();
			JSONObject jsonObject = new JSONObject();
			if(!new OnlDaoImpl().delete(onlId)){
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,MessageProperties.getData("ERR25"));
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG14"));
			out.write(jsonObject.toJSONString().getBytes("UTF-8"));
			out.flush();
			return;
		}
	}

}
