package controller;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Common;
import utils.Constant;
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

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/jsp/index.htm")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user.isRole()) {
			request.setAttribute(Constant.ACTION, "home");
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			return;
		}

		// Lấy danh sách lịch trực trong ngày
		List<Onl> lisOnls = new OnlDaoImpl().getListOnl(Common.getStartNow(),0, user.getUserId());

		// Lấy danh sách lịch gặp sinh viên trong ngày hiện tại
		List<ScheStu> lisScheStus = new ScheStuDaoImpl().getListScheStu(
				Common.getStartNow(), user.getUserId());

		// Lấy danh sách lịch giảng dạy trong ngày
		List<Teach> listTeachs = new TeachDaoImpl().getListTeach(Common.getStartNow(), null, user.getUserId());
		// Lấy full thông tin về lịch giảng dạy trong ngày
		List<TeachWeek> listTeachInfo = new ArrayList<TeachWeek>();
		TeachWeekDao teWeekDao = new TeachWeekDaoImpl();
		Timestamp now = Common.getNow();
		for (Teach teach : listTeachs) {
			// System.out.println(teWeekDao.getTeachDaoByTeachId(teach, now));
			listTeachInfo.add(teWeekDao.getTeachDaoByTeachId(teach, now));
		}

		request.setAttribute("lisOnls", lisOnls);
		request.setAttribute("lisScheStus", lisScheStus);
		request.setAttribute("listTeachInfo", listTeachInfo);

		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		request.setAttribute("now",new Time(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND)));

		request.setAttribute(Constant.ACTION, "home");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
