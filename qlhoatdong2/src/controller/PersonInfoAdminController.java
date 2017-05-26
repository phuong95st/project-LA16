package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dao.impl.OnlDaoImpl;
import dao.impl.ScheStuDaoImpl;
import dao.impl.TeachDaoImpl;
import dao.impl.UserDaoImpl;
import entity.User;
import utils.Common;
import utils.Constant;
import utils.MessageProperties;
import utils.StringUtils;
import validate.ValidateUser;

/**
 * Servlet implementation class PersonInfoAdminController
 */
@WebServlet("/admin/info.ad")
public class PersonInfoAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(Constant.ACTION);
		if ("add".equals(action)) {
			request.setAttribute(Constant.ACTION, "hoso");
			request.setAttribute("event", "add");
			request.getRequestDispatcher("add_update_person.jsp").forward(
					request, response);
			return;
		}else if("editImage".equals(action)){
			User user = new UserDaoImpl().getUserById(Integer.parseInt(request.getParameter("userId")));
			request.setAttribute(Constant.ACTION, "hoso");
			request.setAttribute("name", user.getName());
			request.setAttribute("userId", user.getUserId());
			request.getRequestDispatcher("addUserSuccess.jsp").forward(
					request, response);
			return;
		}else if("editInfo".equals(action)){
			int userId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
			} catch (NumberFormatException e) {
				request.setAttribute("error", MessageProperties.getData("ERR20"));
				request.getRequestDispatcher("page_error.jsp").forward(request, response);
				return;
			}
			User user = new UserDaoImpl().getUserById(userId);
			request.setAttribute(Constant.ACTION, "hoso");
			request.setAttribute("event", "edit");
			request.setAttribute("user", user);
			request.getRequestDispatcher("add_update_person.jsp").forward(
					request, response);
			return;
		}
		// get danh sách nhân viên
		List<User> lisUser = new UserDaoImpl().getListUserByRole(false);
		request.setAttribute("lisUser", lisUser);
		request.setAttribute(Constant.ACTION, "hoso");
		request.getRequestDispatcher("person_manager.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(Constant.ACTION);
		if ("load_info".equals(action)) {
			String userId = request.getParameter("userId");
			User user = null;
			if ("none".equals(userId)) {
				HttpSession session = request.getSession();
				user = (User) session.getAttribute("user");
			} else {
				int uId = Integer.parseInt(userId);
				// lấy thông tin về user
				user = new UserDaoImpl().getUserById(uId);
			}
			request.setAttribute("user", user);
			request.getRequestDispatcher("load/load_info.jsp").forward(request,
					response);
			return;
		} else if ("add".equals(action) || "edit".equals(action)) {
			// lấy data
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			String sex = request.getParameter("sex");
			String que = request.getParameter("que");
			String cmt = request.getParameter("cmt");
			String danToc = request.getParameter("dantoc");
			String title = request.getParameter("title");
			String office = request.getParameter("lamviec");
			String hienTai = request.getParameter("hientai");
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			String fax = request.getParameter("fax");
			String tieuSu = request.getParameter("tieusu");
			String monDay = request.getParameter("monday");
			String huongNc = request.getParameter("huongnc");
			String congBo = request.getParameter("congbo");
			String xuatBan = request.getParameter("xuatban");
			String other = request.getParameter("other");
			String role = request.getParameter("role");
			int userId = 0;

			try {
				userId = Integer.parseInt(request.getParameter("userId"));
			} catch (NumberFormatException e) {
				userId = 0;
			}

			// add to obj
			User user = new User();
			if(userId != 0){
				user.setUserId(userId);
			}
			user.setName(name);
			user.setBirthDay(new Date(Common.getYearByString(birth) - 1900, Common
					.getMonthByString(birth) - 1, Common
					.getDayOfMonthByString(birth)));
			if ("nam".equals(sex)) {
				user.setSex(true);
			} else {
				user.setSex(false);
			}
			user.setQueQuan(que);
			user.setCmt(cmt);
			user.setDanToc(danToc);
			user.setTitle(title);
			user.setOffice(office);
			user.setAddressNow(hienTai);
			user.setEmail(email);
			user.setPhone(tel);
			user.setFax(fax);
			user.setCongTac(tieuSu);
			user.setListSubject(monDay);
			user.setResearch(huongNc);
			user.setReleasedEngine(congBo);
			user.setReleasedBook(xuatBan);
			user.setOther(other);
			if ("1".equals(role)) {
				user.setRole(true);
			} else {
				user.setRole(false);
			}
			StringBuilder pass = new StringBuilder();
			pass.append(StringUtils.escapeFullName(name)).append(String.valueOf(Common.getMonthByString(birth))).append(String.valueOf(Common.getYearByString(birth)));
			System.out.println(StringUtils.escapeFullName(pass.toString()));
			user.setPass(Common.encodeMD5(StringUtils.escapeFullName(pass.toString())));

			// validate
			List<String> lisError = new ValidateUser().validateUser(email, tel,cmt, userId);
			if (lisError.size() != 0) {
				request.setAttribute("lisError", lisError);
				request.setAttribute("user", user);
				request.setAttribute("event", action);
				request.setAttribute(Constant.ACTION, "hoso");
				request.getRequestDispatcher("add_update_person.jsp").forward(
						request, response);
				return;
			}
			
			if(user.getUserId() == 0){
				// add
				int generatedKey = new UserDaoImpl().addUser(user);
				if(generatedKey == 0){
					request.setAttribute("error", MessageProperties.getData("ERR21"));
					request.getRequestDispatcher("page_error.jsp").forward(request, response);
					return;
				}
				request.setAttribute("name", name);
				request.setAttribute("userId", generatedKey);
				request.setAttribute(Constant.ACTION, "hoso");
				request.setAttribute("message", MessageProperties.getData("MSG06"));
				request.getRequestDispatcher("addUserSuccess.jsp").forward(request, response);
				return;
			}else{
				// edit
				if(!new UserDaoImpl().updateUser(user)){
					request.setAttribute("error", MessageProperties.getData("ERR21"));
					request.getRequestDispatcher("page_error.jsp").forward(request, response);
					return;
				}
				request.setAttribute("name", name);
				request.setAttribute("userId", userId);
				request.setAttribute(Constant.ACTION, "hoso");
				request.setAttribute("message", MessageProperties.getData("MSG07"));
				request.getRequestDispatcher("addUserSuccess.jsp").forward(request, response);
				return;
			}
		}else{
			int userId = Integer.parseInt(request.getParameter("userId"));
			// kiểm tra có dữ liệu liên quan
			// nếu có thì ko thực hiện xóa mà thông báo 
			OutputStream out = response.getOutputStream();
			JSONObject jsonObject = new JSONObject();
			if(new TeachDaoImpl().checkTeachByUserId(userId) || new OnlDaoImpl().checkOnlByUserId(userId) || new ScheStuDaoImpl().checkByUserId(userId)){
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, MessageProperties.getData("MSG08"));
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			// delete
			if(!new UserDaoImpl().delUser(userId)){
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, MessageProperties.getData("ERR21"));
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG09"));
			out.write(jsonObject.toJSONString().getBytes("UTF-8"));
			out.flush();
			return;
		}
	}
}
