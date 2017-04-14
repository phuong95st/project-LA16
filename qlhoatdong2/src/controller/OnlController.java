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

import utils.Constant;
import utils.MessageProperties;
import dao.OnlDao;
import dao.impl.OnlDaoImpl;
import dao.impl.PositionDaoImpl;
import entity.Onl;
import entity.Position;

/**
 * Servlet implementation class OnlController
 */
@WebServlet("/jsp/onl.htm")
public class OnlController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("position".equals(action)) {
			double latitude = Double.parseDouble(request
					.getParameter("latitude"));
			double longitude = Double.parseDouble(request
					.getParameter("longitude"));
			long onlId = Long.parseLong(request.getParameter("id"));

			// lấy tọa độ phòng B1-502
			Position position = new PositionDaoImpl().getPositionById("B1-502");

			JSONObject jsonObject = new JSONObject();
			ServletOutputStream out = response.getOutputStream();
			if (position == null) {
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("ERR12"));
				out.write(jsonObject.toJSONString().getBytes());
				out.flush();
				return;
			}
			// check vị trí
			// if(!checkPosition(position, latitude, longitude)){
			// jsonObject.put(Constant.STATUS, false);
			// jsonObject.put(Constant.DATA,MessageProperties.getData("ERR13"));
			// out.write(jsonObject.toJSONString().getBytes());
			// out.flush();
			// return;
			// }
			if (!(latitude >= 20 && latitude <= 22 && longitude >= 105 && longitude <= 106)) {
				String error = MessageProperties.getData("ERR09");
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA, error);
				out.write(jsonObject.toJSONString().getBytes());
				out.flush();
				return;
			}
			// check thời giạn click nhận giao ban
			Calendar cal = Calendar.getInstance();
			cal.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
			Time now = new Time(cal.get(Calendar.HOUR_OF_DAY),
					cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));

			// lấy lịch trực bởi id
			OnlDao onlDao = new OnlDaoImpl();
			Onl onl = onlDao.getOnlById(onlId);
			if (now.after(onl.getTimeStart()) && now.before(onl.getTimeEnd())) {
				// tính thời gian muộn
				int lateMin = (int) Math.round((double) ((now.getTime() - onl
						.getTimeStart().getTime()) / 1000) / 60);
				// thay đổi trạng thái
				onl.setLate(true);
				onl.setLateMin(lateMin);
			} else if (now.after(onl.getTimeEnd())) {
				// nghỉ
				String error = MessageProperties.getData("ERR14");
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put("flagHol", true);
				jsonObject.put(Constant.DATA, error);
				out.write(jsonObject.toJSONString().getBytes());
				out.flush();
				return;
			}

			// cập nhật trạng thái nghỉ
			onl.setHol(false);
			// cập nhật
			if (!onlDao.update(onl, true)) {
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,
						MessageProperties.getData("ERR10"));
				out.write(jsonObject.toJSONString().getBytes());
				out.flush();
				return;
			}
			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG04"));
			out.write(jsonObject.toJSONString().getBytes());
			out.flush();
			return;
		} else if ("addReason".equals(action)) {
			JSONObject jsonObject = new JSONObject();
			ServletOutputStream out = response.getOutputStream();

			String reason = request.getParameter("reason");
			long onlId = Long.parseLong(request.getParameter("id"));
			// update lý do
			if (!new OnlDaoImpl().setReason(reason, onlId)) {
				jsonObject.put(Constant.STATUS, false);
				jsonObject.put(Constant.DATA,MessageProperties.getData("ERR10"));
				out.write(jsonObject.toJSONString().getBytes());
				out.flush();
				return;
			}
			jsonObject.put(Constant.STATUS, true);
			jsonObject.put(Constant.DATA, MessageProperties.getData("MSG01"));
			out.write(jsonObject.toJSONString().getBytes());
			out.flush();
			return;
		}
	}

	/**
	 * check vị trí khi nhận giao ban
	 * 
	 * @param position
	 *            tọa độ vị trí phòng B1-502
	 * @param latitude
	 *            vĩ độ lấy được ở vị trí hiện tại
	 * @param longitude
	 *            kinh độ ở vị trí hiện tại
	 * @return true nếu vị trí hiện tại trùng với vị trí phòng B1-502. flase nếu
	 *         ngược lại không trùng
	 */
	public boolean checkPosition(Position position, double latitude,
			double longitude) {
		double latitudeMin = (double) Math
				.round(position.getLatitude() * 1000000) / 1000000;
		double latitudeMax = (double) Math
				.round(position.getLatitude() * 1000000) / 1000000 + 0.000001;
		double longitudeMin = (double) Math
				.round(position.getLongitude() * 10000000) / 10000000;
		double longitudeMax = (double) Math
				.round((position.getLongitude()) * 10000000) / 10000000 + 0.0000001;
		System.out.println(latitudeMin + " <= " + latitude + " <= "
				+ latitudeMax);
		System.out.println(longitudeMin + " <= " + longitude + " <= "
				+ longitudeMax);
		return latitudeMin <= latitude && latitude <= latitudeMax
				&& longitudeMin <= longitude && longitude <= longitudeMax;
	}

}
