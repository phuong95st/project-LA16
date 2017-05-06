/**
 * Copyright(C) 2017 Luvina software company
 * ValidateOnl.java, 03-05-2017 Tran
 */
package validate;

import java.util.ArrayList;
import java.util.List;

import utils.Common;
import utils.MessageProperties;
import dao.impl.OnlDaoImpl;
import entity.Onl;

/**
 * 
 * @author Tran
 *
 */
public class ValidateOnl {
	public List<String> validateOnl(Onl onl){
		List<String> listError = new ArrayList<>();
		if(onl.getTimeStart().compareTo(onl.getTimeEnd()) >= 0){
			listError.add(MessageProperties.getData("ERR22"));
		}
		// get listOnl
		List<Onl> listOnl = new OnlDaoImpl().getListOnl(onl.getUser().getUserId(),onl.getWeek().getWeekId(),onl.getId());
		// check data
		if(listOnl != null && Common.checkData(onl, listOnl)){
			listError.add(MessageProperties.getData("ERR24"));
		}
		return listError;
	}
	
	
}
