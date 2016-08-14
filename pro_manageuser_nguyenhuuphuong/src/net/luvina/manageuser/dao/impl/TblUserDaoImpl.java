/**
 * Copyright(C) 2016 Luvina Software Company
 * TblUserDaoImpl.java, Aug 9, 2016 PhuongNH
 */
package net.luvina.manageuser.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.luvina.manageuser.dao.TblUserDao;
import net.luvina.manageuser.entities.UserInfor;
import net.luvina.manageuser.utils.Common;

/**
 * Thực thi phương thức getListUser của interface TblUserDao
 *
 * @author PhuongNH
 *
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {

	/*
	 * (non-Javadoc)
	 *
	 * @see net.luvina.manageuser.dao.TblUserDao#getListUsers(int, int, int,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId,
			String fullName, String sortType, String sortByFullName,
			String sortByCodeLevel, String sortByEndDate) {
		List<UserInfor> lsUsers = new ArrayList<UserInfor>();
		fullName = Common.escapeInjection(fullName);
		if (connectToDB()) {
			try {
				StringBuilder sqlCommand = new StringBuilder();
				// String name = user.getFullName();
				// String fullName = user.getFullName().trim();
				// int grpId = user.getGroupId();

				// create SQL
				sqlCommand
						.append("SELECT u.user_id,full_name,birthday, group_name, email,tel,name_level,end_date ");
				sqlCommand.append("FROM tbl_user u ");
				sqlCommand.append("INNER JOIN mst_group g ");
				sqlCommand.append("ON u.group_id = g.group_id ");
				sqlCommand.append("LEFT JOIN (tbl_detail_user_japan d ");
				sqlCommand.append("INNER JOIN mst_japan j ");
				sqlCommand.append("ON d.code_level = j.code_level) ");
				sqlCommand.append("ON u.user_id = d.user_id ");

				// Conditions search
				if (groupId > 0 && fullName.length() > 0) {
					sqlCommand.append(" WHERE ");
					sqlCommand.append("u.group_id = " + groupId);
					sqlCommand.append(" AND ");
					sqlCommand.append("u.full_name = '" + fullName + "' ");
				} else {
					if (groupId > 0) {
						sqlCommand.append(" WHERE ");
						sqlCommand.append("u.group_id = " + groupId);
					}
					if (fullName.length() > 0) {
						sqlCommand.append(" WHERE ");
						// Start fix bug ID 6 – Phuong 2016/08/04
						sqlCommand.append("u.full_name LIKE '%" + fullName
								+ "%' ");
						// End fix bug ID 6 – Phuong 2016/08/04
					}
				}

				// Order by full_name ASC
				if ("full_name".equals(sortType)) {
					sqlCommand.append(" ORDER BY " + sortType + " "
							+ sortByFullName + ", j.code_level "
							+ sortByCodeLevel + ", end_date " + sortByEndDate);
				} else if ("code_level".equals(sortType)) {
					sqlCommand.append(" ORDER BY j." + sortType + " "
							+ sortByCodeLevel + ", full_name " + sortByFullName
							+ ", end_date " + sortByEndDate);
				} else {
					sqlCommand.append(" ORDER BY " + sortType + " "
							+ sortByEndDate + ", full_name " + sortByFullName
							+ ", j.code_level " + sortByCodeLevel);
				}
				sqlCommand.append(" LIMIT " + offset + " , " + limit);

				System.out.println(sqlCommand);
				preparedStatement = connection.prepareStatement(sqlCommand
						.toString());

				// run SQL
				rs = preparedStatement.executeQuery();

				// read result from SQL
				if (rs != null) {
					while (rs.next()) {
						UserInfor userInfor = new UserInfor();
						userInfor.setId(rs.getInt("u.user_id"));
						userInfor.setFullName(rs.getString("full_name"));
						userInfor.setBirthday(rs.getString("birthday"));
						userInfor.setGroupName(rs.getString("group_name"));
						userInfor.setEmail(rs.getString("email"));
						userInfor.setTel(rs.getString("tel"));
						userInfor.setNameLevel(rs.getString("name_level"));
						userInfor.setEndDate(rs.getString("end_date"));

						lsUsers.add(userInfor);
					}
					rs.close();
				}

			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
			}
			closeConnect();

		}
		return lsUsers;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.luvina.manageuser.dao.TblUserDao#getTotalUser(int,
	 * java.lang.String)
	 */
	@Override
	public int getTotalUser(int groupId, String fullName) {
		int totalUsers = 0;
		fullName = Common.escapeInjection(fullName);
		if (connectToDB()) {
			try {
				StringBuilder sqlCommand = new StringBuilder();
				// String name = user.getFullName();
				// String fullName = user.getFullName().trim();
				// int grpId = user.getGroupId();

				// create SQL
				sqlCommand.append("SELECT count(*) AS total ");
				sqlCommand.append("FROM tbl_user AS u ");
				sqlCommand.append("INNER JOIN mst_group AS g ");
				sqlCommand.append("ON u.group_id = g.group_id ");

				// Conditions search
				if (groupId > 0 && fullName.length() > 0) {
					sqlCommand.append("WHERE ");
					sqlCommand.append("u.group_id = " + groupId);
					sqlCommand.append(" AND ");
					sqlCommand.append("u.full_name = '" + fullName + "' ");
				} else {
					if (groupId > 0) {
						sqlCommand.append("WHERE ");
						sqlCommand.append("u.group_id = " + groupId);
					}
					if (fullName.length() > 0) {
						sqlCommand.append("WHERE ");
						sqlCommand.append("u.full_name LIKE '%" + fullName
								+ "%' ");
					}
				}
				preparedStatement = connection.prepareStatement(sqlCommand
						.toString());

				// run SQL
				rs = preparedStatement.executeQuery();

				// read result from SQL
				if (rs != null) {
					while (rs.next()) {
						totalUsers = rs.getInt("total");
					}
					rs.close();
				}

			} catch (Exception e) {
				System.out.println("an exception occur: " + e.getMessage());
			}
			closeConnect();
		}
		return totalUsers;
	}
}
