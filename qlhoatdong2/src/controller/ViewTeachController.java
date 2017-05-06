package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Constant;
import dao.impl.HocKyDaoImpl;
import dao.impl.TeachDaoImpl;
import entity.HocKy;
import entity.Teach;
import entity.User;

/**
 * Servlet implementation class ViewTeachController
 */
@WebServlet("/jsp/viewTeach.htm")
public class ViewTeachController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// get list học kỳ
		List<HocKy> listHocKy = new HocKyDaoImpl().getListHocKy();
		request.setAttribute("listHocKy", listHocKy);
		// lấy lịch dạy học kỳ đầu tiên trong listHocKy
		List<Teach> listTeach = new TeachDaoImpl().getListTeach(null, listHocKy.get(0).getName(), user.getUserId());
		request.setAttribute(Constant.ACTION, "viewTeach");
		request.setAttribute("listTeach", listTeach);
		request.getRequestDispatcher("view_teach.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hocKy = request.getParameter("hocKy");	
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Teach> listTeach = new TeachDaoImpl().getListTeach(null, hocKy, user.getUserId());
		request.setAttribute("listTeach", listTeach);
		request.getRequestDispatcher("load/viewTeachLoad.jsp").forward(request, response);
	}

}
