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
import utils.Constant;
import dao.impl.OnlDaoImpl;
import dao.impl.ScheStuDaoImpl;
import dao.impl.TeachDaoImpl;
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Lấy danh sách lịch trực trong ngày
		List<Onl> lisOnls = new OnlDaoImpl().getListOnl(Common.getStartNow(), Common.getEndNow(), user.getUserId());

		// Lấy danh sách lịch gặp sinh viên trong ngày hiện tại
		List<ScheStu> lisScheStus = new ScheStuDaoImpl().getListScheStu(Common.getStartNow(), Common.getEndNow(), user.getUserId());

		// Lấy danh sách lịch giảng dạy trong ngày
		List<Teach> listTeachs = new TeachDaoImpl().getListTeach(Common.getStartNow(), Common.getEndNow(), user.getUserId());

		request.setAttribute("lisOnls", lisOnls);
		request.setAttribute("lisScheStus", lisScheStus);
		request.setAttribute("listTeachs", listTeachs);
		request.setAttribute("now", Common.getNow());

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
