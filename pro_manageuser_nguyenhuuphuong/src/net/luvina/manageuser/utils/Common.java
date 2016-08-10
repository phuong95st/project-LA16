/**
 * Copyright(C) 2016 Luvina Software Company
 * Common.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.utils;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

/**
 * @author PhuongNH
 *
 */
public class Common {
	/**
	 * Lấy danh sách các bản ghi cần hiển thị trong trang hiện tại
	 *
	 * @param totalRecords
	 *            tổng số bản ghi list đc
	 * @param limit
	 *            số bản ghi trong 1 trang
	 * @param currentPage
	 *            trang hiện tại
	 * @return list các page cần hiển thị trong trang hiện tại. Nếu trang hiện
	 *         tại không tồn tại thì trả về null
	 */
	public static ArrayList<Integer> getListPaging(int totalRecords, int limit,
			int currentPage) {
		ArrayList<Integer> listPaging = null;
		// Tính tổng số trang
		int totalPage = (int) Math.ceil((double) totalRecords / limit);
		// vòng lặp chạy từ 1 đến tổng số trang, bước nhảy 3
		for (int i = 1; i <= totalPage; i += 3) {
			// nếu không tồn tai trang hiện tại
			if (currentPage > totalPage) {
				break;
			}
			// nếu trang hiện tại nằm trong khoảng từ biến chạy i đến i+3
			if (currentPage >= i && currentPage < (i + 3)) {
				// khởi tạo listPaging
				listPaging = new ArrayList<Integer>();
				// add trang i vào list
				listPaging.add(i);
				// tăng i lên 1
				int j = i + 1;
				// kiểm tra đã vượt quá tổng số trang chưa. nếu chưa thì add
				if (j <= totalPage) {
					listPaging.add(i + 1);
				}
				// tăng i lên 2
				j++;
				// kiểm tra tương tự
				if (j <= totalPage) {
					listPaging.add(i + 2);
				}
				break;
			}
		}
		return listPaging;
	}

	/**
	 * Paging
	 *
	 * @param record_num
	 *            int
	 * @param record_per_page
	 *            int
	 * @param page_range
	 *            int
	 * @param url
	 *            String
	 * @param pageid
	 *            int
	 * @param f_name_js
	 *            String
	 * @return String paging
	 */
	public static String paging(int record_num, int record_per_page,
			int page_range, String url, int pageid, String f_name_js) {

		String separator = "";
		String pre_str = "&lsaquo;";
		String next_str = "&rsaquo;";
		String first_str = "&laquo;";
		String last_str = "&raquo;";

		if (record_per_page == 0) {
			return "";
		}

		String st = "";
		String cc;
		if (url.indexOf("?") > -1) {
			cc = "&";
		} else {
			cc = "?";
		}

		int page_num;
		page_num = (int) Math.ceil((double) record_num / record_per_page);

		pageid = (int) pageid;

		if (pageid < 1) {
			pageid = 1;
		}

		if (pageid > page_num) {
			pageid = page_num;
		}

		if (page_num < page_range) {
			page_range = page_num;
		}

		int division;
		int page_lim1 = 0;
		int page_lim2 = 0;

		if (page_range == 0) {
			division = 0;
		} else {
			division = (int) Math.floor(page_num / page_range);
		}

		for (int i = 0; i <= division; i++) {
			if (((i * page_range + 1) <= pageid)
					&& (((i + 1) * page_range) >= pageid)) {
				page_lim1 = i * page_range + 1;
				page_lim2 = page_lim1 + page_range - 1;
				if (page_lim2 > page_num) {
					page_lim2 = page_num;
				}
				break;
			}
		}

		String urlEncode = url;

		if (page_num > 1) {
			if (pageid > 1) {
				if (f_name_js.trim().compareTo("") != 0) {
					if (first_str.trim().compareTo("") != 0)
						st += " <a href='javascript:" + f_name_js + "(\""
								+ urlEncode + "\", 1)'  class='txtBlue'>"
								+ first_str + "</a>";
					if (pre_str.trim().compareTo("") != 0)
						st += separator + " <a href='javascript:" + f_name_js
								+ "(\"" + urlEncode + "\", " + (pageid - 1)
								+ ")' class='txtBlue'>" + pre_str + "</a> ";
				} else {
					if (first_str.trim().compareTo("") != 0)
						st += " <a href='" + url + cc
								+ "page=1&isBack=1' class='txtBlue'>"
								+ first_str + "&nbsp;</a>";
					if (pre_str.trim().compareTo("") != 0)
						st += " <a href='" + url + cc + "page=" + (pageid - 1)
								+ "&isBack=1' class='txtBlue'>" + pre_str
								+ "</a> ";
				}
			}
		}

		for (int i = page_lim1; i < pageid; i++) {
			if (f_name_js.trim().compareTo("") != 0) {
				st += separator + "<a href='javascript:" + f_name_js + "(\""
						+ urlEncode + "\", " + i + ")' class='txtBlue'>" + i
						+ "</a> ";
			} else {
				st += separator + "<a href='" + url + cc + "page=" + i
						+ "&isBack=1' class='txtBlue'>" + i + "</a> ";
			}
		}

		st += separator + "<span class='txtGrey11'>" + pageid + "</span>&nbsp;";

		for (int i = pageid + 1; i <= page_lim2; i++) {
			if (f_name_js.trim().compareTo("") != 0) {
				st += separator + "<a href='javascript:" + f_name_js + "(\""
						+ urlEncode + "\", " + i + ")' class='txtBlue'>" + i
						+ "</a> ";
			} else {
				st += separator + "<a href='" + url + cc + "page=" + i
						+ "&isBack=1' class='txtBlue'>" + (i) + "</a> ";
			}
		}
		st += separator;

		if (page_num > 1) {
			if (pageid + 1 <= page_num) {
				if (f_name_js.trim().compareTo("") != 0) {
					if (next_str.trim().compareTo("") != 0)
						st += " <a href='javascript:" + f_name_js + "(\""
								+ urlEncode + "\", " + (pageid + 1)
								+ ")' class='txtBlue'>" + next_str + "</a>";
					if (last_str.trim().compareTo("") != 0)
						st += separator + " &nbsp;<a href='javascript:"
								+ f_name_js + "(\"" + urlEncode + "\", "
								+ page_num + ")' class='txtBlue'>" + last_str
								+ "</a>";
				} else {
					if (next_str.trim().compareTo("") != 0)
						st += " <a href='" + url + cc + "page=" + (pageid + 1)
								+ "&isBack=1' class='txtBlue'>" + next_str
								+ "</a>";
					if (last_str.trim().compareTo("") != 0)
						st += "  <a href='" + url + cc + "page=" + page_num
								+ "&isBack=1' class='txtBlue'>" + last_str
								+ "</a>";
				}
			}
		} else {
			st = "";
		}
		return st;
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
		String tem = str.replace("'", "''");
		tem = tem.replace("\\", "\\\\");
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

//	 public static void main(String[] args) {
//	 Common common = new Common();
//	 ArrayList<Integer> listpaging = common.getListPaging(3, 5, 1);
//
//	 System.out.println(listpaging);
//	 }
}
