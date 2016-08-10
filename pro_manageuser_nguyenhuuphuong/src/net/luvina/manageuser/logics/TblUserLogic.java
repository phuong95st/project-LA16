/**
 * Copyright(C) 2016 Luvina Software Company
 * TblUserLogic.java, Aug 9, 2016 PhuongNH
 */
package net.luvina.manageuser.logics;

import java.util.List;

import net.luvina.manageuser.entities.TblUser;
import net.luvina.manageuser.entities.UserInfor;

/**
 * @author PhuongNH
 *
 */
public interface TblUserLogic {
	/**
	 *
	 * @param offset
	 *            vị trí data cần lấy nào
	 * @param limit
	 *            số lượng lấy
	 * @param groupId
	 *            mã nhóm tìm kiếm
	 * @param fullName
	 *            Tên tìm kiếm
	 * @param sortType
	 *            Nhận biết xem cột nào được ưu tiên sắp xếp(full_name or
	 *            end_date or code_level)
	 * @param sortByFullName
	 *            Giá trị sắp xếp của cột Tên(ASC or DESC)
	 * @param sortByCodeLevel
	 *            Giá trị sắp xếp của cột Trình độ tiếng nhật(ASC or DESC)
	 * @param sortByEndDate Giá trị sắp xếp của cột Ngày kết hạn(ASC or DESC)
	 * @return Danh sách các đối tượng UserInfor
	 */
	public List<UserInfor> getListUsers(int offset, int limit, int groupId,
			String fullName, String sortType, String sortByFullName,
			String sortByCodeLevel, String sortByEndDate);

	/**
	 * getTotalListUsers
	 *
	 * @param groupId
	 *            group_id trong db
	 * @param fullName
	 *            full_name trong db
	 * @return Tổng số user lấy được trong db
	 */
	public int getTotalUsers(int groupId, String fullName);
}
