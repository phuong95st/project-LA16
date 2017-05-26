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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import com.mysql.jdbc.Statement;

import dao.TeachDao;
import entity.HocKy;
import entity.Position;
import entity.Teach;
import entity.TeachWeek;
import entity.User;
import entity.Week;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class TeachDaoImpl extends BaseDaoImpl implements TeachDao {

	/**
	 * 
	 */
	public TeachDaoImpl() {
		super();
	}

	/**
	 * 
	 */
	public TeachDaoImpl(String message) {
		super();
		connectToDB();
	}

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
				if (now != null && hocKy == null && userId != 0) {
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
				} else if (hocKy != null && userId != 0) {
					sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE h2.name = ? AND user_id = ? ");
					ps = conn.prepareStatement(sql.toString());
					ps.setString(1, hocKy);
					ps.setInt(2, userId);
				} else if (hocKy != null && userId == 0) {
					sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE h2.name = ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setString(1, hocKy);
				} else {
					sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE user_id = ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, userId);
				}

				System.out.println(sql.toString());
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
					w1.setHocKy(new HocKy(rs.getInt("h1.hoc_ky_id"), rs
							.getString("h1.name"), rs.getString("h1.nam_hoc")));
					teach.setWeekStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStartDate(rs.getDate("w2.start_date"));
					w2.setEndDate(rs.getDate("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					w2.setHocKy(new HocKy(rs.getInt("h2.hoc_ky_id"), rs
							.getString("h2.name"), rs.getString("h2.nam_hoc")));
					teach.setWeekEnd(w2);

					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));

					int uId = rs.getInt("user_id");
					User user = new UserDaoImpl().getUserById(uId);
					teach.setUser(user);
					// teach.setHol(rs.getBoolean("is_hol"));
					teach.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("latitude"), rs.getDouble("longitude")));
					// teach.setLate(rs.getBoolean("is_late"));
					// teach.setLateMin(rs.getInt("late_min"));
					// teach.setReason(rs.getString("reason"));

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
	 * @see dao.TeachDao#getListTeach(int, int)
	 */
	@Override
	public List<Teach> getListTeach(int weekId, int userId) {
		List<Teach> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			try {

				sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE t.week_start<= ? AND t.week_end >= ? AND user_id = ? ");
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, weekId);
				ps.setInt(2, weekId);
				ps.setInt(3, userId);

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
					w1.setHocKy(new HocKy(rs.getInt("h1.hoc_ky_id"), rs
							.getString("h1.name"), rs.getString("h1.nam_hoc")));
					teach.setWeekStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStartDate(rs.getDate("w2.start_date"));
					w2.setEndDate(rs.getDate("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					w2.setHocKy(new HocKy(rs.getInt("h2.hoc_ky_id"), rs
							.getString("h2.name"), rs.getString("h2.nam_hoc")));
					teach.setWeekEnd(w2);

					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					teach.setUser(user);
					// teach.setHol(rs.getBoolean("is_hol"));
					teach.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("latitude"), rs.getDouble("longitude")));
					// teach.setLate(rs.getBoolean("is_late"));
					// teach.setLateMin(rs.getInt("late_min"));
					// teach.setReason(rs.getString("reason"));

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
					w1.setHocKy(new HocKy(rs.getInt("h1.hoc_ky_id"), rs
							.getString("h1.name"), rs.getString("h1.nam_hoc")));
					teach.setWeekStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStartDate(rs.getDate("w2.start_date"));
					w2.setEndDate(rs.getDate("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					w2.setHocKy(new HocKy(rs.getInt("h2.hoc_ky_id"), rs
							.getString("h2.name"), rs.getString("h2.nam_hoc")));
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
	 * @see dao.TeachDao#getHourTeach(java.lang.String, java.lang.String, int)
	 */
	@Override
	public double getHourTeach(String hocKy, String weekId, int userId) {
		double minusAll = 0;
		if (connectToDB()) {
			try {
				if ("none".equals(weekId)) {
					// lấy tất cả lịch dạy trong học kỳ
					List<Teach> listTeach = getListTeach(null, hocKy, userId);
					if (listTeach.size() != 0) {
						// lặp qua từng lịch dạy
						for (Teach teach : listTeach) {
							double minus = (double) (teach.getTimeEnd()
									.getTime() - teach.getTimeStart().getTime())
									/ (1000 * 60);
							// lặp qua từng tuần của lịch dạy
							for (int i = teach.getWeekStart().getWeekId(); i <= teach
									.getWeekEnd().getWeekId(); i++) {
								// lấy thông tin về lịch dạy từng tuần
								TeachWeek teachWeek = new TeachWeekDaoImpl()
										.getTeachDaoByTeachId(teach, i);
								if (teachWeek != null) {
									if (!teachWeek.isHol()) {
										minusAll += minus;
									}
								}
							}
						}
					}
				} else {
					int wId = Integer.parseInt(weekId);
					// lấy tất cả lịch dạy theo tuần
					List<Teach> listTeach = getListTeach(wId, userId);
					if (listTeach.size() != 0) {
						// lấy thông tin về lịch dạy theo tuần
						for (Teach teach : listTeach) {
							double minus = (double) (teach.getTimeEnd()
									.getTime() - teach.getTimeStart().getTime())
									/ (1000 * 60);
							TeachWeek teachWeek = new TeachWeekDaoImpl()
									.getTeachDaoByTeachId(teach, wId);
							if (teachWeek != null) {
								if (!teachWeek.isHol()) {
									minusAll += minus;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return (double) Math.round(((double) minusAll / 60) * 100) / 100;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.TeachDao#getAllHourTeach(java.lang.String, java.lang.String,
	 * int)
	 */
	@Override
	public double getAllHourTeach(String hocKy, String weekId, int userId) {
		double minusAll = 0;
		if (connectToDB()) {
			try {
				if ("none".equals(weekId)) {
					// lấy tất cả lịch dạy trong học kỳ
					List<Teach> listTeach = getListTeach(null, hocKy, userId);
					if (listTeach.size() != 0) {
						// lặp qua từng lịch dạy
						for (Teach teach : listTeach) {
							double minus = (double) (teach.getTimeEnd()
									.getTime() - teach.getTimeStart().getTime())
									/ (1000 * 60);
							// lặp qua từng tuần của lịch dạy
							for (int i = teach.getWeekStart().getWeekId(); i <= teach
									.getWeekEnd().getWeekId(); i++) {
								// lấy thông tin về lịch dạy từng tuần
								TeachWeek teachWeek = new TeachWeekDaoImpl()
										.getTeachDaoByTeachId(teach, i);
								if (teachWeek != null) {
									minusAll += minus;
								}
							}
						}
					}
				} else {
					int wId = Integer.parseInt(weekId);
					// lấy tất cả lịch dạy theo tuần
					List<Teach> listTeach = getListTeach(wId, userId);
					if (listTeach.size() != 0) {
						// lấy thông tin về lịch dạy theo tuần
						for (Teach teach : listTeach) {
							double minus = (double) (teach.getTimeEnd()
									.getTime() - teach.getTimeStart().getTime())
									/ (1000 * 60);
							TeachWeek teachWeek = new TeachWeekDaoImpl()
									.getTeachDaoByTeachId(teach, wId);
							if (teachWeek != null) {
								minusAll += minus;

							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return (double) Math.round(((double) minusAll / 60) * 100) / 100;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.TeachDao#getHourLateTeach(java.lang.String, java.lang.String,
	 * int)
	 */
	@Override
	public double getHourLateTeach(String hocKy, String weekId, int userId) {
		double minusAll = 0;
		if (connectToDB()) {
			try {
				if ("none".equals(weekId)) {
					// lấy tất cả lịch dạy trong học kỳ
					List<Teach> listTeach = getListTeach(null, hocKy, userId);
					if (listTeach.size() != 0) {
						// lặp qua từng lịch dạy
						for (Teach teach : listTeach) {
							// lặp qua từng tuần của lịch dạy
							for (int i = teach.getWeekStart().getWeekId(); i <= teach
									.getWeekEnd().getWeekId(); i++) {
								// lấy thông tin về lịch dạy từng tuần
								TeachWeek teachWeek = new TeachWeekDaoImpl()
										.getTeachDaoByTeachId(teach, i);
								if (teachWeek != null) {
									if (teachWeek.isLate()) {
										minusAll += teachWeek.getLateMin();
									}
								}
							}
						}
					}
				} else {
					int wId = Integer.parseInt(weekId);
					// lấy tất cả lịch dạy theo tuần
					List<Teach> listTeach = getListTeach(wId, userId);
					if (listTeach.size() != 0) {
						// lấy thông tin về lịch dạy theo tuần
						for (Teach teach : listTeach) {
							TeachWeek teachWeek = new TeachWeekDaoImpl()
									.getTeachDaoByTeachId(teach, wId);
							if (teachWeek != null) {
								if (teachWeek.isLate()) {
									minusAll += teachWeek.getLateMin();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return (double) Math.round(((double) minusAll / 60) * 100) / 100;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.TeachDao#checkTeachByUserId(int)
	 */
	@Override
	public boolean checkTeachByUserId(int userId) {
		{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) FROM `tbl_teach` WHERE `user_id` = ?");
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
	 * @see dao.TeachDao#addTeach(entity.Teach)
	 */
	@Override
	public long addTeach(Teach teach) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `tbl_teach`(`time_start`, `time_end`, `code_class`, `code_subject`, `name`, `user_id`, `phong`, `date_of_week`, `week_start`, `week_end`) VALUES (?,?,?,?,?,?,?,?,?,?)");

		try {
			ps = conn.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			ps.setTime(i++, teach.getTimeStart());
			ps.setTime(i++, teach.getTimeEnd());
			ps.setString(i++, teach.getCodeClass());
			ps.setString(i++, teach.getCodeSubject());
			ps.setString(i++, teach.getName());
			ps.setInt(i++, teach.getUser().getUserId());
			ps.setString(i++, teach.getPhong().getKey());
			ps.setInt(i++, teach.getDateOfWeek());
			ps.setInt(i++, teach.getWeekStart().getWeekId());
			ps.setInt(i++, teach.getWeekEnd().getWeekId());

			int result = ps.executeUpdate();
			if (result != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					return rs.getLong(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.TeachDao#getListTeach(int, long)
	 */
	@Override
	public List<Teach> getListTeach(int userId, long teachId) {
		List<Teach> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			try {
				if (teachId == 0) {
					sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE t.user_id = ? ");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, userId);
				} else {
					sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE t.user_id = ? AND t.teach_id != ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, userId);
					ps.setLong(2, teachId);
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
					w1.setHocKy(new HocKy(rs.getInt("h1.hoc_ky_id"), rs
							.getString("h1.name"), rs.getString("h1.nam_hoc")));
					teach.setWeekStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStartDate(rs.getDate("w2.start_date"));
					w2.setEndDate(rs.getDate("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					w2.setHocKy(new HocKy(rs.getInt("h2.hoc_ky_id"), rs
							.getString("h2.name"), rs.getString("h2.nam_hoc")));
					teach.setWeekEnd(w2);

					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));

					int uId = rs.getInt("user_id");
					User user = new UserDaoImpl().getUserById(uId);
					teach.setUser(user);
					teach.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("latitude"), rs.getDouble("longitude")));

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
	 * @see dao.TeachDao#editTeach(entity.Teach)
	 */
	@Override
	public boolean editTeach(Teach teach) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE `tbl_teach` SET `time_start`=?,`time_end`=?,`code_class`=?,`code_subject`=?,`name`=?,`phong`=?,`date_of_week`=? WHERE `teach_id` = ?");
			try {
				ps = conn.prepareStatement(sql.toString());
				int i = 1;
				ps.setTime(i++, teach.getTimeStart());
				ps.setTime(i++, teach.getTimeEnd());
				ps.setString(i++, teach.getCodeClass());
				ps.setString(i++, teach.getCodeSubject());
				ps.setString(i++, teach.getName());
				ps.setString(i++, teach.getPhong().getKey());
				ps.setInt(i++, teach.getDateOfWeek());
				ps.setLong(i++, teach.getTeachId());

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.TeachDao#deleteTeach(long)
	 */
	@Override
	public boolean deleteTeach(long teachId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM `tbl_teach` WHERE `teach_id` = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, teachId);

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
	 * @see dao.TeachDao#getListTeach(java.util.Map)
	 */
	@Override
	public List<Teach> getListTeach(Map<String, Object> listValue) {
		List<Teach> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_teach t INNER JOIN tbl_position p ON t.phong = p.phong INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_hocky h1 ON w1.hoc_ky_id = h1.hoc_ky_id INNER JOIN tbl_hocky h2 ON w2.hoc_ky_id = h2.hoc_ky_id WHERE ");
			try {
				int i = 0;
				boolean isTime = false;
				for (Entry<String, Object> item : listValue.entrySet()) {
					// System.out.println(item.getValue().getClass());
					if (i != 0 && item.getValue().getClass() != Time.class) {
						sql.append(" AND ");
					}
					if (item.getValue().getClass() == Time.class) {
						isTime = true;
					} else if (item.getValue().getClass() == Date.class) {
						sql.append(" w1.start_date <= ? AND w2.end_date >= ? AND date_of_week = ? ");
					} else if (item.getValue().getClass() == Integer.class) {
						sql.append(item.getKey() + " = ?");
					}
					i++;
				}
				if (isTime) {
					sql.append(" AND ((t.time_start <= ? AND t.time_end>= ?) OR (t.time_start <= ? AND t.time_end>= ?) OR (t.time_start >= ? AND  t.time_end <= ?)) ");
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
					w1.setHocKy(new HocKy(rs.getInt("h1.hoc_ky_id"), rs
							.getString("h1.name"), rs.getString("h1.nam_hoc")));
					teach.setWeekStart(w1);
					Week w2 = new Week();
					w2.setWeekId(rs.getInt("w2.week_id"));
					w2.setStartDate(rs.getDate("w2.start_date"));
					w2.setEndDate(rs.getDate("w2.end_date"));
					w2.setWeekCount(rs.getInt("w2.week_count"));
					w2.setHocKy(new HocKy(rs.getInt("h2.hoc_ky_id"), rs
							.getString("h2.name"), rs.getString("h2.nam_hoc")));
					teach.setWeekEnd(w2);

					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));

					int uId = rs.getInt("user_id");
					User user = new UserDaoImpl().getUserById(uId);
					teach.setUser(user);
					// teach.setHol(rs.getBoolean("is_hol"));
					teach.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("latitude"), rs.getDouble("longitude")));
					// teach.setLate(rs.getBoolean("is_late"));
					// teach.setLateMin(rs.getInt("late_min"));
					// teach.setReason(rs.getString("reason"));

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
	 * @see dao.TeachDao#getUserFreeTeach(java.sql.Time, java.sql.Time,
	 * java.sql.Date)
	 */
	@Override
	public List<String> getUserFreeTeach(Time timeStart, Time timeEnd, Date date) {
		List<String> listUser = new ArrayList<>();
		if (connectToDB()) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(date.getTime());
			int dateOfWeek = cal.get(Calendar.DAY_OF_WEEK);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT u.name FROM `tbl_teach` t INNER JOIN tbl_week w1 ON t.week_start = w1.week_id INNER JOIN tbl_week w2 ON t.week_end = w2.week_id INNER JOIN tbl_user u ON t.user_id = u.user_id WHERE date_of_week = ? AND w1.start_date <= ? AND w2.end_date >= ? AND ((t.time_start <= ? AND t.time_end>= ?) OR (t.time_start <= ? AND t.time_end>= ?) OR (t.time_start >= ? AND  t.time_end <= ?))");
			try {
				int i = 1;
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
				while (rs.next()) {
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
