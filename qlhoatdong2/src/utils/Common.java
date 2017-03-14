/**
 * Copyright(C) 2017 Luvina software company
 * Common.java, Mar 2, 2017 nguyenhuuphuong
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Common {
	/**
	 * Mã hóa dữ liệu theo mã hóa MD5
	 * 
	 * @param content
	 *            dữ liệu đầu vào
	 * @return dữ liệu đã mã hóa
	 */
	public static String encodeMD5(String content) {
		if (content == null) {
			return null;
		}
		byte[] defaultBytes = content.getBytes();

		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			content = hexString + "";
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Not encode!");
		}

		return content;
	}

	/**
	 * Lấy ngày cuối tuần của tuần hiện tại
	 * 
	 * @return đối tượng Timestamp là ngày cuối tuần hiện tại
	 */
	public static Timestamp getLastDayOfWeek() {
		Calendar cal = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		// set thời gian về cuối ngày
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		// lấy ngày trong tuần (Chủ nhât = 1, Thứ 7 = 0)
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int range = 0;
		// xét các ngày khác chủ nhật
		if (dayOfWeek != 1) {
			// nếu là thứ 7
			if (dayOfWeek == 0) {
				range = 1;
			} else {
				range = 7 - (dayOfWeek - 1);
			}
		}

		return new Timestamp(cal.getTimeInMillis() + range * Constant.ONE_DAY);
	}

	/**
	 * Lấy ngày cuối tuần của tuần trc
	 * 
	 * @return đối tượng Timestamp là ngày cuối tuần trước
	 */
	public static Timestamp getStartDay() {
		return new Timestamp(getLastDayOfWeek().getTime()
				- (7 * Constant.ONE_DAY));
	}

	/**
	 * Lấy thời gian bắt đầu ngày hôm nay
	 * 
	 * @return đối tượng Timestamp là thời gian bắt đầu ngày hiện tại
	 */
	public static Timestamp getStartNow() {
		Calendar cal = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		// set thời gian về đầu ngày
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * Lấy thời gian kết thúc ngày hiện tại
	 * 
	 * @return đối tượng Timestamp là thời gian kêt thúc ngày hiện tại
	 */
	public static Timestamp getEndNow() {
		Calendar cal = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		// set thời gian về đầu ngày
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return new Timestamp(cal.getTimeInMillis());
	}

	public static String getHour(Timestamp time) {
		Calendar calendar = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		calendar.setTimeInMillis(time.getTime());
		return new SimpleDateFormat("HH:mm").format(calendar.getTime());
	}

	public static String mapType(int type) {
		if (type == 1) {
			return "Buổi sáng";
		} else if (type == 2) {
			return "Buổi chiều";
		}
		return "Cả ngày";
	}

	public static String mappingTypeStudent(int typeStudent) {
		if (typeStudent == 1) {
			return "Project 1: Nhập môn";
		} else if (typeStudent == 2) {
			return "Project 2: Phân tích và thiết kế HTTT";
		} else if (typeStudent == 3) {
			return "Project 3: Định hướng công nghệ";
		}
		return "Đồ án tốt nghiệp";
	}

	/**
	 * Lấy ngày trong chuỗi Timestamp
	 * 
	 * @param time
	 * @return
	 */
	public static String getDay(Timestamp time) {
		Calendar calendar = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		calendar.setTimeInMillis(time.getTime());
		return new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
	}

	/**
	 * 
	 * @param status
	 * @return
	 */
	public static String mappingStatus(boolean status) {
		if (status) {
			return "OK";
		}
		return "NA";
	}

	/**
	 * parse chuỗi ngày tháng dạng dd-MM-yyyy hoặc dd/MM/yyyy
	 * 
	 * @param time
	 *            chuỗi thời gian
	 * @return null nếu không thể parse được. Map nếu parse thành công
	 */
	public static Map<String, Integer> parseTime(String time) {
		if (time == null) {
			return null;
		}
		if (time.matches("^[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}$")) {
			String[] split = null;
			split = time.split("-");
			Map<String, Integer> map = new HashMap<>();
			map.put(Constant.DAY, Integer.parseInt(split[0]));
			map.put(Constant.MONTH, Integer.parseInt(split[1]));
			map.put(Constant.YEAR, Integer.parseInt(split[2]));
			return map;
		}
		if (time.matches("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$")) {
			String[] split = null;
			split = time.split("/");
			Map<String, Integer> map = new HashMap<>();
			map.put(Constant.DAY, Integer.parseInt(split[0]));
			map.put(Constant.MONTH, Integer.parseInt(split[1]));
			map.put(Constant.YEAR, Integer.parseInt(split[2]));
			return map;
		}
		return null;
	}

	public static void main(String[] args) {
		Map<String, Integer> map = Common.parseTime("16-3-2017");
		System.out.println(map.get(Constant.MONTH));
		

	}
}
