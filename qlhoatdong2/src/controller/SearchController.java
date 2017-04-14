package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TeachWeekDao;
import dao.impl.ScheStuDaoImpl;
import dao.impl.TeachDaoImpl;
import dao.impl.TeachWeekDaoImpl;
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// lấy thông tin tìm kiếm
		String timeStart = request.getParameter("timeStart");
		String timeEnd = request.getParameter("timeEnd");
		String date = request.getParameter("date");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// tìm kiếm lịch dạy
		List<Teach> listTeachs = new TeachDaoImpl().getListTeach(timeStart,
				timeEnd, date, user.getUserId());
		// Lấy full thông tin về lịch giảng dạy trong ngày
		List<TeachWeek> listTeachInfo = new ArrayList<TeachWeek>();
		TeachWeekDao teWeekDao = new TeachWeekDaoImpl();
		Timestamp now = Common.getNow();
		for (Teach teach : listTeachs) {
			// System.out.println(teWeekDao.getTeachDaoByTeachId(teach, now));
			listTeachInfo.add(teWeekDao.getTeachDaoByTeachId(teach, now));
		}
		request.setAttribute("listTeachInfo", listTeachInfo);
		
		// tìm kiếm lịch hướng dẫn
		List<ScheStu> listScheStu = new ScheStuDaoImpl().getLisScheStu(timeStart, timeEnd, date, user.getUserId());
		request.setAttribute("lisScheStus", listScheStu);
		// tìm kiếm lịch trực

		request.getRequestDispatcher("load/searchLoad.jsp").forward(request, response);
	}

}
