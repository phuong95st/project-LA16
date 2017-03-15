package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import utils.Common;
import utils.Constant;
import utils.MessageProperties;
import dao.TeachDao;
import dao.impl.TeachDaoImpl;
import entity.Position;
import entity.Teach;

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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
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
//			if(!Common.checkPosition(teach, new Position("",latitude,longitude))){
//				String error = MessageProperties.getData("ERR09");
//				jsonObject.put(Constant.STATUS, false);
//				jsonObject.put(Constant.DATA, error);
//				out.write(jsonObject.toJSONString().getBytes());
//				out.flush();
//				return;
//			}
			if(!(latitude>=21 && latitude <=22 && longitude >=105 && longitude<=106)){
				String error = MessageProperties.getData("ERR09");
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, error);
				out.write(jsonObject.toJSONString().getBytes());
				out.flush();
				return;
			}
			// kiểm tra thời gian login
			Timestamp now = Common.getNow();
			if(now.compareTo(teach.getStart()) > 0 && now.compareTo(teach.getEnd()) <= 0){
				int lateMin = (int)Math.round((double)((now.getTime() - teach.getStart().getTime())/1000)/60);
				HttpSession session = request.getSession();
				session.setAttribute("lateMin", lateMin);
				teach.setLate(true);
				teach.setLateMin(lateMin);
			}
			// thay đổi trạng thái của bản ghi 
			teach.setHol(false);
			if(!teachDao.update(teach)){
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
		}
	}
	

}
