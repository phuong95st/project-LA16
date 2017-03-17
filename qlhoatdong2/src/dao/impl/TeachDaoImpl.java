/**
 * Copyright(C) 2017 Luvina software company
 * TeachDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import utils.Common;
import dao.TeachDao;
import entity.Position;
import entity.Teach;
import entity.User;
import entity.Week;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class TeachDaoImpl extends BaseDaoImpl implements TeachDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.TeachDao#getListTeach(java.sql.Timestamp, java.sql.Timestamp,
	 * int)
	 */
	@Override
	public List<Teach> getListTeach(Timestamp now, String hocKy, int userId) {

		List<Teach> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			try {
				if (hocKy == null) {
					// lấy ngày của tuần
					Calendar cal = Calendar.getInstance();
					cal.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
					cal.setTimeInMillis(now.getTime());
					int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);
					sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id WHERE date_of_week = ? AND w1.start_date <= ? AND w2.end_date >= ? AND user_id = ? ");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, dateOfWeek);
					ps.setTimestamp(2, now);
					ps.setTimestamp(3, now);
					ps.setInt(4, userId);
				}

				rs = ps.executeQuery();
				while (rs.next()) {
					Teach teach = new Teach();
					teach.setTeachId(rs.getInt("teach_id"));
					teach.setDateOfWeek(rs.getInt("date_of_week"));
					teach.setTimeStart(rs.getTime("time_start"));
					teach.setTimeEnd(rs.getTime("time_end"));
					
					Week w1 = new Week();
					w1.setWeekId(rs.getInt("w1.week_id"));
					w1.setStart(rs.getTimestamp("w1.start_date"));
					w1.setEnd(rs.getTimestamp("w1.end_date"));
					w1.setWeekCount(rs.getInt("w1.week_count"));
					teach.setWeekStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStart(rs.getTimestamp("w2.start_date"));
					w2.setEnd(rs.getTimestamp("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					teach.setWeekStart(w2);
					
					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					teach.setUser(user);
//					teach.setHol(rs.getBoolean("is_hol"));
					teach.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("latitude"), rs.getDouble("longitude")));
//					teach.setLate(rs.getBoolean("is_late"));
//					teach.setLateMin(rs.getInt("late_min"));
//					teach.setReason(rs.getString("reason"));
					teach.setHocKy(rs.getString("hoc_ky"));
					
					list.add(teach);
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
	 * @see dao.TeachDao#getTeachById(long)
	 */
	@Override
	public Teach getTeachById(long id) {
		Teach teach = null;
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id WHERE teach_id = ? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, id);

				rs = ps.executeQuery();
				if (rs.next()) {
					teach = new Teach();
					teach.setTeachId(rs.getInt("teach_id"));
					teach.setTimeStart(rs.getTime("time_start"));
					teach.setTimeEnd(rs.getTime("time_end"));
					
					Week w1 = new Week();
					w1.setWeekId(rs.getInt("w1.week_id"));
					w1.setStart(rs.getTimestamp("w1.start_date"));
					w1.setEnd(rs.getTimestamp("w1.end_date"));
					w1.setWeekCount(rs.getInt("w1.week_count"));
					teach.setWeekStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStart(rs.getTimestamp("w2.start_date"));
					w2.setEnd(rs.getTimestamp("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					teach.setWeekStart(w2);
					
					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					teach.setUser(user);
					teach.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("latitude"), rs.getDouble("longitude")));
					teach.setHocKy(rs.getString("hoc_ky"));
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return teach;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.TeachDao#update(entity.Teach)
	 */
	@Override
	public boolean update(Teach teach) {
//		if (connectToDB()) {
//			StringBuilder sql = new StringBuilder();
//			sql.append("UPDATE tbl_teach SET time_start=?, time_end=?, week_id=?, code_class=?, code_subject=?, name=?, user_id=?, is_hol=?, phong=?, is_late=?, late_min=?, reason=? WHERE teach_id=?");
//			try {
//				ps = conn.prepareStatement(sql.toString());
//				int i = 1;
//				ps.setTimestamp(i++, teach.getStart());
//				ps.setTimestamp(i++, teach.getEnd());
//				ps.setInt(i++, teach.getWeek().getWeekId());
//				ps.setString(i++, teach.getCodeClass());
//				ps.setString(i++, teach.getCodeSubject());
//				ps.setString(i++, teach.getName());
//				ps.setInt(i++, teach.getUser().getUserId());
//				ps.setBoolean(i++, teach.isHol());
//				ps.setString(i++, teach.getPhong().getKey());
//				ps.setBoolean(i++, teach.isLate());
//				ps.setInt(i++, teach.getLateMin());
//				ps.setString(i++, teach.getReason());
//				ps.setLong(i++, teach.getTeachId());
//
//				int result = ps.executeUpdate();
//				if (result != 0) {
//					return true;
//				}
//			} catch (SQLException e) {
//				System.out.println("Error: " + e.getMessage());
//			} finally {
//				close();
//			}
//		}
		return false;
	}
	
	public static void main(String[] args){
		System.out.println(new TeachDaoImpl().getListTeach(Common.getNow(), null, 1));
	}

}
