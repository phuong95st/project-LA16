package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.TeachLogic;

import org.json.simple.JSONObject;

import dao.WeekDao;
import dao.impl.HocKyDaoImpl;
import dao.impl.PositionDaoImpl;
import dao.impl.TeachDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.WeekDaoImpl;
import entity.HocKy;
import entity.Position;
import entity.Teach;
import entity.User;
import entity.Week;
import utils.Common;
import utils.Constant;
import utils.MessageProperties;
import validate.ValidateTeach;

/**
 * Servlet implementation class TeachAdminController
 */
@WebServlet("/admin/teach.ad")
public class TeachAdminController extends HttpServlet {
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
			try {
				userId = Integer.parseInt(request.getParameter("user"));
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

			// get list tuần học
			List<Week> lisWeeks = new WeekDaoImpl().getListWeek(hocKy);

			// get list phòng
			List<Position> lisPositions = new PositionDaoImpl()
					.getListPosition(null);

			// forward to add teach
			request.setAttribute("userId", userId);
			request.setAttribute("hocKy", hocKy);
			request.setAttribute(Constant.ACTION, "teach");
			request.setAttribute("user", user);
			request.setAttribute("lisWeeks", lisWeeks);
			request.setAttribute("lisPositions", lisPositions);

			request.getRequestDispatcher("add_update_teach.jsp").forward(
					request, response);
			return;
		} else if ("cencel".equals(action)) {
			HttpSession session = request.getSession();
			if (session.getAttribute("listTeach") != null) {
				session.removeAttribute("listTeach");
			}
			// forward
			response.sendRedirect("teach.ad");
			return;
		} else if ("edit".equals(action)) {
			// get teachId
			String tmpTeachId = request.getParameter("teachId");
			long teachId = 0;
			int userId = 0;
			try {
				teachId = Long.parseLong(tmpTeachId);
				userId = Integer.parseInt(request.getParameter("userId"));
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
			// get teach by id
			Teach teach = new TeachDaoImpl().getTeachById(teachId);

			request.setAttribute(Constant.ACTION, "teach");
			request.setAttribute("teach", teach);
			request.setAttribute("event", action);
			request.setAttribute("hocKy", hocKy);
			request.setAttribute("user", user);
			request.getRequestDispatcher("add_update_teach.jsp").forward(
					request, response);
			return;
		} else if ("delete".equals(action)) {
			// get params
			String tmpTeachId = request.getParameter("teachId");
			long teachId = 0;
			try {
				teachId = Long.parseLong(tmpTeachId);
			} catch (NumberFormatException e) {
				request.setAttribute("error",
						MessageProperties.getData("ERR20"));
				request.getRequestDispatcher("page_error.jsp").forward(request,
						response);
				return;
			}
			// get Teach
			Teach teach = new TeachDaoImpl().getTeachById(teachId);
			// delete
			String message = "";
			if(!new TeachLogic().removeTeach(teach)){
				message =  MessageProperties.getData("ERR25");
				request.setAttribute("messageErorr", message);
			}else{
				message = MessageProperties.getData("MSG14");
				request.setAttribute("message", message);
			}
			
			request.setAttribute(Constant.ACTION, "teach");
			request.getRequestDispatcher("deleteTeachSuccess.jsp").forward(request,response);
			return;
		}

		// get list nhân viên
		List<User> listUser = new UserDaoImpl().getListUserByRole(false);
		// get list học kỳ
		List<HocKy> listHocKy = new HocKyDaoImpl().getListHocKy();

		request.setAttribute("listUser", listUser);
		request.setAttribute("listHocKy", listHocKy);

		request.setAttribute(Constant.ACTION, "teach");
		request.getRequestDispatcher("ql_giang_day.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(Constant.ACTION);
		if ("search".equals(action)) {
			String tmpUserId = request.getParameter("userId");
			String hocKy = request.getParameter("hocKy");
			List<Teach> listTeach = null;
			// gọi hàm search
			if ("none".equals(tmpUserId)) {
				listTeach = new TeachDaoImpl().getListTeach(null, hocKy, 0);
				request.setAttribute("hocKy", hocKy);
			} else if ("none".equals(hocKy)) {
				int userId = Integer.parseInt(tmpUserId);
				User user = new UserDaoImpl().getUserById(userId);
				listTeach = new TeachDaoImpl().getListTeach(null, null, userId);
				request.setAttribute("user", user);
			} else {
				int userId = Integer.parseInt(tmpUserId);
				User user = new UserDaoImpl().getUserById(userId);
				listTeach = new TeachDaoImpl()
						.getListTeach(null, hocKy, userId);
				request.setAttribute("user", user);
				request.setAttribute("hocKy", hocKy);
			}
			request.setAttribute("listTeach", listTeach);
			request.getRequestDispatcher("load/load_search_teach.jsp").forward(
					request, response);
			return;
		} else if ("add".equals(action) || "edit".equals(action)) {
			// get params
			int userId = Integer.parseInt(request.getParameter("userId"));
			long teachId = 0;
			if ("edit".equals(action)) {
				teachId = Long.parseLong(request.getParameter("teachId"));
			}
			String timeStart = request.getParameter("timeStart");
			String timeEnd = request.getParameter("timeEnd");
			int dateOfweek = Integer.parseInt(request
					.getParameter("dateOfweek"));
			int wStartId = 0;
			int wEndId = 0;
			if ("add".equals(action)) {
				wStartId = Integer.parseInt(request.getParameter("wStartId"));
				wEndId = Integer.parseInt(request.getParameter("wEndId"));
			}
			String phong = request.getParameter("phong");
			String codeClass = request.getParameter("codeClass");
			String codeSubject = request.getParameter("codeSubject");
			String name = request.getParameter("name");

			// set to Teach
			Teach teach = new Teach();
			teach.setTimeStart(new Time(Common.getHourByString(timeStart),
					Common.getMinByString(timeStart), 0));
			teach.setTimeEnd(new Time(Common.getHourByString(timeEnd), Common
					.getMinByString(timeEnd), 0));
			teach.setDateOfWeek(dateOfweek);

			if ("add".equals(action)) {
				WeekDao weekDao = new WeekDaoImpl();
				Week wStart = weekDao.getWeekById(wStartId);
				teach.setWeekStart(wStart);
				Week wEnd = weekDao.getWeekById(wEndId);
				teach.setWeekEnd(wEnd);
			} else {
				teach.setTeachId(teachId);
			}
			teach.setPhong(new Position(phong, 0, 0));
			teach.setCodeClass(codeClass);
			teach.setCodeSubject(codeSubject);
			teach.setName(name);

			User user = new User();
			user.setUserId(userId);
			teach.setUser(user);

			// validate
			List<String> listError = new ValidateTeach().validateTeach(teach);
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
			if ("add".equals(action)) {
				// add to session
				HttpSession session = request.getSession();
				List<Teach> listTeach = null;
				if (session.getAttribute("listTeach") == null) {
					listTeach = new ArrayList<>();
					listTeach.add(teach);
				} else {
					listTeach = (List<Teach>) session.getAttribute("listTeach");
					if (!Common.checkData(teach, listTeach)) {
						listTeach.add(teach);
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
				session.setAttribute("listTeach", listTeach);
			} else {
				// edit
				if (!new TeachDaoImpl().editTeach(teach)) {
					jsonObject.put(Constant.STATUS, false);
					jsonObject.put("flag", true);
					jsonObject.put(Constant.DATA,
							MessageProperties.getData("MSG12"));
					out.write(jsonObject.toJSONString().getBytes("UTF-8"));
					out.flush();
					return;
				}
			}

			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG13"));
			out.write(jsonObject.toJSONString().getBytes("UTF-8"));
			out.flush();
			return;
		} else if ("removeItem".equals(action)) {
			// get pos
			int pos = Integer.parseInt(request.getParameter("pos"));
			HttpSession session = request.getSession();
			List<Teach> listTeach = (List<Teach>) session
					.getAttribute("listTeach");
			if (listTeach != null) {
				listTeach.remove(--pos);
				if (listTeach.size() == 0) {
					session.removeAttribute("listTeach");
				}
			}
			return;
		} else if ("insert".equals(action)) {
			HttpSession session = request.getSession();
			List<Teach> listTeach = (List<Teach>) session
					.getAttribute("listTeach");

			OutputStream out = response.getOutputStream();
			JSONObject jsonObject = new JSONObject();
			if (listTeach == null) {
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("ERR21"));
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			// add to db
			if (!new TeachLogic().insertTeach(listTeach)) {
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("MSG11"));
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			// remove session
			session.removeAttribute("listTeach");

			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG10"));
			out.write(jsonObject.toJSONString().getBytes("UTF-8"));
			out.flush();
			return;
		}
	}

}
