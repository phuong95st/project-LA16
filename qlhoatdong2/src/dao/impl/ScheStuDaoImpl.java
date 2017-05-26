/**
 * Copyright(C) 2017 Luvina software company
 * ScheStuDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import dao.ScheStuDao;
import entity.HocKy;
import entity.Position;
import entity.ScheStu;
import entity.User;
import entity.Week;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class ScheStuDaoImpl extends BaseDaoImpl implements ScheStuDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ScheStuDao#getListScheStu(java.sql.Timestamp,
	 * java.sql.Timestamp, int)
	 */
	@Override
	public List<ScheStu> getListScheStu(Timestamp now, int userId) {
		List<ScheStu> list = new ArrayList<>();
		if (connectToDB()) {
			// lấy ngày của tuần
			Calendar cal = Calendar.getInstance();
			cal.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
			cal.setTimeInMillis(now.getTime());
			int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_sche_stu ss INNER JOIN tbl_week w1 ON ss.week_start = w1.week_id INNER JOIN tbl_week w2 ON ss.week_end = w2.week_id INNER JOIN tbl_position p ON ss.phong = p.phong WHERE date_of_week = ? AND w1.start_date <= ? AND w2.end_date >= ? AND user_id = ? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, dateOfWeek);
				ps.setTimestamp(2, now);
				ps.setTimestamp(3, now);
				ps.setInt(4, userId);

				rs = ps.executeQuery();
				while (rs.next()) {
					ScheStu scheStu = new ScheStu();
					scheStu.setId(rs.getInt("id"));
					scheStu.setStart(rs.getTime("time_start"));
					scheStu.setEnd(rs.getTime("time_end"));
					scheStu.setType(rs.getInt("type"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					scheStu.setUser(user);

					scheStu.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("p.latitude"), rs.getDouble("longitude")));
					scheStu.setDateOfWeek(rs.getInt("date_of_week"));
					scheStu.setStudentName(rs.getString("student_name"));
					scheStu.setTopic(rs.getString("topic"));

					list.add(scheStu);
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			} finally {
				close();
			}

		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ScheStuDao#getListScheStu(java.util.Map, int)
	 */
	@Override
	public List<ScheStu> getListScheStu(Map<String, Object> listValue,int userId) {
		List<ScheStu> list = new ArrayList<>();
		if (connectToDB()) {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_sche_stu ss INNER JOIN tbl_week w1 ON ss.week_start = w1.week_id INNER JOIN tbl_week w2 ON ss.week_end = w2.week_id INNER JOIN tbl_position p ON ss.phong = p.phong INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id ");
			if (listValue.size() != 0) {
				sql.append("WHERE ");
				int i = 0;
				for (Entry<String, Object> value : listValue.entrySet()) {
					if (i != 0) {
						sql.append(" AND ");
					}
					if ("name".equals(value.getKey())) {
						sql.append("student_name LIKE ? ");
					} else{
						sql.append(value.getKey());
						sql.append(" = ? ");
					}
					i++;
				}
				sql.append("AND user_id = ?");
				System.out.println(sql.toString());
			}
			try {
				ps = conn.prepareStatement(sql.toString());

				if (listValue.size() != 0) {
					sql.append("WHERE ");
					int i = 1;
					for (Entry<String, Object> value : listValue.entrySet()) {
						if (i != 0) {
							sql.append(" AND ");
						}
						if ("name".equals(value.getKey())) {
							ps.setString(i, "%" + (String)value.getValue() + "%");
						} else if(value.getValue().getClass() == Integer.class){
							ps.setInt(i, (int)value.getValue());
						}else{
							ps.setString(i, (String)value.getValue());
						}
						i++;
					}
					ps.setInt(i, userId);

				}

				rs = ps.executeQuery();
				while (rs.next()) {
					ScheStu scheStu = new ScheStu();
					scheStu.setId(rs.getInt("id"));
					scheStu.setStart(rs.getTime("time_start"));
					scheStu.setEnd(rs.getTime("time_end"));
					scheStu.setType(rs.getInt("type"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					scheStu.setUser(user);

					scheStu.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("p.latitude"), rs.getDouble("longitude")));
					scheStu.setDateOfWeek(rs.getInt("date_of_week"));
					scheStu.setStudentName(rs.getString("student_name"));
					scheStu.setTopic(rs.getString("topic"));

					Week w1 = new Week();
					w1.setWeekId(rs.getInt("w1.week_id"));
					w1.setStartDate(rs.getDate("w1.start_date"));
					w1.setEndDate(rs.getDate("w1.end_date"));
					w1.setWeekCount(rs.getInt("w1.week_count"));
					w1.setHocKy(new HocKy(rs.getInt("h1.hoc_ky_id"), rs
							.getString("h1.name"), rs.getString("h1.nam_hoc")));
					scheStu.setwStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStartDate(rs.getDate("w2.start_date"));
					w2.setEndDate(rs.getDate("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					w2.setHocKy(new HocKy(rs.getInt("h2.hoc_ky_id"), rs
							.getString("h2.name"), rs.getString("h2.nam_hoc")));
					scheStu.setwEnd(w2);

					list.add(scheStu);
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
	 * @see dao.ScheStuDao#addScheStu(entity.ScheStu)
	 */
	@Override
	public boolean addScheStu(ScheStu scheStu) {
		if (connectToDB()) {
			try {
				conn.setAutoCommit(false);

				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO tbl_sche_stu(time_start, time_end, type, user_id, date_of_week, week_start, week_end, student_name, topic, phong) VALUES (?,?,?,?,?,?,?,?,?,?)");

				ps = conn.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setTime(i++, scheStu.getStart());
				ps.setTime(i++, scheStu.getEnd());
				ps.setInt(i++, scheStu.getType());
				ps.setInt(i++, scheStu.getUser().getUserId());
				ps.setInt(i++, scheStu.getDateOfWeek());
				ps.setInt(i++, scheStu.getwStart().getWeekId());
				ps.setInt(i++, scheStu.getwEnd().getWeekId());
				ps.setString(i++, scheStu.getStudentName());
				ps.setString(i++, scheStu.getTopic());
				ps.setString(i++, scheStu.getPhong().getKey());

				int result = ps.executeUpdate();
				if (result != 0) {
					rs = ps.getGeneratedKeys();
					if (rs.next()) {
						int generatedKey = rs.getInt(1);

						StringBuilder sql2 = new StringBuilder();
						sql2.append("INSERT INTO tbl_checkin_stu(sche_stu_id, week_id, status, content, other) VALUES (?,?,?,?,?)");
						for (int j = scheStu.getwStart().getWeekId(); j <= scheStu
								.getwEnd().getWeekId(); j++) {
							ps = conn.prepareStatement(sql2.toString());
							int k = 1;
							ps.setInt(k++, generatedKey);
							ps.setInt(k++, j);
							ps.setBoolean(k++, false);
							ps.setString(k++, "");
							ps.setString(k++, "");

							result = ps.executeUpdate();
							if (result == 0) {
								conn.rollback();
								return false;
							}
						}
						conn.setAutoCommit(true);
						return true;
					}
					conn.rollback();
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				close();
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ScheStuDao#getScheStuById(int)
	 */
	@Override
	public ScheStu getScheStuById(int id) {
		ScheStu scheStu = null;
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_sche_stu ss INNER JOIN tbl_week w1 ON ss.week_start = w1.week_id INNER JOIN tbl_week w2 ON ss.week_end = w2.week_id INNER JOIN tbl_position p ON ss.phong = p.phong INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE ss.id = ?");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, id);

				rs = ps.executeQuery();
				if (rs.next()) {
					scheStu = new ScheStu();

					scheStu.setId(rs.getInt("id"));
					scheStu.setStart(rs.getTime("time_start"));
					scheStu.setEnd(rs.getTime("time_end"));
					scheStu.setType(rs.getInt("type"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					scheStu.setUser(user);

					scheStu.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("p.latitude"), rs.getDouble("longitude")));
					scheStu.setDateOfWeek(rs.getInt("date_of_week"));
					scheStu.setStudentName(rs.getString("student_name"));
					scheStu.setTopic(rs.getString("topic"));

					Week w1 = new Week();
					w1.setWeekId(rs.getInt("w1.week_id"));
					w1.setStartDate(rs.getDate("w1.start_date"));
					w1.setEndDate(rs.getDate("w1.end_date"));
					w1.setWeekCount(rs.getInt("w1.week_count"));
					w1.setHocKy(new HocKy(rs.getInt("h1.hoc_ky_id"), rs
							.getString("h1.name"), rs.getString("h1.nam_hoc")));
					scheStu.setwStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStartDate(rs.getDate("w2.start_date"));
					w2.setEndDate(rs.getDate("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					w2.setHocKy(new HocKy(rs.getInt("h2.hoc_ky_id"), rs
							.getString("h2.name"), rs.getString("h2.nam_hoc")));
					scheStu.setwEnd(w2);

				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			} finally {
				close();
			}

		}
		return scheStu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ScheStuDao#updateScheStu(entity.ScheStu)
	 */
	@Override
	public boolean updateScheStu(ScheStu scheStu) {
		if (connectToDB()) {
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE tbl_sche_stu SET time_start =?, time_end=?, type=?, user_id=?, date_of_week=?, student_name=?, topic=?, phong=? WHERE id = ?");

				ps = conn.prepareStatement(sql.toString());
				int i = 1;
				ps.setTime(i++, scheStu.getStart());
				ps.setTime(i++, scheStu.getEnd());
				ps.setInt(i++, scheStu.getType());
				ps.setInt(i++, scheStu.getUser().getUserId());
				ps.setInt(i++, scheStu.getDateOfWeek());
				ps.setString(i++, scheStu.getStudentName());
				ps.setString(i++, scheStu.getTopic());
				ps.setString(i++, scheStu.getPhong().getKey());
				ps.setInt(i++, scheStu.getId());
				
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.ScheStuDao#delScheStu(entity.ScheStu)
	 */
	@Override
	public boolean delScheStu(ScheStu scheStu) {
		if (connectToDB()) {
			try {
				conn.setAutoCommit(false);

				StringBuilder sql = new StringBuilder();
				sql.append("DELETE FROM tbl_checkin_stu WHERE sche_stu_id = ? AND week_id = ?");
				
				for (int i = scheStu.getwStart().getWeekId(); i <= scheStu.getwEnd().getWeekId(); i++) {
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, scheStu.getId());
					ps.setInt(2, i);

					ps.executeUpdate();
				}
				StringBuilder sql2 = new StringBuilder();
				sql2.append("DELETE FROM tbl_sche_stu WHERE id = ?");
				
				ps = conn.prepareStatement(sql2.toString());
				ps.setInt(1, scheStu.getId());
	

				int result = ps.executeUpdate();
				if (result != 0) {
					conn.setAutoCommit(true);
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				close();
			}
		}
		return false;
	}

//	/* (non-Javadoc)
//	 * @see dao.ScheStuDao#getLisScheStu(java.lang.String, java.lang.String, java.lang.String, int)
//	 */
//	@SuppressWarnings("deprecation")
//	@Override
//	public List<ScheStu> getLisScheStu(String timeStart, String timeEnd,String date, int userId) {
//		List<ScheStu> list = new ArrayList<>();
//		if (connectToDB()) {
//			// lấy ngày của tuần
//			Calendar cal = new GregorianCalendar(Common.getYearByString(date), Common.getMonthByString(date) - 1, Common.getDayOfMonthByString(date));
//			int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);
//			StringBuilder sql = new StringBuilder();
//			try {
//				if("".equals(timeStart)){
//					sql.append("SELECT * FROM tbl_sche_stu ss INNER JOIN tbl_week w1 ON ss.week_start = w1.week_id INNER JOIN tbl_week w2 ON ss.week_end = w2.week_id INNER JOIN tbl_position p ON ss.phong = p.phong WHERE date_of_week = ? AND w1.start_date <= ? AND w2.end_date >= ? AND user_id = ? ");
//					ps = conn.prepareStatement(sql.toString());
//					ps.setInt(1, dateOfWeek);
//					ps.setDate(2, new Date(cal.getTimeInMillis()));
//					ps.setDate(3, new Date(cal.getTimeInMillis()));
//					ps.setInt(4, userId);
//				}else{
//					// get Time
//					Time start = new Time(Common.getHourByString(timeStart), Common.getMinByString(timeStart), 0);
//					Time end = new Time(Common.getHourByString(timeEnd),Common.getMinByString(timeEnd), 0);
//					sql.append("SELECT * FROM tbl_sche_stu ss INNER JOIN tbl_week w1 ON ss.week_start = w1.week_id INNER JOIN tbl_week w2 ON ss.week_end = w2.week_id INNER JOIN tbl_position p ON ss.phong = p.phong WHERE date_of_week = ? AND w1.start_date <= ? AND w2.end_date >= ? AND user_id = ? AND ((ss.time_start <= ? AND ss.time_end>= ?) OR (ss.time_start <= ? AND ss.time_end>= ?))");
//					ps = conn.prepareStatement(sql.toString());
//					int i = 1;
//					ps.setInt(i++, dateOfWeek);
//					ps.setDate(i++, new Date(cal.getTimeInMillis()));
//					ps.setDate(i++, new Date(cal.getTimeInMillis()));
//					ps.setInt(i++, userId);
//					ps.setTime(i++, start);
//					ps.setTime(i++, start);
//					ps.setTime(i++, end);
//					ps.setTime(i++, end);
//				}
//				
//
//				rs = ps.executeQuery();
//				while (rs.next()) {
//					ScheStu scheStu = new ScheStu();
//					scheStu.setId(rs.getInt("id"));
//					scheStu.setStart(rs.getTime("time_start"));
//					scheStu.setEnd(rs.getTime("time_end"));
//					scheStu.setType(rs.getInt("type"));
//					User user = new User();
//					user.setUserId(rs.getInt("user_id"));
//					scheStu.setUser(user);
//
//					scheStu.setPhong(new Position(rs.getString("p.phong"), rs
//							.getDouble("p.latitude"), rs.getDouble("longitude")));
//					scheStu.setDateOfWeek(rs.getInt("date_of_week"));
//					scheStu.setStudentName(rs.getString("student_name"));
//					scheStu.setTopic(rs.getString("topic"));
//
//					list.add(scheStu);
//				}
//			} catch (SQLException e) {
//				System.out.println("Error: " + e.getMessage());
//				e.printStackTrace();
//			} finally {
//				close();
//			}
//
//		}
//		return list;
//	}

	/* (non-Javadoc)
	 * @see dao.ScheStuDao#checkByUserId(int)
	 */
	@Override
	public boolean checkByUserId(int userId) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) FROM `tbl_sche_stu` WHERE `user_id` = ?");
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, userId);
				rs = ps.executeQuery();
				if(rs.next()){
					if(rs.getInt(1) != 0){
						return true;
					}
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
	 * @see dao.ScheStuDao#getListScheStu(java.util.Map)
	 */
	@Override
	public List<ScheStu> getListScheStu(Map<String, Object> listValue) {
		List<ScheStu> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_sche_stu ss INNER JOIN tbl_week w1 ON ss.week_start = w1.week_id INNER JOIN tbl_week w2 ON ss.week_end = w2.week_id INNER JOIN tbl_position p ON ss.phong = p.phong WHERE ");
			try {
				int i = 0;
				boolean isTime = false;
				for (Entry<String, Object> item : listValue.entrySet()) {
					if(i != 0 && item.getValue().getClass() != Time.class){
						sql.append(" AND ");
					}
					if(item.getValue().getClass() == Time.class){
						isTime = true;
					}else if(item.getValue().getClass() == Date.class){
						sql.append(" w1.start_date <= ? AND w2.end_date >= ? AND date_of_week = ? ");
					}else if(item.getValue().getClass() == Integer.class){
						sql.append(item.getKey() + " = ?");
					}
					i++;
				}
				if(isTime){
					sql.append(" AND ((ss.time_start <= ? AND ss.time_end>= ?) OR (ss.time_start <= ? AND ss.time_end>= ?) OR (ss.time_start >= ? AND  ss.time_end <= ?)) ");
				}
				
//				System.out.println(sql.toString());
				ps = conn.prepareStatement(sql.toString());
				
				Time timeStart = null;
				Time timeEnd = null;
				i = 1;
				for (Entry<String, Object> item : listValue.entrySet()) {
					if(item.getValue().getClass() == Time.class){
						if(item.getKey().contains("time_start")){
							timeStart = (Time)item.getValue();
						}else if(item.getKey().contains("time_end")){
							timeEnd = (Time)item.getValue();
						}
					}else if(item.getValue().getClass() == Date.class){
						Date date = (Date)item.getValue();
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(date.getTime());
						int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);

						ps.setDate(i++, (Date)item.getValue());
						ps.setDate(i++, (Date)item.getValue());
						ps.setInt(i++, dateOfWeek);
					}else if(item.getValue().getClass() == Integer.class){
						ps.setInt(i++, (int)item.getValue());
					}
				}
				if(timeStart != null){
					ps.setTime(i++, timeStart);
					ps.setTime(i++, timeStart);
					ps.setTime(i++, timeEnd);
					ps.setTime(i++, timeEnd);
					ps.setTime(i++, timeStart);
					ps.setTime(i++, timeEnd);
				}
				rs = ps.executeQuery();
				while (rs.next()) {
					ScheStu scheStu = new ScheStu();
					scheStu.setId(rs.getInt("id"));
					scheStu.setStart(rs.getTime("time_start"));
					scheStu.setEnd(rs.getTime("time_end"));
					scheStu.setType(rs.getInt("type"));
					
					User user = new UserDaoImpl().getUserById(rs.getInt("user_id"));
					scheStu.setUser(user);

					scheStu.setPhong(new Position(rs.getString("p.phong"), rs.getDouble("p.latitude"), rs.getDouble("longitude")));
					scheStu.setDateOfWeek(rs.getInt("date_of_week"));
					scheStu.setStudentName(rs.getString("student_name"));
					scheStu.setTopic(rs.getString("topic"));

					list.add(scheStu);
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
	 * @see dao.ScheStuDao#getUserFreeSchel(java.sql.Time, java.sql.Time, java.sql.Date)
	 */
	@Override
	public List<String> getUserFreeSchel(Time timeStart, Time timeEnd, Date date) {
		List<String> listUser = new ArrayList<>();
		if (connectToDB()) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(date.getTime());
			int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			StringBuilder sql = new StringBuilder();
			try {
				int i = 1;
					sql.append("SELECT DISTINCT u.name FROM `tbl_sche_stu` ss INNER JOIN tbl_week w1 ON ss.week_start = w1.week_id INNER JOIN tbl_week w2 ON ss.week_end = w2.week_id INNER JOIN tbl_user u ON ss.user_id = u.user_id WHERE date_of_week = ? AND w1.start_date <= ? AND w2.end_date >= ? AND (ss.time_start <= ? AND ss.time_end>= ?) OR (ss.time_start <= ? AND ss.time_end>= ?) OR (ss.time_start >= ? AND  ss.time_end <= ?)");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(i++, dateOfWeek);	
					ps.setDate(i++, date);
					ps.setDate(i++, date);
					ps.setTime(i++, timeStart);
					ps.setTime(i++, timeStart);
					ps.setTime(i++, timeEnd);
					ps.setTime(i++, timeEnd);
					ps.setTime(i++, timeStart);
					ps.setTime(i++, timeEnd);

				rs = ps.executeQuery();
				while(rs.next()){
					listUser.add(rs.getString(1));
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		
		return listUser;
	}
}
