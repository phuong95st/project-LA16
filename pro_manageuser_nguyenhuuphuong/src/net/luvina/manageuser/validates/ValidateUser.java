/**
 * Copyright(C) 2016 Luvina Software Company
 * ValidateUser.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.validates;

import java.util.ArrayList;
import java.util.List;

import net.luvina.manageuser.utils.ConfigureProperties;
import net.luvina.manageuser.utils.MessageErrorProperties;

/**
 * Thực hiện validate data login
 *
 * @author PhuongNH
 *
 */
public class ValidateUser {
	/**
	 * validateLogin
	 *
	 * @param loginId
	 *            String loginId
	 * @param password
	 *            String password
	 * @return list errorMess
	 */
	public static List<String> validateLogin(String loginId, String password) {
		List<String> lsErrorMess = new ArrayList<String>();
		if (loginId.trim().length() == 0 || password.length() == 0) {
			lsErrorMess.add(MessageErrorProperties.getMessage("ER001"));
		}
		String user = ConfigureProperties.getData("user");
		String pass = ConfigureProperties.getData("password");
		if (loginId.trim().length() != 0 && password.length() != 0) {
			if (!user.equals(loginId) || !pass.equals(password)) {
				lsErrorMess.add(MessageErrorProperties.getMessage("ER016"));
			}
		}
		return lsErrorMess;
	}
}
