/**
 * Copyright(C) 2017 Luvina software company
 * WeekDaoImpl.java, 01-04-2017 Tran
 */
package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.WeekDao;
import entity.HocKy;
import entity.Week;

/**
 * 
 * @author Tran
 *
 */
public class WeekDaoImpl extends BaseDaoImpl implements WeekDao {

	/* (non-Javadoc)
	 * @see dao.WeekDao#getListWeek()
	 */
	@Override
	public List<Week> getListWeek(String hocKy) {
		List<Week> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_week w INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE h.name = ?");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, hocKy);
				rs = ps.executeQuery();
				while (rs.next()) {
					Week week = new Week();
					week.setWeekId(rs.getInt("week_id"));
					week.setWeekCount(rs.getInt("week_count"));
					week.setStartDate(rs.getDate("start_date"));
					week.setEndDate(rs.getDate("end_date"));
					week.setHocKy(new HocKy(rs.getInt("h.hoc_ky_id"), rs.getString("h.name"), rs.getString("h.nam_hoc")));
					
					list.add(week);
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return list;
	}

}
