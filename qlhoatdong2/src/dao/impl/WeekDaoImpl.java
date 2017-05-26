/**
 * Copyright(C) 2017 Luvina software company
 * WeekDaoImpl.java, 01-04-2017 Tran
 */
package dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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

	/* (non-Javadoc)
	 * @see dao.WeekDao#getCurrentWeek(java.sql.Timestamp)
	 */
	@Override
	public Week getCurrentWeek(Timestamp now) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			Date date = new Date(now.getTime());
			sql.append("SELECT * FROM tbl_week w INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE start_date <= ? AND end_date >= ?");
			
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setDate(1, date);
				ps.setDate(2, date);
				
				rs = ps.executeQuery();
				if(rs.next()){
					Week week = new Week();
					
					week.setWeekId(rs.getInt("week_id"));
					week.setWeekCount(rs.getInt("week_count"));
					week.setStartDate(rs.getDate("start_date"));
					week.setEndDate(rs.getDate("end_date"));
					week.setHocKy(new HocKy(rs.getInt("h.hoc_ky_id"), rs.getString("h.name"), rs.getString("h.nam_hoc")));
					
					return week;
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.WeekDao#getWeekById(int)
	 */
	@Override
	public Week getWeekById(int weekId) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_week w INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE w.week_id = ?");
			
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, weekId);
				
				rs = ps.executeQuery();
				if(rs.next()){
					Week week = new Week();
					
					week.setWeekId(rs.getInt("week_id"));
					week.setWeekCount(rs.getInt("week_count"));
					week.setStartDate(rs.getDate("start_date"));
					week.setEndDate(rs.getDate("end_date"));
					week.setHocKy(new HocKy(rs.getInt("h.hoc_ky_id"), rs.getString("h.name"), rs.getString("h.nam_hoc")));
					
					return week;
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.WeekDao#addWeek(entity.Week)
	 */
	@Override
	public boolean addWeek(Week week) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `tbl_week`(`week_count`, `start_date`, `end_date`, `hoc_ky_id`) VALUES (?,?,?,?)");
		
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, week.getWeekCount());
			ps.setDate(2, week.getStartDate());
			ps.setDate(3, week.getEndDate());
			ps.setInt(4, week.getHocKy().getId());
			
			int result = ps.executeUpdate();
			if(result != 0){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}


}
