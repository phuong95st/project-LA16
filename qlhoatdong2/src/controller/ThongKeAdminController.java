package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Constant;
import dao.HocKyDao;
import dao.TeachDao;
import dao.impl.HocKyDaoImpl;
import dao.impl.TeachDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.WeekDaoImpl;
import entity.HocKy;
import entity.User;
import entity.Week;

/**
 * Servlet implementation class ThongKeAdminController
 */
@WebServlet("/admin/thong_ke.ad")
public class ThongKeAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(Constant.ACTION);
		if ("change_data".equals(action)) {
			String hocKy = request.getParameter("hocKy");
			List<Week> listWeek = new WeekDaoImpl().getListWeek(hocKy);
			request.setAttribute("listWeek", listWeek);

			request.getRequestDispatcher("load/listWeek_thongke.jsp").forward(
					request, response);
			return;
		}

		// get list học kỳ
		HocKyDao hocKyDao = new HocKyDaoImpl();
		List<HocKy> listHocKy = hocKyDao.getListHocKy();
		request.setAttribute("listHocKy", listHocKy);

		// get list nhân viên
		List<User> listUser = new UserDaoImpl().getListUserByRole(false);
		request.setAttribute("listUser", listUser);

		request.setAttribute(Constant.ACTION, "thongke");
		request.getRequestDispatcher("thong_ke_day.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String hocKy = request.getParameter("hocKy");
		String weekId = request.getParameter("weekId");
		int userId = Integer.parseInt(request.getParameter("userId"));

		// tính toán thống kê
		TeachDao teachDao = new TeachDaoImpl();
		User user = new UserDaoImpl().getUserById(userId);
		
		// tổng số giờ day
		double allHour = teachDao.getAllHourTeach(hocKy, weekId, user.getUserId());
		// số giờ đã dạy
		double hour = teachDao.getHourTeach(hocKy, weekId, user.getUserId());
		// số giờ không dạy
		double noHour = allHour - hour;
		// Số giờ đi muộn
		double lateHour = teachDao.getHourLateTeach(hocKy, weekId, user.getUserId());

		request.setAttribute("user", user);
		request.setAttribute("allHour", allHour);
		request.setAttribute("hour", hour);
		request.setAttribute("noHour", noHour);
		request.setAttribute("lateHour", lateHour);
		request.setAttribute("tilemuon",
				(double) Math.round((lateHour * 100 / hour) * 100) / 100);
		request.setAttribute("tile",
				(double) Math.round((hour * 100 / allHour) * 100) / 100);
		request.getRequestDispatcher("load/resultChart.jsp").forward(request,
				response);
	}

}
