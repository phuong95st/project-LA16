package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dao.ScheStuDao;
import dao.impl.HocKyDaoImpl;
import dao.impl.PositionDaoImpl;
import dao.impl.ScheStuDaoImpl;
import dao.impl.WeekDaoImpl;
import entity.HocKy;
import entity.Position;
import entity.ScheStu;
import entity.User;
import entity.Week;
import utils.Common;
import utils.Constant;
import utils.MessageProperties;

/**
 * Servlet implementation class StudentManagerController
 */
@WebServlet("/jsp/student_manager.htm")
public class StudentManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String action = request.getParameter(Constant.ACTION);
		if ("add".equals(action)) {
			// lấy danh sách học kỳ
			List<HocKy> listHocKy = new HocKyDaoImpl().getListHocKy();
			request.setAttribute("listHocKy", listHocKy);
			String hocKy = request.getParameter("hocKy");
			if (hocKy == null) {
				hocKy = listHocKy.get(0).getName();
			}
			// lấy danh sách tuần theo học kỳ
			List<Week> lisWeeks = new WeekDaoImpl().getListWeek(hocKy);
			request.setAttribute("lisWeeks", lisWeeks);
			if (request.getParameter("hocKy") != null) {
				request.getRequestDispatcher("load/add_update_student_load.jsp")
						.forward(request, response);
				return;
			}
			// lấy danh sách phòng ở B1
			List<Position> lisPositions = new PositionDaoImpl()
					.getListPosition("B1");
			request.setAttribute("lisPositions", lisPositions);

			request.setAttribute(Constant.ACTION, "studentManager");
			request.getRequestDispatcher("add_update_student.jsp").forward(
					request, response);
			return;
		} else if ("edit".equals(action)) {
			int scheStuId = Integer.parseInt(request.getParameter("scheStuId"));
			// get scheStu by id
			ScheStu scheStu = new ScheStuDaoImpl().getScheStuById(scheStuId);
			request.setAttribute("scheStu", scheStu);
			// lấy danh sách phòng ở B1
			List<Position> lisPositions = new PositionDaoImpl()
					.getListPosition("B1");
			request.setAttribute("lisPositions", lisPositions);

			request.setAttribute(Constant.ACTION, "studentManager");
			request.setAttribute("flag", action);
			request.getRequestDispatcher("add_update_student.jsp").forward(
					request, response);
			return;
		} else {
			// get list học kỳ
			List<HocKy> listHocKy = new HocKyDaoImpl().getListHocKy();
			request.setAttribute("listHocKy", listHocKy);
			String type = "1";
			String hocKy = listHocKy.get(0).getName();
			Map<String, String> listValue = new HashMap<>();
			listValue.put("type", type);
			listValue.put("h2.name", hocKy);
			// lấy danh sách lịch gặp sinh viên
			List<ScheStu> lisScheStus = new ScheStuDaoImpl().getListScheStu(
					listValue, user.getUserId());
			request.setAttribute("lisScheStus", lisScheStus);
			request.setAttribute(Constant.ACTION, "studentManager");
			request.getRequestDispatcher("student-manager.jsp").forward(
					request, response);
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String action = request.getParameter(Constant.ACTION);
		if ("search".equals(action)) {

			String name = request.getParameter("name");
			String type = request.getParameter("type");
			String hocKy = request.getParameter("hocKy");

			// get list học kỳ
			List<HocKy> listHocKy = new HocKyDaoImpl().getListHocKy();
			request.setAttribute("listHocKy", listHocKy);

			Map<String, String> listValue = new HashMap<>();
			if (!"".equals(name)) {
				listValue.put("name", name);
			}
			if (!"".equals(type)) {
				listValue.put("type", type);
			}
			if (!"".equals(hocKy)) {
				listValue.put("h2.name", hocKy);
			}

			// lấy danh sách lịch gặp sinh viên
			List<ScheStu> lisScheStus = new ScheStuDaoImpl().getListScheStu(
					listValue, user.getUserId());
			request.setAttribute("lisScheStus", lisScheStus);
			request.getRequestDispatcher("load/studentManagerLoad.jsp")
					.forward(request, response);
			return;
		} else if ("add".equals(action) || "edit".equals(action)) {
			String studentName = request.getParameter("studentName");
			String topic = request.getParameter("topic");
			int type = Integer.parseInt(request.getParameter("type"));
			// String hocKy = request.getParameter("hocKy");
			int dateOfWeek = Integer.parseInt(request.getParameter("dateOfweek"));
			String timeStart = request.getParameter("timeStart");
			String timeEnd = request.getParameter("timeEnd");
			String phong = request.getParameter("phong");

			ScheStu scheStu = new ScheStu();
			scheStu.setStudentName(studentName);
			scheStu.setTopic(topic);
			scheStu.setType(type);

			if ("add".equals(action)) {
				int wStartId = Integer.parseInt(request.getParameter("wStartId"));
				int wEndId = Integer.parseInt(request.getParameter("wEndId"));
				
				Week w1 = new Week();
				w1.setWeekId(wStartId);
				scheStu.setwStart(w1);
				Week w2 = new Week();
				w2.setWeekId(wEndId);
				scheStu.setwEnd(w2);
			}

			scheStu.setDateOfWeek(dateOfWeek);
			scheStu.setStart(new Time(Common.getHourByString(timeStart), Common
					.getMinByString(timeStart), 0));
			scheStu.setEnd(new Time(Common.getHourByString(timeEnd), Common
					.getMinByString(timeEnd), 0));
			scheStu.setPhong(new Position(phong, 0, 0));

			scheStu.setUser(user);

			if ("add".equals(action)) {
				// add
				if (!new ScheStuDaoImpl().addScheStu(scheStu)) {
					PrintWriter out = response.getWriter();
					out.print("System Error!");
					return;
				}
			} else {
				int id = Integer.parseInt(request.getParameter("id"));
				scheStu.setId(id);
				// update
				if (!new ScheStuDaoImpl().updateScheStu(scheStu)) {
					PrintWriter out = response.getWriter();
					out.print("System Error!");
					return;
				}
			}

			response.sendRedirect("student_manager.htm");
			return;
		} else if ("del".equals(action)) {
			int scheStuId = Integer.parseInt(request.getParameter("scheStuId"));

			// get scheStu
			ScheStuDao scheStuDao = new ScheStuDaoImpl();
			ScheStu scheStu = scheStuDao.getScheStuById(scheStuId);
			// delete
			OutputStream out = response.getOutputStream();
			JSONObject jsonObject = new JSONObject();
			if (!scheStuDao.delScheStu(scheStu)) {
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("ERR10"));
			} else {
				jsonObject.put(Constant.STATUS, true);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("MSG05"));
			}
			out.write(jsonObject.toJSONString().getBytes("UTF-8"));
			out.flush();
			out.close();
		}
	}

}
