/**
 * Copyright(C) 2017 Luvina software company
 * HocKyLogic.java, 05-05-2017 Tran
 */
package logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import utils.Common;
import dao.HocKyDao;
import dao.WeekDao;
import dao.impl.BaseDaoImpl;
import dao.impl.HocKyDaoImpl;
import dao.impl.WeekDaoImpl;
import entity.HocKy;
import entity.Week;

/**
 * 
 * @author Tran
 * 
 */
public class HocKyLogic {
	public boolean creatNamHoc(String namHoc,Date startDate,int total1, int total2, int total3) {
		// tạo connection
		HocKyDao hocKyDao = new HocKyDaoImpl("");
		// get connection
		Connection conn = BaseDaoImpl.getConn();
			
		try {
			// set autocommit
			conn.setAutoCommit(false);
			
			// lặp 3 lần để insert 3 học kỳ
			int countWeek = 1;
			Date countStartDate = startDate;
			System.out.println("start: "+countStartDate);
			Date countEndDate = new Date(countStartDate.getTime() + (6*24*60*60*1000));
			System.out.println("end: "+ countEndDate);
			for (int i = 1; i <= 3; i++) {
				// tạo đối tượng học kỳ
				HocKy hocKy = new HocKy(0, Common.getHocKy(namHoc)+ i, namHoc);
				// add hocKy
				int generatedKey = hocKyDao.add(hocKy);
				if(generatedKey == 0){
					conn.rollback();
					return false;
				}
				// lặp qua số tuần của từng học kỳ để add week
				
				int maxLoop = 0;
				if(i == 1){
					maxLoop = total1;
				}else if(i == 2){
					maxLoop = total2;
				}else{
					maxLoop = total3;
				}
				WeekDao weekDao = new WeekDaoImpl();
				for (int j = 1; j <= maxLoop; j++) {
					// tạo đối tượng week
					Week week = new Week();
					week.setStartDate(countStartDate);
					week.setEndDate(countEndDate);
					week.setWeekCount(countWeek);
					week.setHocKy(new HocKy(generatedKey, Common.getHocKy(namHoc)+ i, namHoc));
					
					// add week
					if(!weekDao.addWeek(week)){
						conn.rollback();
						return false;
					}
					
					// tăng các biến count
					countStartDate.setTime(countStartDate.getTime() + (7*24*60*60*1000));
					countEndDate.setTime(countStartDate.getTime() + (6*24*60*60*1000));
					countWeek++;
				}
				
				// set auto commit true
				conn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
}
