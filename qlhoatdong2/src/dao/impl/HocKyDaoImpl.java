/**
 * Copyright(C) 2017 Luvina software company
 * HocKyDaoImpl.java, 01-04-2017 Tran
 */
package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.HocKyDao;
import entity.HocKy;

/**
 * 
 * @author Tran
 *
 */
public class HocKyDaoImpl extends BaseDaoImpl implements HocKyDao {

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

}
