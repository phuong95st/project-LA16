/**
 * Copyright(C) 2017 Luvina software company
 * ValidateNamHoc.java, 05-05-2017 Tran
 */
package validate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import dao.impl.HocKyDaoImpl;
import utils.Common;
import utils.MessageProperties;

/**
 * 
 * @author Tran
 * 
 */
public class ValidateNamHoc {
	public List<String> checkData(String namHoc, String startDate,
			String total1, String total2, String total3) {
		List<String> listError = new ArrayList<>();
		Calendar inputDate = new GregorianCalendar(
				Common.getYearByString(startDate),
				Common.getMonthByString(startDate) - 1,
				Common.getDayOfMonthByString(startDate));
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(Common.getNow().getTime());

		if(!namHoc.matches("^[0-9]{4}-[0-9]{4}$")){
			listError.add(MessageProperties.getData("ERR29"));
		}else if(new HocKyDaoImpl().existNamHoc(namHoc)){
			listError.add(MessageProperties.getData("ERR30"));
		}
		if (inputDate.compareTo(now) < 0) {
			listError.add(MessageProperties.getData("ERR26"));
		}
		int count1 = 0, count2 = 0, count3 = 0;
		try {
			count1 = Integer.parseInt(total1);
			count2 = Integer.parseInt(total2);
			count3 = Integer.parseInt(total3);
			if (count1 <= 0 || count2 <= 0 || count3 <= 0) {
				listError.add(MessageProperties.getData("ERR28"));
			}
		} catch (NumberFormatException e) {
			listError.add(MessageProperties.getData("ERR27"));
		}
		return listError;
	}
}
