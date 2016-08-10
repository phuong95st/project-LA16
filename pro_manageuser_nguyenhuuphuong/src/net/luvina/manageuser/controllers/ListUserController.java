/**
 * Copyright(C) 2016 Luvina Software Company
 * ListUserController.java, Aug 7, 2016 PhuongNH
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

import net.luvina.manageuser.entities.*;
import net.luvina.manageuser.logics.impl.MstGroupLogicImpl;
import net.luvina.manageuser.logics.impl.TblUserLogicImpl;
import net.luvina.manageuser.logics.impl.UserLogicImpl;
import net.luvina.manageuser.utils.Common;
import net.luvina.manageuser.utils.ConfigureProperties;
import net.luvina.manageuser.utils.Constant;
import net.luvina.manageuser.utils.MessageProperties;

/**
 * @author PhuongNH
 *
 */
public class ListUserController extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 *
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ListUserController() {
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
		doPost(request, response);
	}

	/*
	 * (non-Java-doc)
	 *
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			TblUserLogicImpl userLogic = new TblUserLogicImpl();
			MstGroupLogicImpl groupLogic = new MstGroupLogicImpl();
			List<MstGroup> listGroup = new ArrayList<MstGroup>();
			List<UserInfor> listUser = new ArrayList<UserInfor>();
			HttpSession session = request.getSession();
			UserInfor user = new UserInfor();
			String message = "";
			String template = "";
			String fullnameSearch = "";
			int group_id = 0;
			int page = 0;
			// Start fix bug ID 31 – PhuongNH 2016/08/04
			if (!"".equals(Common.checkLogin(session))) { // TODO bug 3
				template = Common.checkLogin(session);
				System.out.println(template);

				RequestDispatcher req = request.getRequestDispatcher(template);
				req.forward(request, response);

			} else {// Get data search from request
				if (request.getParameter("full_name") != null) {
					// Start fix bug ID 5 – PhuongNH 2016/08/04
					fullnameSearch = Common.escapeHTML(request.getParameter(
							"full_name").toString());
					// End fix bug ID 5– PhuongNH 2016/08/04
				}
				if (request.getParameter("group_id") != null) {
					group_id = Integer.parseInt(request
							.getParameter("group_id").toString());
				}

			}
			//get list group
			listGroup = groupLogic.getAllGroups();

			// get limit
			int limit = Integer.parseInt(ConfigureProperties.getData("limit"));
			// get list user
			listUser = userLogic.getListUsers(0,limit, group_id, fullnameSearch, "full_name", "ASC", "DESC", "DESC");

			// set data to ADM002.jsp
			request.setAttribute("listGroup", listGroup);
			request.setAttribute("listUser", listUser);
			request.setAttribute("full_name", fullnameSearch);
			request.setAttribute("group_id", group_id);

			// forward to ADM002.jsp
			template = Constant.ADM002;
			RequestDispatcher req = request.getRequestDispatcher(template);
			req.forward(request, response);
/*			// End fix bug ID 31– PhuongNH 2016/08/04
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			// Start fix bug ID 7 – PhuongNH 2016/08/04
			if (request.getParameter("clickSearch") != null) {
				page = Integer.parseInt(request.getParameter("clickSearch"));
				if (page == 1) {
					group_id = (Integer) session
							.getAttribute("key_search_group_id");
				}
			}
			// End fix bug ID 7– PhuongNH 2016/08/04

			user.setFullName(fullnameSearch);
			user.setGroupId(group_id);

			// process Paging
			int totalRecord = userLogic.getTotalUsers(user);
			int limit = Integer.parseInt(MessageProperties.getMessage("limit"));
			int totalPage = (int) Math.ceil((double) totalRecord / limit);

			if (page == 0) {
				page = 1;
			}

			if (totalPage == 0) {
				page = 0;
			}
			if (page > totalPage) {
				page = totalPage;
			}
			int offset = (page > 0) ? limit * ((int) page - 1) : 0;

			// create strPaging
			String strPaging = Common.paging(totalRecord, limit, 3,
					"ListUser.do", page, "paging");

			// Search users
			listUser = userLogic.getListUsers(user, offset, limit);

			if (listUser.size() == 0) {
				message = MessageProperties.getMessage("msg_001");
			}

			// Set data search on session
			session.setAttribute("key_search_full_name", fullnameSearch);
			session.setAttribute("key_search_group_id", group_id);

			// Set data to page jsp
			request.setAttribute("listUser", listUser);
			request.setAttribute("strPaging", strPaging);
			request.setAttribute("page", page);

			request.setAttribute("full_name", fullnameSearch);
			request.setAttribute("group_id", group_id);

			// Goto page listUser.jsp
			template = Constant.ADM002;

			// get All Groups
			listGroup = userLogic.getAllGroups();
			request.setAttribute("listGroup", listGroup);

			// Set message
			request.setAttribute("message", message);

			// Forward
			RequestDispatcher req = request.getRequestDispatcher(template);
			req.forward(request, response);
			*/

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
