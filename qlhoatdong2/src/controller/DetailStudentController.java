package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CheckInStudentDao;
import dao.impl.CheckInStudentDaoImpl;
import dao.impl.ScheStuDaoImpl;
import entity.CheckInStudent;
import entity.ScheStu;
import utils.Constant;

/**
 * Servlet implementation class DetailStudentController
 */
@WebServlet("/jsp/student_detail.htm")
public class DetailStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(Constant.ACTION);
		if("view".equals(action)){
			int scheStuId = Integer.parseInt(request.getParameter("scheStuId"));
			// get lịch gặp sinh viên
			ScheStu scheStu = new ScheStuDaoImpl().getScheStuById(scheStuId);
			// get tiến độ
			List<CheckInStudent> listCheck = new CheckInStudentDaoImpl().getListCheckInByScheStu(scheStu);
			request.setAttribute("scheStu", scheStu);
			request.setAttribute("listCheck", listCheck);
			
			request.setAttribute(Constant.ACTION, "studentManager");
			request.getRequestDispatcher("student_detail.jsp").forward(request, response);
			return;
		}else if("edit".equals(action)){
			long checkId = Long.parseLong(request.getParameter("checkId"));
			
			// lấy checkId tương ứng
			CheckInStudent checkInStudent = new CheckInStudentDaoImpl().getCheckInStudentById(checkId);
			request.setAttribute("checkInStudent", checkInStudent);
			
			request.setAttribute(Constant.ACTION, "studentManager");
			request.getRequestDispatcher("add_update_tiendo.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// lấy giá trị form tiến độ
		long checkId = Long.parseLong(request.getParameter("id"));
		String tmpStatus = request.getParameter("status");
		boolean status = false;
		if("1".equals(tmpStatus)){
			status = true;
		}
		String content = request.getParameter("noidung");
		String other = request.getParameter("ghichu");
		
		CheckInStudentDao checkDao = new CheckInStudentDaoImpl();
		CheckInStudent check = checkDao.getCheckInStudentById(checkId);
		check.setContent(content);
		check.setStatus(status);
		check.setOther(other);
		
		// edit
		if(!checkDao.edit(check)){
			PrintWriter out = response.getWriter();
			out.print("System error!");
			return;
		}
		
		response.sendRedirect("student_manager.htm");
	}

}
