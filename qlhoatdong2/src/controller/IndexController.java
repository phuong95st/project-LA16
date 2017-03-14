package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import dao.HolDao;
import dao.UserDao;
import dao.impl.EventDaoImpl;
import dao.impl.HolDaoImpl;
import dao.impl.LateDaoImpl;
import dao.impl.OnlDaoImpl;
import dao.impl.ScheStuDaoImpl;
import dao.impl.TeachDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Event;
import entity.Hol;
import entity.Late;
import entity.Onl;
import entity.ScheStu;
import entity.Teach;
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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Calendar calendar = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		// Lấy danh sách lịch trực trong ngày hiện tại
		List<Onl> lisOnls = new OnlDaoImpl().getListOnl(Common.getStartNow(),
				Common.getEndNow(), user.getUserId());

		// Lấy danh sách lịch gặp sinh viên trong ngày hiện tại
		List<ScheStu> lisScheStus = new ScheStuDaoImpl().getListScheStu(
				Common.getStartNow(), Common.getEndNow(), user.getUserId());

		// Lấy danh sách lịch giảng dạy trong ngày
		List<Teach> listTeachs = new TeachDaoImpl().getListTeach(
				Common.getStartNow(), Common.getEndNow(), user.getUserId());

		
		request.setAttribute("now", new Timestamp(now.getTimeInMillis()));
		request.setAttribute("lisOnls", lisOnls);
		request.setAttribute("listHol", listHol);
		request.setAttribute("lisScheStus", lisScheStus);
		request.setAttribute("listTeachs", listTeachs);
		request.setAttribute("lisEvents", lisEvents);

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
