/**
 * Copyright(C) 2016 Luvina Software Company
 * LoginController.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.luvina.manageuser.utils.Common;
import net.luvina.manageuser.utils.Constant;
import net.luvina.manageuser.validates.ValidateUser;

/**
 * @author PhuongNH
 *
 */
public class LoginController extends javax.servlet.http.HttpServlet implements
javax.servlet.Servlet{
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 *
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/*
	 * (non-Java-doc)
	 *
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher req = request.getRequestDispatcher(Constant.ADM001);
		req.forward(request, response);

	}

	/*
	 * (non-Java-doc)
	 *
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();
		List<String> lsErrMessage = new ArrayList<String>();
		String template = "";

		// Start fix bug ID 12 – PhuongNH 2016/08/04
		String loginId = Common.escapeInjection(request.getParameter("loginId").toString());
		String password = Common.escapeInjection(request.getParameter("password").toString());
		// End fix bug ID 12 – PhuongNH 2016/08/04s

		// set data into session
		session.setAttribute("loginId", loginId);
		session.setAttribute("password", password);

		// validate
		lsErrMessage = ValidateUser.validateLogin(loginId, password);

		if (lsErrMessage.size() > 0) { // go to page login case error
			template = Constant.ADM001;
		} else {
			response.sendRedirect(contextPath + "/ListUser.do");
			return;
		}
		// Set lsErrMessage to request
		request.setAttribute("lsErrMessage", lsErrMessage);

		// forward ADM001
		RequestDispatcher req = request.getRequestDispatcher(template);
		req.forward(request, response);
	}
}
