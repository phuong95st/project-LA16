package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.HocKyLogic;

import org.json.simple.JSONObject;

import utils.Common;
import utils.Constant;
import utils.MessageProperties;
import validate.ValidateNamHoc;
import dao.WeekDao;
import dao.impl.HocKyDaoImpl;
import dao.impl.WeekDaoImpl;
import entity.HocKy;
import entity.Week;

/**
 * Servlet implementation class NamHocAdminController
 */
@WebServlet("/admin/nam_hoc.ad")
public class NamHocAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(Constant.ACTION);
		if("view".equals(action)){
			// get nam học
			String namHoc = request.getParameter("namHoc");
			if(namHoc == null || namHoc == ""){
				request.setAttribute("error", MessageProperties.getData("ERR20"));
				request.getRequestDispatcher("page_error.jsp").forward(request, response);
				return;
			}
			// get list học kỳ từ năm học theo thứ tự học kỳ
			List<HocKy> listHocKy = new HocKyDaoImpl().getLisHocKy(namHoc);
			if(listHocKy.size() == 0){
				request.setAttribute("error", MessageProperties.getData("ERR21"));
				request.getRequestDispatcher("page_error.jsp").forward(request, response);
				return;
			}
			List<Week> listWeek = new ArrayList<>();
			WeekDao weekDao = new WeekDaoImpl();
			for (HocKy hocKy : listHocKy) {
				for (Week week : weekDao.getListWeek(hocKy.getName())) {
					listWeek.add(week);
				}
			}
			// add to request
			request.setAttribute("listWeek", listWeek);
			request.setAttribute("namHoc", namHoc);
			request.setAttribute(Constant.ACTION, "nam_hoc");
			request.getRequestDispatcher("view_nam_hoc.jsp").forward(request, response);
			return;
		}
		// lấy danh sách năm học 
		List<String> listNamHoc = new HocKyDaoImpl().getListNamHoc();
		request.setAttribute("listNamHoc", listNamHoc);
		
		request.setAttribute(Constant.ACTION, "nam_hoc");
		request.getRequestDispatcher("ql_nam_hoc.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(Constant.ACTION);
		if("add".equals(action)){
			// get params
			String namHoc = request.getParameter("namHoc");
			String startDate = request.getParameter("startDate");
			String total1 = request.getParameter("total1");
			String total2 = request.getParameter("total2");
			String total3 = request.getParameter("total3");
			// validate
			List<String> listError = new ValidateNamHoc().checkData(namHoc, startDate, total1, total2, total3);
			OutputStream out = response.getOutputStream();
			JSONObject jsonObject = new JSONObject();
			if(listError.size() != 0){
				StringBuilder data = new StringBuilder();
				int i = 0;
				for (String string : listError) {
					data.append("<span class=\"small red\">");
					if (i != 0) {
						data.append("<br>");
						data.append(string);
					} else {
						data.append(string);
					}
					data.append("</span>");
					i++;
				}
				
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, data.toString());
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			// convert params
			Date date = new Date(Common.getYearByString(startDate) - 1900, Common.getMonthByString(startDate)-1, Common.getDayOfMonthByString(startDate));
			int count1 = Integer.parseInt(total1);
			int count2 = Integer.parseInt(total2);
			int count3 = Integer.parseInt(total3);
			
			// add
			if(!new HocKyLogic().creatNamHoc(namHoc, date, count1, count2, count3)){
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put("addFail", true);
				jsonObject.put(Constant.DATA, MessageProperties.getData("MSG11"));
				out.write(jsonObject.toJSONString().getBytes("UTF-8"));
				out.flush();
				return;
			}
			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG15"));
			out.write(jsonObject.toJSONString().getBytes("UTF-8"));
			out.flush();
			return;
		}
	}
	
	
}
