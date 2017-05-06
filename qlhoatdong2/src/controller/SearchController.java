package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TeachWeekDao;
import dao.impl.OnlDaoImpl;
import dao.impl.ScheStuDaoImpl;
import dao.impl.TeachDaoImpl;
import dao.impl.TeachWeekDaoImpl;
import entity.Onl;
import entity.ScheStu;
import entity.Teach;
import entity.TeachWeek;
import entity.User;
import utils.Common;
import utils.Constant;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/jsp/search.htm")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Constant.ACTION, "search");
		request.setAttribute("now", Common.getNow());
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// lấy thông tin tìm kiếm
		String timeStart = request.getParameter("timeStart");
		String timeEnd = request.getParameter("timeEnd");
		String date = request.getParameter("date");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Map<String, Object> listValue = new HashMap<>();
		if (!"".equals(timeStart)) {
			listValue.put(
					"time_start",
					new Time(Common.getHourByString(timeStart), Common
							.getMinByString(timeStart), 0));
			listValue.put("time_end", new Time(Common.getHourByString(timeEnd),
					Common.getMinByString(timeEnd), 0));
		}
		if (!"".equals(date)) {
			Calendar cal = new GregorianCalendar(Common.getYearByString(date),Common.getMonthByString(date) - 1,Common.getDayOfMonthByString(date));
			listValue.put("", new Date(cal.getTimeInMillis()));
		}
		listValue.put("user_id", user.getUserId());

		// tìm kiếm lịch dạy
		List<Teach> listTeachs = new TeachDaoImpl().getListTeach(listValue);
		// Lấy full thông tin về lịch giảng dạy trong ngày
		List<TeachWeek> listTeachInfo = new ArrayList<TeachWeek>();
		TeachWeekDao teWeekDao = new TeachWeekDaoImpl();
		Calendar cal = new GregorianCalendar(Common.getYearByString(date),
				Common.getMonthByString(date) - 1,
				Common.getDayOfMonthByString(date));
		Timestamp inputDate = new Timestamp(cal.getTimeInMillis());
		for (Teach teach : listTeachs) {
			// System.out.println(teWeekDao.getTeachDaoByTeachId(teach, now));
			listTeachInfo.add(teWeekDao.getTeachDaoByTeachId(teach, inputDate));
		}
		request.setAttribute("listTeachInfo", listTeachInfo);

		// tìm kiếm lịch hướng dẫn
		List<ScheStu> listScheStu = new ScheStuDaoImpl().getListScheStu(listValue);
		request.setAttribute("lisScheStus", listScheStu);
		// tìm kiếm lịch trực
		List<Onl> listOnl = new OnlDaoImpl().getListOnlBySearch(listValue);
		request.setAttribute("lisOnls", listOnl);
		request.getRequestDispatcher("load/searchLoad.jsp").forward(request,
				response);
	}

}
