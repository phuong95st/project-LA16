/**
 * Copyright(C) 2017 Luvina software company
 * HocKyDao.java, 01-04-2017 Tran
 */
package dao;

import java.sql.Timestamp;
import java.util.List;

import entity.HocKy;

/**
 * 
 * @author Tran
 *
 */
public interface HocKyDao {
	public List<HocKy> getListHocKy();
	
	public String getHocKy(Timestamp now);
	
	public List<String> getListNamHoc();
	
	public int add(HocKy hocKy);
	
	public boolean existNamHoc(String namHoc);
	
	public List<HocKy> getLisHocKy(String namHoc);
}
