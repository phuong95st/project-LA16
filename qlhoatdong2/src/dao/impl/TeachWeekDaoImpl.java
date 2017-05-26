/**
 * Copyright(C) 2017 Luvina software company
 * TeachWeekDaoImpl.java, Mar 17, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import dao.TeachWeekDao;
import entity.HocKy;
import entity.Teach;
import entity.TeachWeek;
import entity.Week;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class TeachWeekDaoImpl extends BaseDaoImpl implements TeachWeekDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.TeachWeekDao#getTeachDaoByTeachId(long)
	 */
	@Override
	public TeachWeek getTeachDaoByTeachId(Teach teach, Timestamp now) {
		TeachWeek teachWeek = null;
		if (connectToDB()) {
			Date date = new Date(now.getTime());
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM tbl_teach_week tw INNER JOIN tbl_week w ON tw.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE w.start_date <= ? AND w.end_date >= ? AND teach_id = ?");
				ps = conn.prepareStatement(sql.toString());
				ps.setDate(1, date);
				ps.setDate(2, date);
				ps.setLong(3, teach.getTeachId());

				rs = ps.executeQuery();
				if (rs.next()) {
					teachWeek = new TeachWeek();

					teachWeek.setId(rs.getLong("tw.id"));
					teachWeek.setTeach(teach);
					Week week = new Week();
					week.setWeekId(rs.getInt("w.week_id"));
					week.setWeekCount(rs.getInt("w.week_count"));
					week.setStartDate(rs.getDate("w.start_date"));
					week.setEndDate(rs.getDate("w.end_date"));
					week.setHocKy(new HocKy(rs.getInt("h.hoc_ky_id"),rs.getString("h.name"),rs.getString("h.nam_hoc")));
					teachWeek.setWeek(week);
					teachWeek.setHol(rs.getBoolean("is_hol"));
					teachWeek.setLate(rs.getBoolean("is_late"));
					teachWeek.setLateMin(rs.getInt("late_min"));
					teachWeek.setReason(rs.getString("reason"));
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return teachWeek;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.TeachWeekDao#update(entity.TeachWeek)
	 */
	@Override
	public boolean update(TeachWeek teachWeek) {
		if (connectToDB()) {
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE tbl_teach_week SET is_late = ?, is_hol=?, late_min=?, reason=? WHERE id = ?");
				ps = conn.prepareStatement(sql.toString());
				ps.setBoolean(1, teachWeek.isLate());
				ps.setBoolean(2, teachWeek.isHol());
				ps.setInt(3, teachWeek.getLateMin());
				ps.setString(4, teachWeek.getReason());
				ps.setLong(5, teachWeek.getId());

				int result = ps.executeUpdate();
				if (result != 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.TeachWeekDao#setReason(java.lang.String, long)
	 */
	@Override
	public boolean setReason(String reason, long id) {
		if (connectToDB()) {
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE tbl_teach_week SET reason=? WHERE id = ?");
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, reason);
				ps.setLong(2, id);

				int result = ps.executeUpdate();
				if (result != 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.TeachWeekDao#getTeachDaoByTeachId(entity.Teach, int)
	 */
	@Override
	public TeachWeek getTeachDaoByTeachId(Teach teach, int weekId) {
		TeachWeek teachWeek = null;
		if (connectToDB()) {
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM tbl_teach_week tw INNER JOIN tbl_week w ON tw.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE tw.teach_id = ? AND tw.week_id = ?");
				ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, teach.getTeachId());
				ps.setInt(2, weekId);

				rs = ps.executeQuery();
				if (rs.next()) {
					teachWeek = new TeachWeek();

					teachWeek.setId(rs.getLong("tw.id"));
					teachWeek.setTeach(teach);
					Week week = new Week();
					week.setWeekId(rs.getInt("w.week_id"));
					week.setWeekCount(rs.getInt("w.week_count"));
					week.setStartDate(rs.getDate("w.start_date"));
					week.setEndDate(rs.getDate("w.end_date"));
					week.setHocKy(new HocKy(rs.getInt("h.hoc_ky_id"),rs.getString("h.name"),rs.getString("h.nam_hoc")));
					teachWeek.setWeek(week);
					teachWeek.setHol(rs.getBoolean("is_hol"));
					teachWeek.setLate(rs.getBoolean("is_late"));
					teachWeek.setLateMin(rs.getInt("late_min"));
					teachWeek.setReason(rs.getString("reason"));
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return teachWeek;
	}
	
	public static void main(String[] args){
		Teach teach = new Teach();
		teach.setTeachId(1);
		System.out.println(new TeachWeekDaoImpl().getTeachDaoByTeachId(teach, 34));
	}

	/*
	 * (non-Javadoc)
	 * @see dao.TeachWeekDao#insertTeachWeek(long, int)
	 */
	@Override
	public boolean insertTeachWeek(long teachId, int weekId) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `tbl_teach_week`(`teach_id`, `week_id`, `is_late`, `is_hol`, `late_min`, `reason`) VALUES (?,?,?,?,?,?)");
		try {
			ps = conn.prepareStatement(sql.toString());
			int i = 1;
			ps.setLong(i++, teachId);
			ps.setInt(i++, weekId);
			ps.setBoolean(i++, false);
			ps.setBoolean(i++, true);
			ps.setInt(i++, 0);
			ps.setString(i++, "");
			
			int result = ps.executeUpdate();
			if(result != 0){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.TeachWeekDao#deleteTeachWeek(long, int)
	 */
	@Override
	public boolean deleteTeachWeek(long teachId, int weekId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM `tbl_teach_week` WHERE `teach_id` = ? AND `week_id` = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, teachId);
			ps.setInt(2, weekId);
			
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
