/**
 * Copyright(C) 2016 Luvina Software Company
 * UserDaoImpl.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.luvina.manageuser.dao.UserDao;
import net.luvina.manageuser.entities.TblGroup;
import net.luvina.manageuser.entities.TblUser;
import net.luvina.manageuser.utils.ConfigureProperties;

/**
 * @author PhuongNH
 *
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
	/* (non-Javadoc)
     * @see manageuser.dao.UserDao#getUserByLoginId(java.lang.String, java.lang.String)
     */
    public TblUser getUserByLoginId(String loginId, String password) {
    	TblUser tblUser = null;
//        if (connectToDB()) {
//            try {
//                StringBuilder sqlCommand = new StringBuilder();
//
//                // create SQL
//                sqlCommand.append("SELECT u.* ");
//                sqlCommand.append("FROM tbl_user AS u ");
//                sqlCommand.append("WHERE ");
//                sqlCommand.append("u.login_id = '" + loginId+ "'");
//                sqlCommand.append(" AND ");
//                sqlCommand.append("u.password = '" + password + "'");
//
//                preparedStatement = connection.prepareStatement(sqlCommand
//                        .toString());
//
//                // Biding values search to SQL
//                /*preparedStatement.setString(1, loginId);
//                preparedStatement.setString(2, password);*/
//
//                // run SQL
//                rs = preparedStatement.executeQuery();
//
//                // read result from SQL
//                if (rs != null) {
//                    while (rs.next()) {
//                        tblUser = new TblUser(rs.getInt("id"), rs
//                                .getString("full_name"), rs.getString("email"),
//                                rs.getString("tel"),rs.getString("login_id"), rs.getString("password"));
//                    }
//                    rs.close();
//                }
//
//            } catch (Exception e) {
//                System.out.println("an exception occur: " + e.getMessage());
//            }
//            closeConnect();
//
//        }
    	String user = ConfigureProperties.getData("user");
    	String pass= ConfigureProperties.getData("password");

    	if(user.equals(loginId) && pass.equals(password)){
    		tblUser = new TblUser();
    		tblUser.setLoginId(loginId);
    		tblUser.setPassword(password);
    	}

        return tblUser;
    }


    /* (non-Javadoc)
     * @see manageuser.dao.UserDao#getAllGroups()
     */
    public List<TblGroup> getAllGroups() {
        List<TblGroup> lsGroups = new ArrayList<TblGroup>();
        if (connectToDB()) {
            try {
                StringBuilder sqlCommand = new StringBuilder();
                sqlCommand.append("SELECT * FROM tbl_group");
                // Order by id ASC
                sqlCommand.append(" ORDER BY id ASC ");
                preparedStatement = connection.prepareStatement(sqlCommand
                        .toString());
                // run SQL
                rs = preparedStatement.executeQuery();

                // read result from SQL
                if (rs != null) {
                    while (rs.next()) {
                        TblGroup group = new TblGroup(rs.getInt("id"), rs
                                .getString("name"));
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


    /* (non-Javadoc)
     * @see manageuser.dao.UserDao#getListUsers(manageuser.entities.TblUser, int, int)
     */
    public List<TblUser> getListUsers(TblUser user, int offset, int limit) {
        List<TblUser> lsUsers = new ArrayList<TblUser>();
        if (connectToDB()) {
            try {
                StringBuilder sqlCommand = new StringBuilder();
                String name = user.getFullName();
                String fullName = user.getFullName().trim();
                int grpId = user.getGroupId();

                // create SQL
                sqlCommand.append("SELECT u.*, g.name as name ");
                sqlCommand.append("FROM tbl_user AS u ");
                sqlCommand.append("INNER JOIN tbl_group AS g ");
                sqlCommand.append("ON u.grp_id = g.id ");

                // Conditions search
                if (grpId > 0 && name.length() > 0) {
                    sqlCommand.append("WHERE ");
                    sqlCommand.append("u.grp_id = " + grpId );
                    sqlCommand.append(" AND ");
                    sqlCommand.append("u.full_name = '" + fullName + "' ");
                } else {
                    if (grpId > 0) {
                        sqlCommand.append("WHERE ");
                        sqlCommand.append("u.grp_id = " + grpId );
                    }
                    if (name.length() > 0) {
                        sqlCommand.append("WHERE ");
                     // Start fix bug ID 6 – Phuong 2016/08/04
                        sqlCommand.append("u.full_name LIKE '%" + fullName + "%' ");
                     // End fix bug ID 6 – Phuong 2016/08/04
                    }
                }

                // Order by full_name ASC
                sqlCommand.append(" ORDER BY u.full_name ASC ");
                sqlCommand.append("  LIMIT " + offset + " , " + limit);

                preparedStatement = connection.prepareStatement(sqlCommand
                        .toString());

                // run SQL
                rs = preparedStatement.executeQuery();

                // read result from SQL
                if (rs != null) {
                    while (rs.next()) {
                        TblUser tblUser = new TblUser(rs.getInt("id"), rs
                                .getString("full_name"), rs.getString("email"),
                                rs.getString("tel"), rs.getString("name"));
                        lsUsers.add(tblUser);
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


    /* (non-Javadoc)
     * @see manageuser.dao.UserDao#getTotalUsers(manageuser.entities.TblUser)
     */
    public int getTotalUsers(TblUser user) {
        int totalUsers = 0;
        if (connectToDB()) {
            try {
                StringBuilder sqlCommand = new StringBuilder();
                String name = user.getFullName();
                String fullName = user.getFullName().trim();
                int grpId = user.getGroupId();

                // create SQL
                sqlCommand.append("SELECT count(*) AS total ");
                sqlCommand.append("FROM tbl_user AS u ");
                sqlCommand.append("INNER JOIN tbl_group AS g ");
                sqlCommand.append("ON u.grp_id = g.id ");

                // Conditions search
                if (grpId > 0 && name.length() > 0) {
                    sqlCommand.append("WHERE ");
                    sqlCommand.append("u.grp_id = " + grpId);
                    sqlCommand.append(" AND ");
                    sqlCommand.append("u.full_name = '" + fullName + "' ");
                } else {
                    if (grpId > 0) {
                        sqlCommand.append("WHERE ");
                        sqlCommand.append("u.grp_id = " + grpId);
                    }
                    if (name.length() > 0) {
                        sqlCommand.append("WHERE ");
                        sqlCommand.append("u.full_name = '" + fullName + "' ");
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
