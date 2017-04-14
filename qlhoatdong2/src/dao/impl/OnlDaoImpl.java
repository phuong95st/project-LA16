/**
 * Copyright(C) 2017 Luvina software company
 * OnlDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import dao.OnlDao;
import entity.HocKy;
import entity.Onl;
import entity.User;
import entity.Week;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class OnlDaoImpl extends BaseDaoImpl implements OnlDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.OnlDao#getListOnl(java.sql.Timestamp, int, int)
	 */
	@Override
	public List<Onl> getListOnl(Timestamp now, int weekId, int userId) {
		List<Onl> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			try {
				if (now != null) {
					// lấy ngày của tuần
					Calendar cal = Calendar.getInstance();
					cal.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
					cal.setTimeInMillis(now.getTime());
					int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);

					sql.append("SELECT * FROM tbl_onl o INNER JOIN tbl_week w ON o.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE date_of_week = ? AND w.start_date <= ? AND w.end_date >= ? AND user_id = ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, dateOfWeek);
					ps.setDate(2, new Date(cal.getTimeInMillis()));
					ps.setDate(3, new Date(cal.getTimeInMillis()));
					ps.setInt(4, userId);
				} else {
					sql.append("SELECT * FROM tbl_onl o INNER JOIN tbl_week w ON o.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE o.week_id = ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, weekId);
				}
				rs = ps.executeQuery();
				while (rs.next()) {
					Onl onl = new Onl();

					onl.setId(rs.getLong("id"));
					onl.setTimeStart(rs.getTime("time_start"));
					onl.setTimeEnd(rs.getTime("time_end"));
					onl.setCaTruc(rs.getInt("ca_truc"));
					onl.setDateOfWeek(rs.getInt("date_of_week"));
					onl.setHol(rs.getBoolean("is_hol"));
					onl.setLate(rs.getBoolean("is_late"));
					onl.setLateMin(rs.getInt("late_min"));
					onl.setReason(rs.getString("reason"));

					Week w = new Week();
					w.setWeekId(rs.getInt("w.week_id"));
					w.setWeekCount(rs.getInt("w.week_count"));
					w.setStartDate(rs.getDate("w.start_date"));
					w.setEndDate(rs.getDate("end_date"));
					w.setHocKy(new HocKy(rs.getInt("h.hoc_ky_id"), rs
							.getString("h.name"), rs.getString("h.nam_hoc")));
					onl.setWeek(w);

					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					onl.setUser(user);

					list.add(onl);
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.OnlDao#getOnlById(long)
	 */
	@Override
	public Onl getOnlById(long id) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_onl o INNER JOIN tbl_week w ON o.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE o.id = ?");
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, id);

				rs = ps.executeQuery();
				if (rs.next()) {
					Onl onl = new Onl();

					onl.setId(rs.getLong("id"));
					onl.setTimeStart(rs.getTime("time_start"));
					onl.setTimeEnd(rs.getTime("time_end"));
					onl.setCaTruc(rs.getInt("ca_truc"));
					onl.setDateOfWeek(rs.getInt("date_of_week"));
					onl.setHol(rs.getBoolean("is_hol"));
					onl.setLate(rs.getBoolean("is_late"));
					onl.setLateMin(rs.getInt("late_min"));
					onl.setReason(rs.getString("reason"));

					Week w = new Week();
					w.setWeekId(rs.getInt("w.week_id"));
					w.setWeekCount(rs.getInt("w.week_count"));
					w.setStartDate(rs.getDate("w.start_date"));
					w.setEndDate(rs.getDate("end_date"));
					w.setHocKy(new HocKy(rs.getInt("h.hoc_ky_id"), rs
							.getString("h.name"), rs.getString("h.nam_hoc")));
					onl.setWeek(w);

					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					onl.setUser(user);

					return onl;
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.OnlDao#update(entity.Onl, boolean)
	 */
	@Override
	public boolean update(Onl onl, boolean isUpdateStatus) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			try {
				if (isUpdateStatus) {
					sql.append("UPDATE `tbl_onl` SET `is_hol`= ?,`is_late`=?,`late_min`=?,`reason`=? WHERE `id` = ?");
					ps = conn.prepareStatement(sql.toString());
					int i = 1;
					ps.setBoolean(i++, onl.isHol());
					ps.setBoolean(i++, onl.isLate());
					ps.setInt(i++, onl.getLateMin());
					ps.setString(i++, onl.getReason());
					ps.setLong(i++, onl.getId());

				}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.OnlDao#setReason(java.lang.String, long)
	 */
	@Override
	public boolean setReason(String reason, long id) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			try {
				sql.append("UPDATE `tbl_onl` SET `reason`=? WHERE `id` = ?");
				ps = conn.prepareStatement(sql.toString());
				int i = 1;
				ps.setString(i++, reason);
				ps.setLong(i++, id);

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
}
