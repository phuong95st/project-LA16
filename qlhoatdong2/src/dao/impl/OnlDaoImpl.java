/**
 * Copyright(C) 2017 Luvina software company
 * OnlDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import utils.Constant;
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

	/**
	 * 
	 */
	public OnlDaoImpl() {
		super();
	}

	/**
	 * 
	 */
	public OnlDaoImpl(String str) {
		super();
		connectToDB();
	}

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

				} else {
					sql.append("UPDATE `tbl_onl` SET `time_start`=?,`time_end`=?,`ca_truc`=?,`date_of_week`=? WHERE `id` = ?");
					ps = conn.prepareStatement(sql.toString());
					int i = 1;
					ps.setTime(i++, onl.getTimeStart());
					ps.setTime(i++, onl.getTimeEnd());
					ps.setInt(i++, onl.getCaTruc());
					ps.setInt(i++, onl.getDateOfWeek());
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

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see dao.OnlDao#getListOnl(java.lang.String, java.lang.String,
//	 * java.lang.String, int)
//	 */
//	@SuppressWarnings("deprecation")
//	@Override
//	public List<Onl> getListOnl(String timeStart, String timeEnd, String date,
//			int userId) {
//		List<Onl> list = new ArrayList<>();
//		if (connectToDB()) {
//			StringBuilder sql = new StringBuilder();
//			try {
//				// lấy ngày của tuần
//				Calendar cal = new GregorianCalendar(
//						Common.getYearByString(date),
//						Common.getMonthByString(date) - 1,
//						Common.getDayOfMonthByString(date));
//				int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);
//				if ("".equals(timeStart)) {
//					sql.append("SELECT * FROM tbl_onl o INNER JOIN tbl_week w ON o.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE date_of_week = ? AND w.start_date <= ? AND w.end_date >= ? AND user_id = ?");
//					ps = conn.prepareStatement(sql.toString());
//
//					ps.setInt(1, dateOfWeek);
//					ps.setDate(2, new Date(cal.getTimeInMillis()));
//					ps.setDate(3, new Date(cal.getTimeInMillis()));
//					ps.setInt(4, userId);
//				} else {
//					// get Time
//					Time start = new Time(Common.getHourByString(timeStart),
//							Common.getMinByString(timeStart), 0);
//					Time end = new Time(Common.getHourByString(timeEnd),
//							Common.getMinByString(timeEnd), 0);
//					sql.append("SELECT * FROM tbl_onl o INNER JOIN tbl_week w ON o.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE date_of_week = ? AND w.start_date <= ? AND w.end_date >= ? AND user_id = ? AND ((o.time_start <= ? AND o.time_end>= ?) OR (o.time_start <= ? AND o.time_end>= ?))");
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
//				rs = ps.executeQuery();
//				while (rs.next()) {
//					Onl onl = new Onl();
//
//					onl.setId(rs.getLong("id"));
//					onl.setTimeStart(rs.getTime("time_start"));
//					onl.setTimeEnd(rs.getTime("time_end"));
//					onl.setCaTruc(rs.getInt("ca_truc"));
//					onl.setDateOfWeek(rs.getInt("date_of_week"));
//					onl.setHol(rs.getBoolean("is_hol"));
//					onl.setLate(rs.getBoolean("is_late"));
//					onl.setLateMin(rs.getInt("late_min"));
//					onl.setReason(rs.getString("reason"));
//
//					Week w = new Week();
//					w.setWeekId(rs.getInt("w.week_id"));
//					w.setWeekCount(rs.getInt("w.week_count"));
//					w.setStartDate(rs.getDate("w.start_date"));
//					w.setEndDate(rs.getDate("end_date"));
//					w.setHocKy(new HocKy(rs.getInt("h.hoc_ky_id"), rs
//							.getString("h.name"), rs.getString("h.nam_hoc")));
//					onl.setWeek(w);
//
//					User user = new User();
//					user.setUserId(rs.getInt("user_id"));
//					onl.setUser(user);
//
//					list.add(onl);
//				}
//			} catch (SQLException e) {
//				System.out.println("Error: " + e.getMessage());
//			} finally {
//				close();
//			}
//
//		}
//		return list;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.OnlDao#checkOnlByUserId(int)
	 */
	@Override
	public boolean checkOnlByUserId(int userId) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) FROM `tbl_onl` WHERE `user_id` = ?");
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, userId);
				rs = ps.executeQuery();
				if (rs.next()) {
					if (rs.getInt(1) != 0) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.OnlDao#getListOnl(java.util.Map)
	 */
	@Override
	public List<Onl> getListOnl(Map<String, Object> searchValue) {
		List<Onl> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_onl ");
			sql.append(Constant.TBL_ONL);
			sql.append(" INNER JOIN tbl_week ");
			sql.append(Constant.TBL_WEEK);
			sql.append(" ON ");
			sql.append(Constant.TBL_ONL);
			sql.append(".week_id = ");
			sql.append(Constant.TBL_WEEK);
			sql.append(".week_id INNER JOIN tbl_hocky ");
			sql.append(Constant.TBL_HOC_KY);
			sql.append(" ON ");
			sql.append(Constant.TBL_WEEK);
			sql.append(".hoc_ky_id = ");
			sql.append(Constant.TBL_HOC_KY);
			sql.append(".hoc_ky_id WHERE ");
			int i = 0;
			for (Entry<String, Object> item : searchValue.entrySet()) {
				if (i != 0) {
					sql.append(" AND ");
				}
				sql.append(item.getKey());
				sql.append(" = ?");
				i++;
			}
			System.out.println(sql.toString());
			try {
				ps = conn.prepareStatement(sql.toString());
				i = 1;
				for (Entry<String, Object> item : searchValue.entrySet()) {
					if (item.getValue().getClass() == Integer.class) {
						ps.setInt(i++, (int) item.getValue());
					} else if (item.getValue().getClass() == String.class) {
						ps.setString(i++, (String) item.getValue());
					}
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

					User user = new UserDaoImpl().getUserById(rs
							.getInt("user_id"));
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
	 * @see dao.OnlDao#getListOnl(int, int, long)
	 */
	@Override
	public List<Onl> getListOnl(int userId, int weekId, long onlId) {
		List<Onl> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			try {
				if (onlId == 0) {
					sql.append("SELECT * FROM tbl_onl o INNER JOIN tbl_week w ON o.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE `user_id` = ? AND w.week_id = ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, userId);
					ps.setInt(2, weekId);
				} else {
					sql.append("SELECT * FROM tbl_onl o INNER JOIN tbl_week w ON o.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE `user_id` = ? AND w.week_id = ? AND o.id != ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, userId);
					ps.setInt(2, weekId);
					ps.setLong(3, onlId);
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

					User user = new UserDaoImpl().getUserById(rs
							.getInt("user_id"));
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
	 * @see dao.OnlDao#insert(entity.Onl)
	 */
	@Override
	public boolean insert(Onl onl) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `tbl_onl`(`time_start`, `time_end`, `ca_truc`, `user_id`, `week_id`, `date_of_week`, `is_hol`, `is_late`, `late_min`, `reason`) VALUES (?,?,?,?,?,?,?,?,?,?)");
		try {
			ps = conn.prepareStatement(sql.toString());
			int i = 1;
			ps.setTime(i++, onl.getTimeStart());
			ps.setTime(i++, onl.getTimeEnd());
			ps.setInt(i++, onl.getCaTruc());
			ps.setInt(i++, onl.getUser().getUserId());
			ps.setInt(i++, onl.getWeek().getWeekId());
			ps.setInt(i++, onl.getDateOfWeek());
			ps.setBoolean(i++, onl.isHol());
			ps.setBoolean(i++, onl.isLate());
			ps.setInt(i++, onl.getLateMin());
			ps.setString(i++, onl.getReason());

			int result = ps.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.OnlDao#delete(long)
	 */
	@Override
	public boolean delete(long onlId) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM `tbl_onl` WHERE `id` = ?");
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, onlId);

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
	 * @see dao.OnlDao#getListOnlBySearch(java.util.Map)
	 */
	@Override
	public List<Onl> getListOnlBySearch(Map<String, Object> listValue) {
		List<Onl> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_onl o INNER JOIN tbl_week w ON o.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE ");
			try {
				int i = 0;
				boolean isTime = false;
				for (Entry<String, Object> item : listValue.entrySet()) {
					if (i != 0 && item.getValue().getClass() != Time.class) {
						sql.append(" AND ");
					}
					if (item.getValue().getClass() == Time.class) {
						isTime = true;
					} else if (item.getValue().getClass() == Date.class) {
						sql.append(" w.start_date <= ? AND w.end_date >= ? AND date_of_week = ? ");
					} else if (item.getValue().getClass() == Integer.class) {
						sql.append(item.getKey() + " = ?");
					}
					i++;
				}
				if (isTime) {
					sql.append(" AND ((o.time_start <= ? AND o.time_end>= ?) OR (o.time_start <= ? AND o.time_end>= ?) OR (o.time_start >= ? AND o.time_end <= ?)) ");
				}

				ps = conn.prepareStatement(sql.toString());

				Time timeStart = null;
				Time timeEnd = null;
				i = 1;
				for (Entry<String, Object> item : listValue.entrySet()) {
					if (item.getValue().getClass() == Time.class) {
						if (item.getKey().contains("time_start")) {
							timeStart = (Time) item.getValue();
						} else if (item.getKey().contains("time_end")) {
							timeEnd = (Time) item.getValue();
						}
					} else if (item.getValue().getClass() == Date.class) {
						Date date = (Date) item.getValue();
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(date.getTime());
						int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);

						ps.setDate(i++, (Date) item.getValue());
						ps.setDate(i++, (Date) item.getValue());
						ps.setInt(i++, dateOfWeek);
					} else if (item.getValue().getClass() == Integer.class) {
						ps.setInt(i++, (int) item.getValue());
					}
				}
				if (timeStart != null) {
					ps.setTime(i++, timeStart);
					ps.setTime(i++, timeStart);
					ps.setTime(i++, timeEnd);
					ps.setTime(i++, timeEnd);
					ps.setTime(i++, timeStart);
					ps.setTime(i++, timeEnd);
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

					User user = new UserDaoImpl().getUserById(rs.getInt("user_id"));
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

	/* (non-Javadoc)
	 * @see dao.OnlDao#getUserFreeOnl(java.sql.Time, java.sql.Time, int)
	 */
	@Override
	public List<String> getUserFreeOnl(Time timeStart, Time timeEnd, Date date) {
		List<String> listUser = new ArrayList<>();
		if (connectToDB()) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(date.getTime());
			int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			StringBuilder sql = new StringBuilder();	
			try {
				int i = 1;
					sql.append("SELECT DISTINCT u.name FROM `tbl_onl` o INNER JOIN tbl_week w ON o.week_id = w.week_id INNER JOIN tbl_user u ON o.user_id = u.user_id WHERE date_of_week = ? AND w.start_date <= ? AND w.end_date >= ? AND (o.time_start <= ? AND o.time_end>= ?) OR (o.time_start <= ? AND o.time_end>= ?) OR (o.time_start >= ? AND  o.time_end <= ?)");
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
