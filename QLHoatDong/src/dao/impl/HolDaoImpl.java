/**
 * Copyright(C) 2017 Luvina software company
 * HolDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import dao.HolDao;
import entity.Hol;
import entity.User;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class HolDaoImpl extends BaseDaoImpl implements HolDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.HolDao#getListHol(java.sql.Timestamp, int)
	 */
	@Override
	public List<Hol> getListHol(Timestamp time, int userId) {
		List<Hol> list = new ArrayList<Hol>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_hol_sche WHERE start_date > ? AND user_id=? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setTimestamp(1, time);
				ps.setInt(2, userId);

				rs = ps.executeQuery();
				while (rs.next()) {
					Hol hol = new Hol();
					hol.setId(rs.getInt("id"));
					hol.setType(rs.getInt("type"));
					hol.setStart(rs.getTimestamp("start_date"));
					hol.setEnd(rs.getTimestamp("end_date"));
					hol.setReason(rs.getString("reason"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					hol.setUser(user);
					hol.setStatus(rs.getBoolean("status"));
					hol.setPhep(rs.getBoolean("phep"));

					list.add(hol);
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
	 * @see dao.HolDao#update(entity.Hol, boolean, boolean)
	 */
	@Override
	public boolean update(Hol hol, boolean changeStatus) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();

			try {
				if (changeStatus) {
					sql.append("UPDATE tbl_hol_sche SET status = ?, phep = ? WHERE id = ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setBoolean(1, hol.isStatus());
					ps.setBoolean(2, hol.isPhep());
					ps.setLong(3, hol.getId());
				} else {
					sql.append("UPDATE tbl_hol_sche SET type=?, start_date=?, end_date=?, reason =? WHERE id=?");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, hol.getType());
					ps.setTimestamp(2, hol.getStart());
					ps.setTimestamp(3, hol.getEnd());
					ps.setString(4, hol.getReason());
					ps.setLong(5, hol.getId());
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
	 * @see dao.HolDao#getHolByTime(java.sql.Timestamp, int)
	 */
	@Override
	public Hol getHolByTime(Timestamp time, int userId) {
		Hol hol = null;
		if (connectToDB()) {
			// change
			Calendar cal = Calendar.getInstance(TimeZone
					.getTimeZone("Asia/Ho_Chi_Minh"));
			cal.setTimeInMillis(time.getTime());
			Calendar calStart = new GregorianCalendar(cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			Calendar calEnd = new GregorianCalendar(cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
					23, 59, 59);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_hol_sche WHERE ((start_date > ? AND start_date < ?) OR (end_date > ? AND end_date < ?)) AND  user_id=? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setTimestamp(1, new Timestamp(calStart.getTimeInMillis()));
				ps.setTimestamp(2, new Timestamp(calEnd.getTimeInMillis()));
				ps.setTimestamp(3, new Timestamp(calStart.getTimeInMillis()));
				ps.setTimestamp(4, new Timestamp(calEnd.getTimeInMillis()));
				ps.setInt(5, userId);

				rs = ps.executeQuery();
				if (rs.next()) {
					hol = new Hol();
					hol.setId(rs.getInt("id"));
					hol.setType(rs.getInt("type"));
					hol.setStart(rs.getTimestamp("start_date"));
					hol.setEnd(rs.getTimestamp("end_date"));
					hol.setReason(rs.getString("reason"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					hol.setUser(user);
					hol.setStatus(rs.getBoolean("status"));
					hol.setPhep(rs.getBoolean("phep"));

				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return hol;
	}

	public static void main(String[] args) {
		System.out.println(new HolDaoImpl().getHolByTime(new Timestamp(Calendar
				.getInstance().getTimeInMillis()), 1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.HolDao#insert(entity.Hol)
	 */
	@Override
	public boolean insert(Hol hol) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tbl_hol_sche (type, start_date, end_date, reason, user_id, status, phep) VALUES (?,?,?,?,?,?,?)");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, hol.getType());
				ps.setTimestamp(2, hol.getStart());
				ps.setTimestamp(3, hol.getEnd());
				ps.setString(4, hol.getReason());
				ps.setInt(5, hol.getUser().getUserId());
				ps.setBoolean(6, hol.isStatus());
				ps.setBoolean(7, hol.isPhep());

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
	 * @see dao.HolDao#delete(long)
	 */
	@Override
	public boolean delete(long id) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM tbl_hol_sche WHERE id=?");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, id);

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
	 * @see dao.HolDao#insertByTrans(java.util.Map)
	 */
	@Override
	public boolean insertByTrans(Map<Long, Hol> map) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tbl_hol_sche (type, start_date, end_date, reason, user_id, status, phep) VALUES (?,?,?,?,?,?,?)");
			try {
				conn.setAutoCommit(false);
				
				boolean flag = false;
				for (Entry<Long, Hol> item : map.entrySet()) {
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, item.getValue().getType());
					ps.setTimestamp(2, item.getValue().getStart());
					ps.setTimestamp(3, item.getValue().getEnd());
					ps.setString(4, item.getValue().getReason());
					ps.setInt(5, item.getValue().getUser().getUserId());
					ps.setBoolean(6, item.getValue().isStatus());
					ps.setBoolean(7, item.getValue().isPhep());
					
					int result = ps.executeUpdate();
					if (result == 0) {
						flag  = true;
						break;
					}
				}
				
				if(!flag){
					conn.setAutoCommit(true);
					return true;
				}
				conn.rollback();
				
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}finally{
				close();
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.HolDao#getListHolByStatus(boolean, int)
	 */
	@Override
	public List<Hol> getListHolByStatus(boolean status, int userId) {
		List<Hol> list = new ArrayList<Hol>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_hol_sche WHERE status = ? AND user_id=? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setBoolean(1, status);
				ps.setInt(2, userId);

				rs = ps.executeQuery();
				while (rs.next()) {
					Hol hol = new Hol();
					hol.setId(rs.getInt("id"));
					hol.setType(rs.getInt("type"));
					hol.setStart(rs.getTimestamp("start_date"));
					hol.setEnd(rs.getTimestamp("end_date"));
					hol.setReason(rs.getString("reason"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					hol.setUser(user);
					hol.setStatus(rs.getBoolean("status"));
					hol.setPhep(rs.getBoolean("phep"));

					list.add(hol);
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
