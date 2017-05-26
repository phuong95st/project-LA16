package controller;

import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import utils.Common;
import utils.Constant;
import utils.MessageProperties;
import dao.TeachDao;
import dao.impl.TeachDaoImpl;
import dao.impl.TeachWeekDaoImpl;
import entity.Position;
import entity.Teach;
import entity.TeachWeek;

/**
 * Servlet implementation class TeachController
 */
@WebServlet("/jsp/teach.htm")
public class TeachController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Constant.ACTION, "teach");
		request.getRequestDispatcher("/admin/ql_giang_day.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("position".equals(action)){
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			long id = Long.parseLong(request.getParameter("id"));
			
			// get Teach
			TeachDao teachDao = new TeachDaoImpl();
			Teach teach = teachDao.getTeachById(id);
			JSONObject jsonObject = new JSONObject();
			ServletOutputStream out = response.getOutputStream();
			// check vị trí
			if(!Common.checkPosition(teach, new Position("",latitude,longitude))){
				String error = MessageProperties.getData("ERR09");
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, error);
				out.write(jsonObject.toJSONString().getBytes());
				out.flush();
				return;
			}
//			if(!(latitude>=21 && latitude <=22 && longitude >=105 && longitude<=106)){
//				String error = MessageProperties.getData("ERR09");
//				jsonObject.put(Constant.STATUS, false);
//				jsonObject.put(Constant.DATA, error);
//				out.write(jsonObject.toJSONString().getBytes());
//				out.flush();
//				return;
//			}
			// kiểm tra thời gian login
			Calendar cal = Calendar.getInstance();
			cal.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
			Time now = new Time(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
			// get teach_week
			TeachWeek teachWeek = new TeachWeekDaoImpl().getTeachDaoByTeachId(teach, Common.getNow());
			if(now.after(teach.getTimeStart()) && now.before(teach.getTimeEnd())){
				
				int lateMin = (int)Math.round((double)((now.getTime() - teach.getTimeStart().getTime())/1000)/60);
//				HttpSession session = request.getSession();
//				session.setAttribute("lateMin", lateMin);
				teachWeek.setLate(true);
				teachWeek.setLateMin(lateMin);
			}else if(now.after(teach.getTimeEnd())){
				String error = MessageProperties.getData("ERR11");
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put("flagHol", true);
				jsonObject.put(Constant.DATA, error);
				out.write(jsonObject.toJSONString().getBytes());
				out.flush();
				return;
			}
			// thay đổi trạng thái của bản ghi 
			teachWeek.setHol(false);
			if(!new TeachWeekDaoImpl().update(teachWeek)){
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, MessageProperties.getData("ERR10"));
				out.write(jsonObject.toJSONString().getBytes());
				out.flush();
				return;
			}
			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG04"));
			out.write(jsonObject.toJSONString().getBytes());
			out.flush();
			return;
		}else if("addReason".equals(action)){
			JSONObject jsonObject = new JSONObject();
			ServletOutputStream out = response.getOutputStream();
			
			String reason = request.getParameter("reason");
			long teachWeekId = Long.parseLong(request.getParameter("id")); 
			
			// update lý do
			if(!new TeachWeekDaoImpl().setReason(reason,teachWeekId)){
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, MessageProperties.getData("ERR10"));
				out.write(jsonObject.toJSONString().getBytes());
				out.flush();
				return;
			}
			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG01"));
			out.write(jsonObject.toJSONString().getBytes());
			out.flush();
			return;
		}else if("add".equals(action)){
			int userId = Integer.parseInt(request.getParameter("user"));
			String hocKy = request.getParameter("hocKy");
			System.out.println(userId +" - "+ hocKy);
			request.setAttribute(Constant.ACTION, "teach");
			request.getRequestDispatcher("/admin/add_teach.jsp").forward(request, response);
			return;
		}
	}
	

}
