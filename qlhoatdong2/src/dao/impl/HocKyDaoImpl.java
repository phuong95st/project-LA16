/**
 * Copyright(C) 2017 Luvina software company
 * HocKyDaoImpl.java, 01-04-2017 Tran
 */
package dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import dao.HocKyDao;
import entity.HocKy;

/**
 * 
 * @author Tran
 *
 */
public class HocKyDaoImpl extends BaseDaoImpl implements HocKyDao {
	

	/**
	 * 
	 */
	public HocKyDaoImpl() {
		super();
	}
	
	/**
	 * 
	 */
	public HocKyDaoImpl(String str) {
		super();
		connectToDB();
	}

	/* (non-Javadoc)
	 * @see dao.HocKyDao#getListHocKy()
	 */
	@Override
	public List<HocKy> getListHocKy() {
		List<HocKy> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_hocky ");

			try {
				ps = conn.prepareStatement(sql.toString());

				rs = ps.executeQuery();
				while (rs.next()) {
					list.add(new HocKy(rs.getInt("hoc_ky_id"),rs.getString("name"),rs.getString("nam_hoc")));
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
	 * @see dao.HocKyDao#getHocKy(java.sql.Timestamp)
	 */
	@Override
	public String getHocKy(Timestamp now) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			Date date = new Date(now.getTime());
			
			sql.append("SELECT h.name FROM tbl_hocky h INNER JOIN tbl_week w ON h.hoc_ky_id = w.hoc_ky_id WHERE w.start_date <= ? AND w.end_date >= ?");
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setDate(1, date);
				ps.setDate(2, date);

				rs = ps.executeQuery();
				if(rs.next()){
					return rs.getString(1);
				}
			}  catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.HocKyDao#getListNamHoc()
	 */
	@Override
	public List<String> getListNamHoc() {
		List<String> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT nam_hoc FROM tbl_hocky ");

			try {
				ps = conn.prepareStatement(sql.toString());

				rs = ps.executeQuery();
				while (rs.next()) {
					list.add(rs.getString("nam_hoc"));
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
	 * @see dao.HocKyDao#add(entity.HocKy)
	 */
	@Override
	public int add(HocKy hocKy) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `tbl_hocky`(`name`, `nam_hoc`) VALUES (?,?)");
		
		try {
			ps = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, hocKy.getName());
			ps.setString(2, hocKy.getNamHoc());
			
			int result = ps.executeUpdate();
			if(result != 0){
				rs = ps.getGeneratedKeys();
				if(rs.next()){
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see dao.HocKyDao#existNamHoc(java.lang.String)
	 */
	@Override
	public boolean existNamHoc(String namHoc) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) FROM `tbl_hocky` WHERE `nam_hoc` = ?");
			
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, namHoc);
				
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
	public static void main(String[] args){
		System.out.println(new HocKyDaoImpl().existNamHoc("2017-2018"));
	}

	/* (non-Javadoc)
	 * @see dao.HocKyDao#getLisHocKy(java.lang.String)
	 */
	@Override
	public List<HocKy> getLisHocKy(String namHoc) {
		List<HocKy> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM `tbl_hocky` WHERE `nam_hoc` = ? ORDER BY `name` ASC");
			
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, namHoc);
				
				rs = ps.executeQuery();
				while (rs.next()) {
					list.add(new HocKy(rs.getInt("hoc_ky_id"),rs.getString("name"),rs.getString("nam_hoc")));
				}
			}catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return list;
	}
}
