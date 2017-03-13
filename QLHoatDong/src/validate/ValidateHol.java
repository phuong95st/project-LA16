/**
 * Copyright(C) 2017 Luvina software company
 * ValidateHol.java, Mar 13, 2017 nguyenhuuphuong
 */
package validate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import utils.MessageProperties;
import entity.Hol;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class ValidateHol {
	/**
	 * 
	 * @param hol
	 * @param map
	 * @param hols
	 * @return
	 */
	public List<String> validateHol(Hol hol, Map<Long, Hol> map, List<Hol> hols) {
		List<String> list = new ArrayList<String>();

		Calendar cal = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Ho_Chi_Minh"));
		Calendar now = new GregorianCalendar(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		if (hol.getStart().compareTo(new Timestamp(now.getTimeInMillis())) <= 0
				|| (hol.getStart().compareTo(hol.getEnd()) > 0)) {
			list.add(MessageProperties.getData("ERR07"));
		}

		if (map != null) {
			for (Entry<Long, Hol> item : map.entrySet()) {
				Hol hol2 = item.getValue();
				Calendar cal2 = Calendar.getInstance(TimeZone
						.getTimeZone("Asia/Ho_Chi_Minh"));
				cal2.setTimeInMillis(hol2.getStart().getTime());
				Timestamp start = new Timestamp(new GregorianCalendar(
						cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH),
						cal2.get(Calendar.DAY_OF_MONTH)).getTimeInMillis());
				cal2.setTimeInMillis(hol2.getEnd().getTime());
				Timestamp end = new Timestamp(new GregorianCalendar(
						cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH),
						cal2.get(Calendar.DAY_OF_MONTH)).getTimeInMillis());
				// kiểm tra ngày nhập vào đã đăng ký hay chưa
				if (hol.getStart().compareTo(start) >= 0
						&& hol.getStart().compareTo(end) <= 0) {
					list.add(MessageProperties.getData("ERR08"));
					break;
				}
			}
		}
		
		if (hols != null) {
			for (Hol hol2 : hols) {
				Calendar cal2 = Calendar.getInstance(TimeZone
						.getTimeZone("Asia/Ho_Chi_Minh"));
				cal2.setTimeInMillis(hol2.getStart().getTime());
				Timestamp start = new Timestamp(new GregorianCalendar(
						cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH),
						cal2.get(Calendar.DAY_OF_MONTH)).getTimeInMillis());
				cal2.setTimeInMillis(hol2.getEnd().getTime());
				Timestamp end = new Timestamp(new GregorianCalendar(
						cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH),
						cal2.get(Calendar.DAY_OF_MONTH)).getTimeInMillis());
				// kiểm tra ngày nhập vào đã đăng ký hay chưa
				if (hol.getStart().compareTo(start) >= 0
						&& hol.getStart().compareTo(end) <= 0) {
					list.add(MessageProperties.getData("ERR08"));
					break;
				}
			}
		}
		
		// lấy danh sách những ngày chưa nghỉ
		
		return list;
	}

}
