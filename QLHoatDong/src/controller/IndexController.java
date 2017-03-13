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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		UserDao userDao = new UserDaoImpl();
		// update thời gian login cuối
		user.setLastLogin(userDao.getLastLogin(user.getUserId()));

		// get list Hol
		HolDao holDao = new HolDaoImpl();
		Calendar calendar = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		List<Hol> listHol = holDao.getListHol(
				new Timestamp(calendar.getTimeInMillis()), user.getUserId());

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

		System.out.println("last: " + new Timestamp(i.getTimeInMillis()));
		System.out.println("now: " + new Timestamp(stone.getTimeInMillis()));
		System.out.println("-----------------------");
		if (stone.compareTo(i) > 0) {
			// lấy những ngày nghỉ sau thời gian login cuối
			List<Hol> hols = holDao.getListHol(user.getLastLogin(),
					user.getUserId());
			// lặp qua các ngày nghỉ
			for (Hol hol : hols) {
				// xét những ngày nghỉ đăng kí nhưng chưa nghỉ
				// System.out.println(hol.isStatus());
				if (!hol.isStatus()) {
					Calendar endHol = new GregorianCalendar();
					endHol.setTimeInMillis(hol.getEnd().getTime());
					// nếu ngày nghỉ đã diễn ra (trước hôm nay thì insert vào
					// db)
					if (endHol.compareTo(now) < 0) {
						Hol newHol = new Hol();
						newHol.setId(hol.getId());
						newHol.setStatus(true);
						newHol.setPhep(true);
						// update status và phép
						if (!holDao.update(newHol, true)) {
							response.sendRedirect("page_error.htm");
							return;
						}
					}
				}
			}

			i.set(Calendar.DAY_OF_MONTH, i.get(Calendar.DAY_OF_MONTH) + 1);
			while (i.compareTo(stone) < 0) {
				// kiểm tra có phải thứ 7 và chủ nhật không?
				if (!(i.get(Calendar.DAY_OF_WEEK) == 7 || i
						.get(Calendar.DAY_OF_WEEK) == 1)) {
					// lấy ngày nghỉ = ngày i
					Hol hol = holDao.getHolByTime(
							new Timestamp(i.getTimeInMillis()),
							user.getUserId());
					Hol hol2 = new Hol();
					hol2.setType(3);
					hol2.setStart(new Timestamp(i.getTimeInMillis()));
					hol2.setEnd(new Timestamp(i.getTimeInMillis()));
					hol2.setReason("Nghỉ không phép");
					hol2.setStatus(true);
					hol2.setPhep(false);
					User user2 = new User();
					user2.setUserId(user.getUserId());
					hol2.setUser(user2);
					
					System.out.println("day_of week: "
							+ i.get(Calendar.DAY_OF_WEEK));
					// nếu có thì kiểm tra
					if (hol != null) {
						// nếu là nghỉ sáng
						if (hol.getType() == 1) {
							hol2.setType(2);
						} else {
							hol2.setType(1);
						}
					}
					Common.autoSetTimeHol(hol2);
					// insert
					if (!holDao.insert(hol2)) {
						response.sendRedirect("page_error.htm");
						return;
					}

				}

				i.set(Calendar.DAY_OF_MONTH, i.get(Calendar.DAY_OF_MONTH) + 1);
			}

			stone.set(Calendar.HOUR_OF_DAY, 8);
			stone.set(Calendar.MINUTE, 1);
			stone.set(Calendar.SECOND, 0);

			// System.out.println("now: " + new
			// Timestamp(now.getTimeInMillis()));
			// System.out.println("stone: "
			// + new Timestamp(stone.getTimeInMillis()));

			if (now.after(stone)) {
				int rangeMin = (int) Math
						.ceil(((double) (now.getTimeInMillis() - stone
								.getTimeInMillis()) / 1000) / 60);
				System.out.println("Muộn: " + rangeMin);
				// insert
				long lateId = new LateDaoImpl().insertLate(new Late(0,
						new Timestamp(now.getTimeInMillis()), "", user,
						rangeMin));
				if (lateId == 0) {
					response.sendRedirect("page_error.htm");
					return;
				}
				session.setAttribute("rangeMin", rangeMin);
				session.setAttribute("lateId", lateId);
			}

			// update thời gian login cuối cùng
			if (!userDao.updateLastLogin(new Timestamp(now.getTimeInMillis()),
					user.getUserId())) {
				response.sendRedirect("page_error.htm");
				return;
			}
		}
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
