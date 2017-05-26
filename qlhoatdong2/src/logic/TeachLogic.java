/**
 * Copyright(C) 2017 Luvina software company
 * TeachLogic.java, 28-04-2017 Tran
 */
package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.TeachDao;
import dao.TeachWeekDao;
import dao.impl.BaseDaoImpl;
import dao.impl.TeachDaoImpl;
import dao.impl.TeachWeekDaoImpl;
import entity.Teach;

/**
 * 
 * @author Tran
 * 
 */
public class TeachLogic {
	public boolean insertTeach(List<Teach> listTeach) {
		// tạo connection
		TeachDao teachDao = new TeachDaoImpl("");
		// get connection
		Connection conn = BaseDaoImpl.getConn();

		try {
			// set autocommit
			conn.setAutoCommit(false);
			// lặp insert
			for (Teach teach : listTeach) {
				// insert teach
				long generateKey = teachDao.addTeach(teach);
				if (generateKey == 0) {
					conn.rollback();
					return false;
				}
				// insert info teach
				TeachWeekDao teDao = new TeachWeekDaoImpl();
				for (int i = teach.getWeekStart().getWeekId(); i <= teach
						.getWeekEnd().getWeekId(); i++) {
					if (!teDao.insertTeachWeek(generateKey, i)) {
						conn.rollback();
						return false;
					}
				}
			}
			// set autocommit true
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return true;
	}

	public boolean removeTeach(Teach teach) {
		// tạo connection
		TeachDao teachDao = new TeachDaoImpl("");
		// get connection
		Connection conn = BaseDaoImpl.getConn();

		try {
			// set autocommit
			conn.setAutoCommit(false);
			// delete teachweek
			TeachWeekDao teDao = new TeachWeekDaoImpl();
			for (int i = teach.getWeekStart().getWeekId(); i <= teach.getWeekEnd().getWeekId(); i++) {
				if (!teDao.deleteTeachWeek(teach.getTeachId(), i)) {
					conn.rollback();
					return false;
				}
			}
			// delete teach
			if (!teachDao.deleteTeach(teach.getTeachId())) {
				conn.rollback();
				return false;
			}
			// set autocommit true
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
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
