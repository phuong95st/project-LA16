/**
 * Copyright(C) 2016 Luvina Software Company
 * Common.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.utils;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

/**
 * Chứa các hàm common của dự án
 *
 * @author PhuongNH
 *
 */
public class Common {

	/**
	 * Hàm lấy vị trí bắt đầu của từng nhóm
	 *
	 * @param totalPage
	 *            Tổng số trang
	 * @param pageNumber
	 *            Số trang hiển thị trong 1 lần (Lấy từ file
	 *            configure.propertise)
	 * @return Danh sách chứa các vị trí bắt đầu của từng nhóm
	 */
	public static ArrayList<Integer> getListStart(int totalPage, int range) {
		ArrayList<Integer> listPos = new ArrayList<Integer>();
		for (int i = 1; i <= totalPage; i += range) {
			listPos.add(i);
		}
		return listPos;
	}

	/**
	 * Hàm lấy vị trí bắt đầu của nhóm cần hiển thị. Nếu không lấy được thì trả
	 * về 0
	 *
	 * @param currentPage
	 *            trang hiện tại
	 * @param pageNumber
	 *            số trang cần hiển trị trong 1 lần
	 * @param totalPage
	 *            tổng số trang
	 * @return vị trí bắt đầu của nhóm cần hiển thị.s
	 */
	public static int getStart(int currentPage, int range, int totalPage) {
		ArrayList<Integer> listPos = getListStart(totalPage, range);
		for (Integer pos : listPos) {
			if (currentPage >= pos && currentPage < (pos + range)) {
				return pos;
			}
		}
		return 0;
	}

	/**
	 * Ham lấy dánh sách các trang cần hiển thị trong trang hiện tại
	 *
	 * @param totalRecords
	 *            tổng số bản ghi
	 * @param limit
	 *            số bản ghi trong 1 page
	 * @param currentPage
	 *            trang hiện tại vừa click
	 * @return danh sách các page cần hiển thị
	 */
	public static ArrayList<Integer> getListPaging(int totalRecords, int limit,
			int currentPage) {
		ArrayList<Integer> listPage = new ArrayList<Integer>();
		// get totalPage
		int totalPage = (int) Math.ceil((double) totalRecords / limit);
		// lấy vị trí bắt đầu của nhóm cần hiển thị
		int range = Integer.parseInt(ConfigureProperties.getData("range"));
		int start = getStart(currentPage, range, totalPage);
		// lấy vị trí end
		int len = start + range;
		// lặp từ start đến end
		for (int i = start; i < len; i++) {
			// kiểm tra xem phần tử trong group có lớn hơn totalPage không. Nếu
			// không lớn thì add vào list
			if (i <= totalPage) {
				listPage.add(i);
			}
		}
		return listPage;
	}

	/**
	 * Escape special chars html
	 *
	 * @param content
	 *            String
	 * @return String htmlspecialchars
	 */
	public static String escapeHTML(String content) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);

			switch (c) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;

			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			/*
			 * case '\'': sb.append("&apos;"); break;
			 */
			default:
				sb.append(c);
			}
		}
		content = sb.toString();
		return content;
	}

	/**
	 * escapeInjection
	 *
	 * @param str
	 *            String
	 * @return String escapeInjection
	 */
	public static String escapeInjection(String str) {
		String tem = str.replace("'", "\\'");
		tem = tem.replace("%", "\\%");
		return tem;
	}

	/**
	 * checkLogin
	 *
	 * @param session
	 *            HttpSession
	 * @return ADM001
	 */
	public static String checkLogin(HttpSession session) {
		String template = "";
		if (session.getAttribute("loginId") == null) {
			template = Constant.ADM001;
		}
		return template;

	}

	/**
	 * Lấy vị trí data cần lấy
	 *
	 * @param currentPage
	 *            Trang hiện tại
	 * @param limit
	 *            số lượng cần hiển thị trên 1 trang
	 * @return vị trí cần lấy
	 */
	public static int getOffset(int currentPage, int limit) {
		return limit * ((int) currentPage - 1);
	}

	/**
	 * Lấy số lượng hiển thị bản ghi trên 1 trang
	 *
	 * @return số lượng recorscần lấy
	 */
	public static int getLimit() {
		return Integer.parseInt(ConfigureProperties.getData("limit"));
	}

	/**
	 * Tính tổng số trang
	 *
	 * @param totalRecords
	 *            tổng số User
	 * @param limit
	 *            số lượng cần hiển thị trên 1 trang
	 * @return tổng số trang
	 */
	public static int getTotalPage(int totalRecords, int limit) {
		return (int) Math.ceil((double) totalRecords / limit);
	}
}
