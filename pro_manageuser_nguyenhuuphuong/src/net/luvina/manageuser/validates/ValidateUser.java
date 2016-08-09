/**
 * Copyright(C) 2016 Luvina Software Company
 * ValidateUser.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.validates;

import java.util.ArrayList;
import java.util.List;

import net.luvina.manageuser.logics.impl.UserLogicImpl;
import net.luvina.manageuser.utils.ConfigureProperties;
import net.luvina.manageuser.utils.MessageErrorJaProperties;
import net.luvina.manageuser.utils.MessageJaProperties;
import net.luvina.manageuser.utils.MessageProperties;

/**
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
			lsErrorMess.add(MessageErrorJaProperties.getMessage("ER001"));
		}
		UserLogicImpl userLogic = new UserLogicImpl();
		if (loginId.trim().length() != 0 && password.length() != 0) {
			// Start fix bug ID 3 – PhuongNH 2016/08/04
			if (!userLogic.existLoginId(loginId, password)) {
				// End fix bug ID 3 – PhuongNH 2016/08/04
				lsErrorMess.add(MessageErrorJaProperties.getMessage("ER016"));
			}
		}
		return lsErrorMess;
	}
}
