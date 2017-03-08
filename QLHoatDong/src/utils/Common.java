/**
 * Copyright(C) 2017 Luvina software company
 * Common.java, Mar 2, 2017 nguyenhuuphuong
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import entity.Hol;

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

	/**
	 * Tự động chỉnh lại thời gian nghỉ của nhân viên
	 * 
	 * @param hol
	 *            lịch nghỉ
	 */
	public static void autoSetTimeHol(Hol hol) {
		Calendar cal = Calendar.getInstance();
		if (hol.getType() == 1) {
			cal.setTimeInMillis(hol.getStart().getTime());
			cal.set(Calendar.HOUR_OF_DAY, 8);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			hol.setStart(new Timestamp(cal.getTimeInMillis()));

			cal.setTimeInMillis(hol.getEnd().getTime());
			cal.set(Calendar.HOUR_OF_DAY, 12);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			hol.setEnd(new Timestamp(cal.getTimeInMillis()));
		} else if (hol.getType() == 2) {
			cal.setTimeInMillis(hol.getStart().getTime());
			cal.set(Calendar.HOUR_OF_DAY, 13);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			hol.setStart(new Timestamp(cal.getTimeInMillis()));

			cal.setTimeInMillis(hol.getEnd().getTime());
			cal.set(Calendar.HOUR_OF_DAY, 17);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			hol.setEnd(new Timestamp(cal.getTimeInMillis()));
		} else {
			cal.setTimeInMillis(hol.getStart().getTime());
			cal.set(Calendar.HOUR_OF_DAY, 8);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			hol.setStart(new Timestamp(cal.getTimeInMillis()));

			cal.setTimeInMillis(hol.getEnd().getTime());
			cal.set(Calendar.HOUR_OF_DAY, 17);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			hol.setEnd(new Timestamp(cal.getTimeInMillis()));
		}
	}

	public static void main(String[] args) {
		System.out.println(new GregorianCalendar(2017, 2, 8)
				.compareTo(new GregorianCalendar(2017, 2, 8))>0);

	}
}
