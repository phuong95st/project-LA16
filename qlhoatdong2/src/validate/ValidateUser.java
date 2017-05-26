/**
 * Copyright(C) 2017 Luvina software company
 * ValidateUser.java, Mar 2, 2017 nguyenhuuphuong
 */
package validate;

import java.util.ArrayList;
import java.util.List;

import dao.impl.UserDaoImpl;
import utils.Common;
import utils.MessageProperties;


/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class ValidateUser {
	/**
	 * Validate login
	 * 
	 * @param email
	 *            email
	 * @param pass
	 *            password
	 * @return List các câu thông báo lỗi nếu có lỗi
	 */
	public List<String> validateLogin(String email, String pass) {
		List<String> listError = new ArrayList<String>();
		if ("".equals(email)) {
			listError.add(MessageProperties.getData("ERR01"));
		}
		if ("".equals(pass)) {
			listError.add(MessageProperties.getData("ERR02"));
		}

		if (!"".equals(email) && !"".equals(pass)) {
			if (!new UserDaoImpl().checkLogin(email, Common.encodeMD5(pass))) {
				listError.add(MessageProperties.getData("ERR03"));
			}
		}

		return listError;
	}
	
	public List<String> validateUser(String email, String sdt, String cmt, int userId){
		List<String> listError = new ArrayList<>();
		if(!Common.isCMT(cmt)){
			listError.add(MessageProperties.getData("ERR18"));
		}
		if(!Common.isEmail(email)){
			listError.add(MessageProperties.getData("ERR15"));
		}
		if(new UserDaoImpl().isExistEmail(email, userId)){
			listError.add(MessageProperties.getData("ERR17"));
		}
		if(!Common.isPhoneNumber(sdt)){
			listError.add(MessageProperties.getData("ERR16"));
		}
		return listError;
	}

}
