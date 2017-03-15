/**
 * Copyright(C) 2017 Luvina software company
 * TeachDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	public List<Teach> getListTeach(Timestamp timeStart, Timestamp timeEnd,
			int userId) {
		List<Teach> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM (tbl_teach t INNER JOIN tbl_week w ON t.week_id = w.week_id) INNER JOIN tbl_position p ON t.phong = p.phong WHERE time_start > ? AND time_start < ? AND user_id = ? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setTimestamp(1, timeStart);
				ps.setTimestamp(2, timeEnd);
				ps.setInt(3, userId);

				rs = ps.executeQuery();
				while (rs.next()) {
					Teach teach = new Teach();
					teach.setTeachId(rs.getInt("teach_id"));
					teach.setStart(rs.getTimestamp("time_start"));
					teach.setEnd(rs.getTimestamp("time_end"));
					teach.setWeek(new Week(rs.getInt("w.week_id"), rs
							.getInt("week_count")));
					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					teach.setUser(user);
					teach.setHol(rs.getBoolean("is_hol"));
					teach.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("latitude"), rs.getDouble("longitude")));
					teach.setLate(rs.getBoolean("is_late"));
					teach.setLateMin(rs.getInt("late_min"));
					teach.setReason(rs.getString("reason"));

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
			sql.append("SELECT * FROM (tbl_teach t INNER JOIN tbl_week w ON t.week_id = w.week_id) INNER JOIN tbl_position p ON t.phong = p.phong WHERE teach_id = ? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, id);

				rs = ps.executeQuery();
				if (rs.next()) {
					teach = new Teach();
					teach.setTeachId(rs.getInt("teach_id"));
					teach.setStart(rs.getTimestamp("time_start"));
					teach.setEnd(rs.getTimestamp("time_end"));
					teach.setWeek(new Week(rs.getInt("w.week_id"), rs
							.getInt("week_count")));
					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					teach.setUser(user);
					teach.setHol(rs.getBoolean("is_hol"));
					teach.setPhong(new Position(rs.getString("p.phong"), rs
							.getDouble("latitude"), rs.getDouble("longitude")));
					teach.setLate(rs.getBoolean("is_late"));
					teach.setLateMin(rs.getInt("late_min"));
					teach.setReason(rs.getString("reason"));
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
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tbl_teach SET time_start=?, time_end=?, week_id=?, code_class=?, code_subject=?, name=?, user_id=?, is_hol=?, phong=?, is_late=?, late_min=?, reason=? WHERE teach_id=?");
			try {
				ps = conn.prepareStatement(sql.toString());
				int i = 1;
				ps.setTimestamp(i++, teach.getStart());
				ps.setTimestamp(i++, teach.getEnd());
				ps.setInt(i++, teach.getWeek().getWeekId());
				ps.setString(i++, teach.getCodeClass());
				ps.setString(i++, teach.getCodeSubject());
				ps.setString(i++, teach.getName());
				ps.setInt(i++, teach.getUser().getUserId());
				ps.setBoolean(i++, teach.isHol());
				ps.setString(i++, teach.getPhong().getKey());
				ps.setBoolean(i++, teach.isLate());
				ps.setInt(i++, teach.getLateMin());
				ps.setString(i++, teach.getReason());
				ps.setLong(i++, teach.getTeachId());

				int result = ps.executeUpdate();
				if(result != 0){
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
