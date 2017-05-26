/**
 * Copyright(C) 2017 Luvina software company
 * OnlLogic.java, 03-05-2017 Tran
 */
package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.OnlDao;
import dao.impl.BaseDaoImpl;
import dao.impl.OnlDaoImpl;
import entity.Onl;

/**
 * 
 * @author Tran
 * 
 */
public class OnlLogic {
	public boolean insertOnl(List<Onl> listOnl) {
		// tạo connection
		OnlDao onlDao = new OnlDaoImpl("");
		// get connection
		Connection conn = BaseDaoImpl.getConn();

		try {
			// set autocommit
			conn.setAutoCommit(false);
			for (Onl onl : listOnl) {
				if(!onlDao.insert(onl)){
					conn.rollback();
					return false;
				}
			}
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return true;
	}
}
