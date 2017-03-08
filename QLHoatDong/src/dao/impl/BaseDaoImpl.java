/**
 * Copyright(C) 2017 Luvina software company
 * BaseDaoImpl.java, Feb 7, 2017 nguyenhuuphuong
 */
package dao.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.DatabaseProperties;
import dao.BaseDao;


/**
 * 
 * @author nguyenhuuphuong
 *
 */
public class BaseDaoImpl implements BaseDao {
	protected static Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;

	/**
	 * 
	 */
	public BaseDaoImpl() {
		super();
	}


	/* (non-Javadoc)
	 * @see net.luvina.shop.dao.BaseDao#connectToDB()
	 */
	@Override
	public boolean connectToDB() {
		try {
			Class.forName(DatabaseProperties.getData("driverClassName").trim());
			conn = DriverManager.getConnection(DatabaseProperties.getData("url").trim(), DatabaseProperties.getData("username").trim(), DatabaseProperties.getData("password").trim());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see net.luvina.shop.dao.BaseDao#close()
	 */
	@Override
	public void close() {
		if(conn != null){
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("Not close connection!");
			}
		}

	}

	/**
	 * 
	 * @return
	 */
	public static Connection getConn(){
		return conn;
	}
	
	/**
	 * 
	 * @param connection
	 */
	public static void setConn(Connection connection){
		conn = connection;
	}
}
