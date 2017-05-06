package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Common;
import dao.WeekDao;
import dao.impl.OnlDaoImpl;
import dao.impl.WeekDaoImpl;
import entity.Onl;
import entity.User;
import entity.Week;

/**
 * Servlet implementation class ViewOnlController
 */
@WebServlet("/jsp/viewOnl.htm")
public class ViewOnlController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get hocKy
		String hocKy = request.getParameter("hocKy");
		// get list week theo học kỳ
		WeekDao weekDao = new WeekDaoImpl();
		List<Week> listWeek = weekDao.getListWeek(hocKy);
		request.setAttribute("listWeek", listWeek);
		// get tuần hiện tại
		Week currentWeek = weekDao.getCurrentWeek(Common.getNow());
		request.setAttribute("currentWeek", currentWeek);
		
		request.getRequestDispatcher("load/listWeek.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int weekId = Integer.parseInt(request.getParameter("weekId"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		// lấy lịch trực trong tuần
		List<Onl> listOnl = new OnlDaoImpl().getListOnl(null, weekId, user.getUserId());
		request.setAttribute("listOnl", listOnl);
		request.getRequestDispatcher("load/resultOnl.jsp").forward(request, response);
	}

}
