/**
 * Copyright(C) 2017 Luvina software company
 * TeachDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import utils.Common;
import dao.TeachDao;
import entity.HocKy;
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
					sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE date_of_week = ? AND w1.start_date <= ? AND w2.end_date >= ? AND user_id = ? ");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, dateOfWeek);
					ps.setDate(2, new Date(now.getTime()));
					ps.setDate(3, new Date(now.getTime()));
					ps.setInt(4, userId);
				}else{
					sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE h2.name = ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setString(1, hocKy);
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
					w1.setStartDate(rs.getDate("w1.start_date"));
					w1.setEndDate(rs.getDate("w1.end_date"));
					w1.setWeekCount(rs.getInt("w1.week_count"));
					w1.setHocKy(new HocKy(rs.getInt("h1.hoc_ky_id"), rs.getString("h1.name"), rs.getString("h1.nam_hoc")));
					teach.setWeekStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStartDate(rs.getDate("w2.start_date"));
					w2.setEndDate(rs.getDate("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					w2.setHocKy(new HocKy(rs.getInt("h2.hoc_ky_id"), rs.getString("h2.name"), rs.getString("h2.nam_hoc")));
					teach.setWeekEnd(w2);
					
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
			sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE teach_id = ? ");

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
					w1.setStartDate(rs.getDate("w1.start_date"));
					w1.setEndDate(rs.getDate("w1.end_date"));
					w1.setWeekCount(rs.getInt("w1.week_count"));
					w1.setHocKy(new HocKy(rs.getInt("h1.hoc_ky_id"), rs.getString("h1.name"), rs.getString("h1.nam_hoc")));
					teach.setWeekStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStartDate(rs.getDate("w2.start_date"));
					w2.setEndDate(rs.getDate("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					w2.setHocKy(new HocKy(rs.getInt("h2.hoc_ky_id"), rs.getString("h2.name"), rs.getString("h2.nam_hoc")));
					teach.setWeekEnd(w2);
					
					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					teach.setUser(user);
					teach.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("latitude"), rs.getDouble("longitude")));
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

	/* (non-Javadoc)
	 * @see dao.TeachDao#getListTeach(java.sql.Time, java.sql.Time, java.sql.Date)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<Teach> getListTeach(String timeStart, String timeEnd, String date, int userId) {
		List<Teach> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			try {
				// lấy ngày của tuần
				Calendar cal = new GregorianCalendar(Common.getYearByString(date), Common.getMonthByString(date) - 1, Common.getDayOfMonthByString(date));
				int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				if ("".equals(timeStart)) {
					sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE date_of_week = ? AND w1.start_date <= ? AND w2.end_date >= ? AND user_id = ? ");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, dateOfWeek);
					ps.setDate(2, new Date(cal.getTimeInMillis()));
					ps.setDate(3, new Date(cal.getTimeInMillis()));
					ps.setInt(4, userId);
				}else{
					// get Time
					Time start = new Time(Common.getHourByString(timeStart), Common.getMinByString(timeStart), 0);
					Time end = new Time(Common.getHourByString(timeEnd),Common.getMinByString(timeEnd), 0);
					sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE date_of_week = ? AND w1.start_date <= ? AND w2.end_date >= ? AND user_id = ? AND ((t.time_start <= ? AND t.time_end>= ?) OR (t.time_start <= ? AND t.time_end>= ?))");
					ps = conn.prepareStatement(sql.toString());
					int i = 1;
					ps.setInt(i++, dateOfWeek);
					ps.setDate(i++, new Date(cal.getTimeInMillis()));
					ps.setDate(i++, new Date(cal.getTimeInMillis()));
					ps.setInt(i++, userId);
					ps.setTime(i++, start);
					ps.setTime(i++, start);
					ps.setTime(i++, end);
					ps.setTime(i++, end);
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
					w1.setStartDate(rs.getDate("w1.start_date"));
					w1.setEndDate(rs.getDate("w1.end_date"));
					w1.setWeekCount(rs.getInt("w1.week_count"));
					w1.setHocKy(new HocKy(rs.getInt("h1.hoc_ky_id"), rs.getString("h1.name"), rs.getString("h1.nam_hoc")));
					teach.setWeekStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStartDate(rs.getDate("w2.start_date"));
					w2.setEndDate(rs.getDate("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					w2.setHocKy(new HocKy(rs.getInt("h2.hoc_ky_id"), rs.getString("h2.name"), rs.getString("h2.nam_hoc")));
					teach.setWeekEnd(w2);
					
					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					teach.setUser(user);

					teach.setPhong(new Position(rs.getString("p.phong"), rs.getDouble("latitude"), rs.getDouble("longitude")));
					
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
	
	public static void main(String[] args){
		System.out.println(new TeachDaoImpl().getListTeach("9:39", "15:00", "23/03/2017", 1));
	}

}
