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

import dao.TeachWeekDao;
import dao.impl.OnlDaoImpl;
import dao.impl.ScheStuDaoImpl;
import dao.impl.TeachDaoImpl;
import dao.impl.TeachWeekDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Onl;
import entity.ScheStu;
import entity.Teach;
import entity.TeachWeek;
import entity.User;
import utils.Common;
import utils.Constant;

/**
 * Servlet implementation class SearchAdminController
 */
@WebServlet("/admin/search.ad")
public class SearchAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// get list nhân viên
		List<User> listUser = new UserDaoImpl().getListUserByRole(false);

		request.setAttribute("listUser", listUser);
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
		// get params
		String timeStart = request.getParameter("timeStart");
		String timeEnd = request.getParameter("timeEnd");
		String date = request.getParameter("date");
		String tmpUserId = request.getParameter("userId");

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
			Calendar cal = new GregorianCalendar(Common.getYearByString(date),
					Common.getMonthByString(date) - 1,
					Common.getDayOfMonthByString(date));
			listValue.put("", new Date(cal.getTimeInMillis()));
		}
		if (!"none".equals(tmpUserId)) {
			int userId = Integer.parseInt(tmpUserId);
			listValue.put("user_id", userId);
		}

		// gọi hàm searchTeach
		List<Teach> listTeachs = new TeachDaoImpl().getListTeach(listValue);
		// Lấy full thông tin về lịch giảng dạy trong ngày
		List<TeachWeek> listTeachInfo = new ArrayList<>();
		TeachWeekDao teWeekDao = new TeachWeekDaoImpl();
		Calendar cal = new GregorianCalendar(Common.getYearByString(date),Common.getMonthByString(date) - 1,Common.getDayOfMonthByString(date));
		Timestamp inputDate = new Timestamp(cal.getTimeInMillis());
		for (Teach teach : listTeachs) {
			// System.out.println(teWeekDao.getTeachDaoByTeachId(teach, now));
			listTeachInfo.add(teWeekDao.getTeachDaoByTeachId(teach, inputDate));
		}
		request.setAttribute("listTeachInfo", listTeachInfo);
		
		// hàm search lịch gặp sinh viên
		List<ScheStu> lisScheStus = new ScheStuDaoImpl().getListScheStu(listValue);
		request.setAttribute("lisScheStus", lisScheStus);
		
		// hàm search lịch trực
		List<Onl> lisOnls = new OnlDaoImpl().getListOnlBySearch(listValue);
		request.setAttribute("lisOnls", lisOnls);
		
		request.getRequestDispatcher("load/searchLoad.jsp").forward(request, response);
	}

}
