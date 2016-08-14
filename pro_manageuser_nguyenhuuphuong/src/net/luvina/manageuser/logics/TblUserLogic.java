/**
 * Copyright(C) 2016 Luvina Software Company
 * TblUserLogic.java, Aug 9, 2016 PhuongNH
 */
package net.luvina.manageuser.logics;

import java.util.List;
import net.luvina.manageuser.entities.UserInfor;

/**
 * Định nghĩa các hàm getListUsers() và getTotalUser() để lấy data trong db
 * thông qua dao
 *
 * @author PhuongNH
 *
 */
public interface TblUserLogic {
	/**
	 * Get list user
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
	 * @param sortByEndDate
	 *            Giá trị sắp xếp của cột Ngày kết hạn(ASC or DESC)
	 * @return Danh sách các đối tượng UserInfor
	 */
	public List<UserInfor> getListUsers(int offset, int limit, int groupId,
			String fullName, String sortType, String sortByFullName,
			String sortByCodeLevel, String sortByEndDate);

	/**
	 * Thực hiện lấy số bản ghi theo groupId và fullName
	 *
	 * @param groupId
	 *            id của group
	 * @param fullName
	 *            tên của user
	 * @return tổng số bản ghi lấy được
	 */
	public int getTotalUser(int groupId, String fullName);
}
