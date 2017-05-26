/**
 * Copyright(C) 2017 Luvina software company
 * CheckInStudentDaoImpl.java, 02-04-2017 Tran
 */
package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CheckInStudentDao;
import entity.CheckInStudent;
import entity.HocKy;
import entity.ScheStu;
import entity.Week;

/**
 * 
 * @author Tran
 *
 */
public class CheckInStudentDaoImpl extends BaseDaoImpl implements CheckInStudentDao{

	/* (non-Javadoc)
	 * @see dao.CheckInStudentDao#getListCheckInById(int)
	 */
	@Override
	public List<CheckInStudent> getListCheckInByScheStu(ScheStu scheStu) {
		List<CheckInStudent> list = new ArrayList<>();
		if(connectToDB()){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_checkin_stu c INNER JOIN tbl_sche_stu ss ON c.sche_stu_id = ss.id INNER JOIN tbl_week w ON c.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE c.sche_stu_id = ? AND h.name = ? ");
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, scheStu.getId());
				ps.setString(2, scheStu.getwEnd().getHocKy().getName());
				
				rs = ps.executeQuery();
				while(rs.next()){
					CheckInStudent check = new CheckInStudent();
					check.setId(rs.getLong("c.id"));
					check.setScheStu(scheStu);
					
					Week week = new Week();
					week.setWeekId(rs.getInt("w.week_id"));
					week.setWeekCount(rs.getInt("w.week_count"));
					week.setStartDate(rs.getDate("w.start_date"));
					week.setEndDate(rs.getDate("w.end_date"));
					week.setHocKy(new HocKy(rs.getInt("h.hoc_ky_id"), rs.getString("h.name"), rs.getString("h.nam_hoc")));
					check.setWeek(week);
					
					check.setStatus(rs.getBoolean("status"));
					check.setContent(rs.getString("content"));
					check.setOther(rs.getString("c.other"));
					
					list.add(check);
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
	 * @see dao.CheckInStudentDao#getCheckInStudentById(long)
	 */
	@Override
	public CheckInStudent getCheckInStudentById(long id) {
		CheckInStudent check = null;
		if(connectToDB()){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_checkin_stu c INNER JOIN tbl_sche_stu ss ON c.sche_stu_id = ss.id INNER JOIN tbl_week w ON c.week_id = w.week_id INNER JOIN tbl_hocky h ON w.hoc_ky_id = h.hoc_ky_id WHERE c.id = ? ");
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, id);
				
				rs = ps.executeQuery();
				if(rs.next()){
					check = new CheckInStudent();
					
					check.setId(rs.getLong("c.id"));
					ScheStu scheStu = new ScheStuDaoImpl().getScheStuById(rs.getInt("c.sche_stu_id"));
					check.setScheStu(scheStu);
					
					Week week = new Week();
					week.setWeekId(rs.getInt("w.week_id"));
					week.setWeekCount(rs.getInt("w.week_count"));
					week.setStartDate(rs.getDate("w.start_date"));
					week.setEndDate(rs.getDate("w.end_date"));
					week.setHocKy(new HocKy(rs.getInt("h.hoc_ky_id"), rs.getString("h.name"), rs.getString("h.nam_hoc")));
					check.setWeek(week);
					
					check.setStatus(rs.getBoolean("status"));
					check.setContent(rs.getString("content"));
					check.setOther(rs.getString("c.other"));
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return check;
	}

	/* (non-Javadoc)
	 * @see dao.CheckInStudentDao#edit(entity.CheckInStudent)
	 */
	@Override
	public boolean edit(CheckInStudent check) {
		if (connectToDB()) {
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE tbl_checkin_stu SET `status` =?, `content`=?, `other`=? WHERE `id` = ?");

				ps = conn.prepareStatement(sql.toString());
				int i = 1;
				ps.setBoolean(i++, check.isStatus());
				ps.setString(i++, check.getContent());
				ps.setString(i++, check.getOther());
				ps.setLong(i++, check.getId());
				
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
