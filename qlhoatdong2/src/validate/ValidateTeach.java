/**
 * Copyright(C) 2017 Luvina software company
 * ValidateTeach.java, 26-04-2017 Tran
 */
package validate;

import java.util.ArrayList;
import java.util.List;

import dao.impl.TeachDaoImpl;
import utils.Common;
import utils.MessageProperties;
import entity.Teach;

/**
 * 
 * @author Tran
 *
 */
public class ValidateTeach {
	public List<String> validateTeach(Teach teach){
		List<String> listError = new ArrayList<>();
		if(teach.getTimeStart().compareTo(teach.getTimeEnd()) >= 0){
			listError.add(MessageProperties.getData("ERR22"));
		}
		if(teach.getTeachId()==0 && teach.getWeekEnd().getWeekCount() <= teach.getWeekStart().getWeekCount()){
			listError.add(MessageProperties.getData("ERR23"));
		}
		// get ListTeach
		List<Teach> listTeach = new TeachDaoImpl().getListTeach(teach.getUser().getUserId(), teach.getTeachId());
		// check data
		if(listTeach != null && Common.checkData(teach, listTeach)){
			listError.add(MessageProperties.getData("ERR24"));
		}
		return listError;
	}
}
