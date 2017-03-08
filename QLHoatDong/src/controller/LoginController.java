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

import dao.HolDao;
import dao.impl.EventDaoImpl;
import dao.impl.HolDaoImpl;
import dao.impl.OnlDaoImpl;
import dao.impl.ScheStuDaoImpl;
import dao.impl.TeachDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Event;
import entity.Hol;
import entity.Onl;
import entity.ScheStu;
import entity.Teach;
import entity.Time;
import entity.User;
import utils.Common;
import utils.Constant;
import validate.ValidateUser;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/jsp/login.htm")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		List<String> listError = new ValidateUser().validateLogin(email, pass);
		if (listError.size() != 0) {
			request.setAttribute("listError", listError);
			request.setAttribute("email", email);
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		}

		// get user by email
		User user = new UserDaoImpl().getUserByEmail(email);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		// get list Hol
		HolDao holDao = new HolDaoImpl();
		List<Hol> listHol = holDao.getListHol(user.getUserId());

		// Lấy danh sách lịch trực trong ngày hiện tại
		List<Onl> lisOnls = new OnlDaoImpl().getListOnl(Common.getStartNow(),
				Common.getEndNow(), user.getUserId());

		// Lấy danh sách lịch gặp sinh viên trong ngày hiện tại
		List<ScheStu> lisScheStus = new ScheStuDaoImpl().getListScheStu(
				Common.getStartNow(), Common.getEndNow(), user.getUserId());

		// Lấy danh sách lịch giảng dạy trong ngày
		List<Teach> listTeachs = new TeachDaoImpl().getListTeach(
				Common.getStartNow(), Common.getEndNow(), user.getUserId());

		// Lấy danh sách các sự kiện trong tuần
		List<Event> lisEvents = new EventDaoImpl().getListEvent(
				Common.getStartDay(), Common.getLastDayOfWeek());

		Calendar now = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		// xét các trường hợp login vào ngày hôm sau
		Calendar lastLogin = new GregorianCalendar();
		lastLogin.setTimeInMillis(user.getLastLogin().getTime());

		Calendar i = new GregorianCalendar(lastLogin.get(Calendar.YEAR),
				lastLogin.get(Calendar.MONTH),
				lastLogin.get(Calendar.DAY_OF_MONTH));
		Calendar stone = new GregorianCalendar(now.get(Calendar.YEAR),
				now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
		if (stone.compareTo(i) > 0) {
			// lặp qua các ngày nghỉ
			for (Hol hol : listHol) {
				// xét những ngày nghỉ đăng kí nhưng chưa nghỉ
				if (!hol.isStatus()) {
					Calendar endHol = new GregorianCalendar();
					endHol.setTimeInMillis(hol.getEnd().getTime());
					// nếu ngày nghỉ đã diễn ra (trước hôm nay thì insert vào
					// db)
					if (new GregorianCalendar(endHol.get(Calendar.YEAR),
							endHol.get(Calendar.MONTH),
							endHol.get(Calendar.DAY_OF_MONTH)).compareTo(stone) < 0) {
						// update status
						// if(!holDao.update(null, true)){
						// response.sendRedirect("page_error.htm");
						// return;
						// }
					}
				}
			}

			i.set(Calendar.DAY_OF_MONTH, i.get(Calendar.DAY_OF_MONTH) + 1);
			System.out.println("i: " + new Timestamp(i.getTimeInMillis()));
			System.out.println("stone: "
					+ new Timestamp(stone.getTimeInMillis()));
			while (i.compareTo(stone) < 0) {
				boolean flag = false;
				for (Hol hol : listHol) {
					// xét những ngày nghỉ, đăng kí nhưng chưa nghỉ và trước hôm
					// nay
					Calendar endHol = new GregorianCalendar();
					endHol.setTimeInMillis(hol.getEnd().getTime());

					Calendar j = new GregorianCalendar(
							endHol.get(Calendar.YEAR),
							endHol.get(Calendar.MONTH),
							endHol.get(Calendar.DAY_OF_MONTH));
					if (!hol.isStatus() && j.compareTo(stone) < 0) {
						// nếu ngày nghỉ nằm trong ngày không login
						if (i.compareTo(j) == 0) {
							flag = true;
						}
					}
				}
				if (!flag) {
					// insert nghỉ không phép
					System.out.println("nghỉ không phép");

				}
				i.set(Calendar.DAY_OF_MONTH, i.get(Calendar.DAY_OF_MONTH) + 1);
			}

			stone.set(Calendar.HOUR_OF_DAY, 8);
			stone.set(Calendar.MINUTE, 1);
			stone.set(Calendar.SECOND, 0);

			System.out.println("now: " + new Timestamp(now.getTimeInMillis()));
			System.out.println("stone: "
					+ new Timestamp(stone.getTimeInMillis()));

			if (now.after(stone)) {
				int rangeMin = (int) Math
						.ceil(((double) (now.getTimeInMillis() - stone
								.getTimeInMillis()) / 1000) / 60);
				System.out.println("Muộn: " + rangeMin);
				// insert ngày đi muộn
			}
			
			// update thời gian login cuối cùng
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
