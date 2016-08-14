/**
 * Copyright(C) 2016 Luvina Software Company
 * MstGroupDaoImpl.java, Aug 10, 2016 nhphuong
 */
package net.luvina.manageuser.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.luvina.manageuser.dao.MstGroupDao;
import net.luvina.manageuser.entities.MstGroup;

/**
 * thực thi phương thức getAllGroup trong interface MstGroupDao
 * @author nhphuong
 *
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {

	/* (non-Javadoc)
	 * @see net.luvina.manageuser.dao.MstGroupDao#getAllGroups()
	 */
	@Override
	public List<MstGroup> getAllGroups() {
		List<MstGroup> lsGroups = new ArrayList<MstGroup>();
		if (connectToDB()) {
			try {
				StringBuilder sqlCommand = new StringBuilder();
				sqlCommand.append("SELECT * FROM mst_group");
				// Order by id ASC
				sqlCommand.append(" ORDER BY group_id ASC ");
				preparedStatement = connection.prepareStatement(sqlCommand
						.toString());
				// run SQL
				rs = preparedStatement.executeQuery();

				// read result from SQL
				if (rs != null) {
					while (rs.next()) {
						MstGroup group = new MstGroup();
						group.setId(Integer.parseInt(rs.getString("group_id")));
						group.setName(rs.getString("group_name"));

						lsGroups.add(group);
					}
					rs.close();
				}

			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
			}
			closeConnect();

		}
		return lsGroups;
	}

}
