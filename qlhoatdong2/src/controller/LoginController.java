package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import utils.Constant;
import validate.ValidateUser;

/**
 * Thực hiện điều khiển thao tác login hệ thống
 *
 * @author PhuongNH
 *
 */

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/jsp/login.do")
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
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserByEmail(email);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		if (user.isRole()) {
			request.setAttribute(Constant.ACTION, "home");
			response.sendRedirect(request.getContextPath()+"/admin/index.ad");
			return;
		}
		response.sendRedirect("index.htm");

	}
}
