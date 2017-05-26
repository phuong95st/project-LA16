/**
 * Copyright(C) 2017 Luvina software company
 * PositionDaoImpl.java, 02-04-2017 Tran
 */
package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PositionDao;
import entity.Position;

/**
 * 
 * @author Tran
 * 
 */
public class PositionDaoImpl extends BaseDaoImpl implements PositionDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.PositionDao#getListPosition(java.lang.String)
	 */
	@Override
	public List<Position> getListPosition(String shorcut) {
		List<Position> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();

			try {
				if (shorcut == null) {
					sql.append("SELECT * FROM tbl_position ");
					ps = conn.prepareStatement(sql.toString());
				} else {
					sql.append("SELECT * FROM tbl_position WHERE phong LIKE ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setString(1, shorcut + "%");
				}

				rs = ps.executeQuery();
				while (rs.next()) {
					list.add(new Position(rs.getString("phong"), rs
							.getDouble("latitude"), rs.getDouble("longitude")));
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
	 * @see dao.PositionDao#getPositionById(java.lang.String)
	 */
	@Override
	public Position getPositionById(String key) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_position WHERE phong = ?");
			try {				
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, key);

				rs = ps.executeQuery();
				if (rs.next()) {
					return new Position(rs.getString("phong"), rs.getDouble("latitude"), rs.getDouble("longitude"));
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return null;
	}

}
