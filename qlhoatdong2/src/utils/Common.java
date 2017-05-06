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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import entity.Onl;
import entity.Position;
import entity.Teach;

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
	 * Lấy thời gian hiện tại
	 * 
	 * @return đối tượng Timestamp là thời gian hiện tại
	 */
	public static Timestamp getNow() {
		Calendar cal = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
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

	public static String getHour(Date time) {
		if (time == null) {
			return null;
		}
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
	public static String getDay(Date time) {
		if (time == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		calendar.setTimeInMillis(time.getTime());
		return new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
	}

	/**
	 * Lấy thứ dạng chuỗi
	 * 
	 * @param time
	 *            Timestamp
	 * @return thứ trong tuần dạng chuỗi
	 */
	public static String getThu(Timestamp time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time.getTime());
		return new SimpleDateFormat("E").format(calendar.getTime());
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

	/**
	 * Check vị trí của người dùng
	 * 
	 * @param teach
	 * @param position
	 * @return
	 */
	public static boolean checkPosition(Teach teach, Position position) {
		System.out.println(( Math.floor(teach.getPhong().getLatitude() * 1000000) / 1000000)+ "<="+ position.getLatitude()+ "<="+ ( Math.ceil(teach.getPhong().getLatitude() * 1000000) / 1000000));
		System.out.println(( Math.floor(teach.getPhong().getLongitude() * 10000000) / 10000000)+ "<="+ position.getLongitude()+ "<="+ ( Math.ceil((teach.getPhong().getLongitude()) * 10000000) / 10000000));
		return Math.floor(teach.getPhong().getLatitude() * 1000000) / 1000000 <= position.getLatitude()&& position.getLatitude() <= (Math.ceil(teach.getPhong().getLatitude() * 1000000) / 1000000) && Math.floor(teach.getPhong().getLongitude() * 10000000) / 10000000 <= position.getLongitude()&& position.getLongitude() <= (Math.ceil((teach.getPhong().getLongitude()) * 10000000) / 10000000);
	}

	public static String mapDayOfWeek(int dayOfWeek) {
		switch (dayOfWeek) {
		case 1:
			return "CN";
		case 2:
			return "T2";
		case 3:
			return "T3";
		case 4:
			return "T4";
		case 5:
			return "T5";
		case 6:
			return "T6";
		default:
			return "T7";
		}
	}

	public static int getHourByString(String time) {
		if (time == null) {
			return 0;
		}
		if (time.matches("^[0-9]{1,2}:[0-9]{1,2}$")) {
			String[] split = time.split(":");
			return Integer.parseInt(split[0]);
		}
		return 0;
	}

	public static int getMinByString(String time) {
		if (time == null) {
			return 0;
		}
		if (time.matches("^[0-9]{1,2}:[0-9]{1,2}$")) {
			String[] split = time.split(":");
			return Integer.parseInt(split[1]);
		}
		return 0;
	}

	public static int getYearByString(String date) {
		if (date == null) {
			return 0;
		}
		if (date.matches("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$")) {
			String[] split = date.split("/");
			return Integer.parseInt(split[2]);
		}
		return 0;
	}

	public static int getMonthByString(String date) {
		if (date == null) {
			return 0;
		}
		if (date.matches("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$")) {
			String[] split = date.split("/");
			return Integer.parseInt(split[1]);
		}
		return 0;
	}

	public static int getDayOfMonthByString(String date) {
		if (date == null) {
			return 0;
		}
		if (date.matches("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$")) {
			String[] split = date.split("/");
			return Integer.parseInt(split[0]);
		}
		return 0;
	}

	public static boolean isEmail(String email) {
		if (email == null) {
			return false;
		}
		return email
				.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		if (phoneNumber == null) {
			return false;
		}
		return phoneNumber.matches("(\\+84|0)\\d{9,10}");
	}

	public static boolean isCMT(String cmt) {
		if (cmt == null) {
			return false;
		}
		String regex = "^[0-9]{9}$";
		if (!cmt.matches(regex)) {
			regex = "^[0-9]{12}$";
			return cmt.matches(regex);
		}
		return true;
	}

	public static boolean checkData(Teach teach, List<Teach> lisTeach) {
		for (Teach item : lisTeach) {
			if (teach.getDateOfWeek() == item.getDateOfWeek()
					&& checkTime(teach, item) && checkWeek(teach, item)) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkTime(Teach teach1, Teach teach2) {
		return (teach1.getTimeStart().compareTo(teach2.getTimeStart()) >= 0 && teach1
				.getTimeStart().compareTo(teach2.getTimeEnd()) <= 0)
				|| (teach1.getTimeEnd().compareTo(teach2.getTimeStart()) >= 0 && teach1
						.getTimeEnd().compareTo(teach2.getTimeEnd()) <= 0)
				|| (teach1.getTimeStart().compareTo(teach2.getTimeStart()) < 0 && teach1
						.getTimeEnd().compareTo(teach2.getTimeEnd()) > 0);
	}

	public static boolean checkWeek(Teach teach1, Teach teach2) {
		return (teach1.getWeekStart().getWeekCount() >= teach2.getWeekStart()
				.getWeekCount() && teach1.getWeekStart().getWeekCount() <= teach2
				.getWeekEnd().getWeekCount())
				|| (teach1.getWeekEnd().getWeekCount() >= teach2.getWeekStart()
						.getWeekCount() && teach1.getWeekEnd().getWeekCount() <= teach2
						.getWeekEnd().getWeekCount());
	}

	public static boolean checkData(Onl onl, List<Onl> listOnl) {
		for (Onl item : listOnl) {
			if (onl.getDateOfWeek() == item.getDateOfWeek()
					&& checkTime(onl, item)
					&& onl.getWeek().getWeekId() == item.getWeek().getWeekId()) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkTime(Onl onl1, Onl onl2) {
		return (onl1.getTimeStart().compareTo(onl2.getTimeStart()) >= 0 && onl1
				.getTimeStart().compareTo(onl2.getTimeEnd()) <= 0)
				|| (onl1.getTimeEnd().compareTo(onl2.getTimeStart()) >= 0 && onl1
						.getTimeEnd().compareTo(onl2.getTimeEnd()) <= 0)
				|| (onl1.getTimeStart().compareTo(onl2.getTimeStart()) < 0 && onl1
						.getTimeEnd().compareTo(onl2.getTimeEnd()) > 0);
	}

	public static String getHocKy(String namHoc) {
		if (namHoc == null) {
			return null;
		}
		if (!namHoc.matches("^[0-9]{4}-[0-9]{4}$")) {
			return "";
		}
		return namHoc.substring(0, 4);
	}

}
