/**
 * Copyright(C) 2017 Luvina software company
 * Common.java, Mar 2, 2017 nguyenhuuphuong
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	 * định dạng đối tượng Date thành dạng hh:mm
	 * @param time
	 * @return
	 */
	public static String getHour(Date time) {
		if (time == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		calendar.setTimeInMillis(time.getTime());
		return new SimpleDateFormat("HH:mm").format(calendar.getTime());
	}

	/**
	 * Map các trạng thái của lịch trực. 
	 * @param type
	 * @return
	 */
	public static String mapType(int type) {
		if (type == 1) {
			return "Buổi sáng";
		} else if (type == 2) {
			return "Buổi chiều";
		}
		return "Cả ngày";
	}

	/**
	 * Mapping các loại sinh viên
	 * @param typeStudent
	 * @return
	 */
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
		System.out
				.println((Math.floor(teach.getPhong().getLatitude() * 1000000) / 1000000)
						+ "<="
						+ position.getLatitude()
						+ "<="
						+ (Math.ceil(teach.getPhong().getLatitude() * 1000000) / 1000000));
		System.out
				.println((Math
						.floor(teach.getPhong().getLongitude() * 10000000) / 10000000)
						+ "<="
						+ position.getLongitude()
						+ "<="
						+ (Math.ceil((teach.getPhong().getLongitude()) * 10000000) / 10000000));
		return Math.floor(teach.getPhong().getLatitude() * 1000000) / 1000000 <= position
				.getLatitude()
				&& position.getLatitude() <= (Math.ceil(teach.getPhong()
						.getLatitude() * 1000000) / 1000000)
				&& Math.floor(teach.getPhong().getLongitude() * 10000000) / 10000000 <= position
						.getLongitude()
				&& position.getLongitude() <= (Math.ceil((teach.getPhong()
						.getLongitude()) * 10000000) / 10000000);
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
		return (teach1.getTimeStart().compareTo(teach2.getTimeStart()) >= 0 && teach1.getTimeStart().compareTo(teach2.getTimeEnd()) <= 0)|| (teach1.getTimeEnd().compareTo(teach2.getTimeStart()) >= 0 && teach1.getTimeEnd().compareTo(teach2.getTimeEnd()) <= 0)|| (teach1.getTimeStart().compareTo(teach2.getTimeStart()) < 0 && teach1.getTimeEnd().compareTo(teach2.getTimeEnd()) > 0);
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

	/**
	 * Lấy danh sách tên nhân viên có ít nhât 1 lịch
	 * 
	 * @param listFreeTeach
	 * @param listFreeOnl
	 * @param listFreeSchel
	 * @return
	 */
	public static List<String> getUserDistinct(List<String> listFreeTeach,
			List<String> listFreeOnl, List<String> listFreeSchel) {
		List<String> listNoneFree = new ArrayList<>();
		if (listFreeTeach != null) {
			for (String string : listFreeTeach) {
				if (!checkExist(listNoneFree, string)) {
					listNoneFree.add(string);
				}
			}
		}
		if (listFreeOnl != null) {
			for (String string : listFreeOnl) {
				if (!checkExist(listNoneFree, string)) {
					listNoneFree.add(string);
				}
			}
		}
		if (listFreeSchel != null) {
			for (String string : listFreeSchel) {
				if (!checkExist(listNoneFree, string)) {
					listNoneFree.add(string);
				}
			}
		}
		return listNoneFree;
	}

	/**
	 * Kiểm tra 1 chuỗi đã tồn tại trong 1 danh sách chuỗi
	 * 
	 * @param listName
	 * @param name
	 * @return
	 */
	public static boolean checkExist(List<String> listName, String name) {
		for (String string : listName) {
			if (name.equals(string)) {
				return true;
			}
		}
		return false;
	}

	// public static void main(String[] args){
	// System.out.println(Common.checkExist(new ArrayList<String>(), null));
	// }

}
