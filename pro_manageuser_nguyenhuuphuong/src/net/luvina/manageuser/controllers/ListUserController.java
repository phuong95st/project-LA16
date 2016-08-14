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
import net.luvina.manageuser.utils.Common;
import net.luvina.manageuser.utils.ConfigureProperties;
import net.luvina.manageuser.utils.Constant;
import net.luvina.manageuser.utils.MessageProperties;

/**
 * Điều khiển xử lý logic màn hình ADM002
 *
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
			ArrayList<Integer> listPage = new ArrayList<Integer>();
			HttpSession session = request.getSession();
			String message = null;
			String template = "";
			// khởi tạo mặc định
			String fullnameSearch = "";
			String sortType = "full_name";
			String sortByFullName = Constant.ASC;
			String sortByCodeLevel = Constant.DESC;
			String sortByEndDate = Constant.DESC;
			int groupId = 0;
			int currentPage = 1;
			int start = 0; // phần tử đầu trong list page lấy được
			int end = 0; // phần tử cuối trong list page lấy được
			int offset = 0; // vị trí bắt đầu lấy user trong sql
			// nếu không tồn tại session
			if (!"".equals(Common.checkLogin(session))) { // TODO bug 3
				template = Common.checkLogin(session);
				System.out.println(template);

				// hiển thị ADM001
				RequestDispatcher req = request.getRequestDispatcher(template);
				req.forward(request, response);

			} else {
				// Get data search from request
				if (request.getParameter("full_name") != null) {
					fullnameSearch = Common.escapeHTML(request.getParameter(
							"full_name").toString());
				}
				if (request.getParameter("group_id") != null) {
					groupId = Integer.parseInt(request.getParameter("group_id")
							.toString());
				}
			}

			// get all group
			listGroup = groupLogic.getAllGroups();

			// lấy data mà jsp truyền qua cho việc sort
			String name = (String) request.getParameter("ten");
			String oder = (String) request.getParameter("oder");

			// nếu tồn tại session và click vào sort thì lấy name, group, Page
			// theo session
			if (session.getAttribute("key_search_full_name") != null
					&& session.getAttribute("key_search_group_id") != null
					&& session.getAttribute("currentPage") != null
					&& name != null) {
				fullnameSearch = (String) session
						.getAttribute("key_search_full_name");
				groupId = (Integer) session.getAttribute("key_search_group_id");

				currentPage = (Integer) session.getAttribute("currentPage");
			}

			// nếu người dùng click sort
			if (name != null) {
				sortType = name;
				// nếu click sort của cột full_name
				if ("full_name".equals(name)) {
					// nếu tăng thì giảm
					if (Constant.ASC.equals(oder)) {
						sortByFullName = Constant.DESC;
						sortByCodeLevel = Constant.DESC;
						sortByEndDate = Constant.DESC;
					} else {
						sortByFullName = Constant.ASC;
						sortByCodeLevel = Constant.DESC;
						sortByEndDate = Constant.DESC;
					}
				} else if ("code_level".equals(name)) {
					// nếu tăng thì giảm
					if (Constant.ASC.equals(oder)) {
						sortByCodeLevel = Constant.DESC;
						sortByEndDate = Constant.DESC;
						sortByFullName = Constant.ASC;
					} else {
						sortByCodeLevel = Constant.ASC;
						sortByEndDate = Constant.DESC;
						sortByFullName = Constant.ASC;
					}
				} else {
					// nếu tăng thì giảm
					if (Constant.ASC.equals(oder)) {
						sortByEndDate = Constant.DESC;
						sortByCodeLevel = Constant.DESC;
						sortByFullName = Constant.ASC;
					} else {
						sortByEndDate = Constant.ASC;
						sortByCodeLevel = Constant.DESC;
						sortByFullName = Constant.ASC;
					}

				}
			}

			// get limit
			int limit = Common.getLimit();

			// lấy page mà người dùng vừa click
			String page = request.getParameter("pageId");

			// nếu người dùng click page
			if (page != null) {
				// lấy current = page
				currentPage = Integer.parseInt(page);
			}

			// Nếu người dùng click page và tồn tại điều kiện trc thì lấy
			// theo điều kiện trước, nếu không thì lấy mặc định
			if (session.getAttribute("key_search_full_name") != null
					&& session.getAttribute("sortType") != null && page != null) {

				fullnameSearch = (String) session
						.getAttribute("key_search_full_name");
				groupId = (Integer) session.getAttribute("key_search_group_id");
				sortType = (String) session.getAttribute("sortType");
				sortByFullName = (String) session
						.getAttribute("sortByFullName");
				sortByCodeLevel = (String) session
						.getAttribute("sortByCodeLevel");
				sortByEndDate = (String) session.getAttribute("sortByEndDate");
			}

			// get total records
			int totalRecords = userLogic.getTotalUser(groupId, fullnameSearch);

			// nếu không lấy được bản ghi
			if (totalRecords == 0) {
				message = MessageProperties.getMessage("MSG005");
			} else {
				// lấy tổng số page
				int totalPage = Common.getTotalPage(totalRecords, limit);

				// lấy offset
				offset = Common.getOffset(currentPage, limit);

				// lấy danh sách trang cần hiển thị
				listPage = Common.getListPaging(totalRecords, limit,
						currentPage);

				// lấy phần tử đầu và cuối của listPage chuyển sang jsp để hiển
				// thị >> <<
				if (listPage.size() > 0) {
					start = listPage.get(0);
					end = listPage.get(listPage.size() - 1);
				}

				// set listPage và các giá trị cần thiết cho việc hiển thị
				// paging trong jsp
				request.setAttribute("listPage", listPage);
				request.setAttribute("start", start);
				request.setAttribute("end", end);
				request.setAttribute("totalPage", totalPage);
			}

			// get range
			int range = Integer.parseInt(ConfigureProperties.getData("range"));

			// get list user
			listUser = userLogic.getListUsers(offset, limit, groupId,
					fullnameSearch, sortType, sortByFullName, sortByCodeLevel,
					sortByEndDate);

			// set data to ADM002.jsp
			request.setAttribute("listGroup", listGroup);
			request.setAttribute("listUser", listUser);
			request.setAttribute("full_name", fullnameSearch);
			request.setAttribute("group_id", groupId);

			request.setAttribute("sortType", sortType);
			request.setAttribute("sortByFullName", sortByFullName);
			request.setAttribute("sortByCodeLevel", sortByCodeLevel);
			request.setAttribute("sortByEndDate", sortByEndDate);

			request.setAttribute("totalRecords", totalRecords);
			request.setAttribute("range", range);

			// Set message
			request.setAttribute("message", message);

			// set session
			session.setAttribute("key_search_full_name", fullnameSearch);
			session.setAttribute("key_search_group_id", groupId);
			session.setAttribute("currentPage", currentPage);
			session.setAttribute("sortType", sortType);
			session.setAttribute("sortByFullName", sortByFullName);
			session.setAttribute("sortByCodeLevel", sortByCodeLevel);
			session.setAttribute("sortByEndDate", sortByEndDate);

			// forward to ADM002.jsp
			template = Constant.ADM002;
			RequestDispatcher req = request.getRequestDispatcher(template);
			req.forward(request, response);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
