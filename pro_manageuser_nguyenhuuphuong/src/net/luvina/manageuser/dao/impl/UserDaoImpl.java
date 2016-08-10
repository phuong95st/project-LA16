/**
 * Copyright(C) 2016 Luvina Software Company
 * UserDaoImpl.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.dao.impl;

import net.luvina.manageuser.dao.UserDao;
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
                        sqlCommand.append("u.full_name LIKE '%" + fullName + "%' ");
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
