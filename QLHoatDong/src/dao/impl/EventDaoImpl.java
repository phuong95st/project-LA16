/**
 * Copyright(C) 2017 Luvina software company
 * EventDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.EventDao;
import entity.Event;
import entity.TypeEvent;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class EventDaoImpl extends BaseDaoImpl implements EventDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.EventDao#getListEvent(java.sql.Timestamp, java.sql.Timestamp)
	 */
	@Override
	public List<Event> getListEvent(Timestamp timeStart, Timestamp timeEnd) {
		List<Event> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_event e INNER JOIN tbl_type_event te ON e.type_id = te.type_id WHERE time_start > ? AND time_start < ? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setTimestamp(1, timeStart);
				ps.setTimestamp(2, timeEnd);

				rs = ps.executeQuery();
				while (rs.next()) {
					Event event = new Event();
					event.setEventId(rs.getInt("event_id"));
					event.setType(new TypeEvent(rs.getInt("te.type_id"), rs.getString("te.name")));
					event.setStart(rs.getTimestamp("time_start"));
					event.setEnd(rs.getTimestamp("time_end"));
					event.setPlace(rs.getString("place"));
					event.setContent(rs.getString("content"));
					event.setTitle(rs.getString("title"));

					list.add(event);
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
