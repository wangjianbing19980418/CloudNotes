package com.wjb.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wjb.DAO.BaseDAO;

public class HistoryNoteDAO extends BaseDAO {

	public boolean insertHistoryNote(UpdateItem updateItem) {

		String sql = "insert into history_item(notes_id,update_time,update_type,before_title,after_title,before_content,after_content) values(?,?,?,?,?,?,?)";
		int rows = executeUpdate(sql,
				new Object[] { updateItem.getNotes_id(), updateItem.getUpdate_time(), updateItem.getUpdate_type(),
						updateItem.getBefore_title(), updateItem.getAfter_title(), updateItem.getBefore_content(),
						updateItem.getAfter_content() });
		return rows > 0;
	}

	public boolean deleteHistoryNote(int id) {
		String sql = "delete from history_item where history_id=?";
		int rows = executeUpdate(sql, new Object[] { id });
		return rows > 0;
	}

	public boolean deleteAllHistoryNote(int user_id) {
		String sql = "delete from history_item where notes_id in(select distinct notes_id from notes where user_id=?)";
		int rows = executeUpdate(sql, new Object[] { user_id });
		return rows > 0;
	}

	public List<UpdateItem> findHistoryItems(int userId) {

		List<UpdateItem> updateItems = new ArrayList<UpdateItem>();
		ResultSet res = executeQuery(
				"select * from history_item where notes_id in(select distinct notes_id from notes where user_id=?)",
				new Object[] { userId });
		try {
			while (res.next()) {
				int history_id = res.getInt("history_id");
				int notes_id = res.getInt("notes_id");
				String update_time = res.getString("update_time");
				int update_type = res.getInt("update_type");
				String before_title = res.getString("before_title");
				String after_title = res.getString("after_title");
				String before_content = res.getString("before_content");
				String after_content = res.getString("after_content");

				UpdateItem updateItem = new UpdateItem(history_id, notes_id, update_time, update_type, before_title,
						after_title, before_content, after_content);
				updateItems.add(updateItem);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateItems;
	}

	public List<UpdateItem> searchUpdateItem(int userId, String key_words) {

		List<UpdateItem> updateItems = new ArrayList<UpdateItem>();
		ResultSet res = executeQuery(
				"select * from history_item where notes_id in(select distinct notes_id from notes where user_id=?) and "
						+ "(before_title like '%" + key_words + "%' or after_title like '%" + key_words
						+ "%' or before_content like '%" + key_words + "%' or after_content like '%" + key_words
						+ "%')",
				new Object[] { userId });
		try {
			while (res.next()) {
				int history_id = res.getInt("history_id");
				int notes_id = res.getInt("notes_id");
				String update_time = res.getString("update_time");
				int update_type = res.getInt("update_type");
				String before_title = res.getString("before_title");
				String after_title = res.getString("after_title");
				String before_content = res.getString("before_content");
				String after_content = res.getString("after_content");

				UpdateItem updateItem = new UpdateItem(history_id, notes_id, update_time, update_type, before_title,
						after_title, before_content, after_content);
				updateItems.add(updateItem);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateItems;
	}

	public boolean updateHistoryItem(UpdateItem updateItem) {

		String sql = "update history_item set before_title=?,after_title=?,before_content=? ,after_content=? ,update_time=? where history_id=?";
		int rows = executeUpdate(sql,
				new Object[] { updateItem.getBefore_title(), updateItem.getAfter_title(),
						updateItem.getBefore_content(), updateItem.getAfter_content(), updateItem.getUpdate_time(),
						updateItem.getHistory_id() });
		return rows > 0;
	}

	public static void main(String[] args) {
		HistoryNoteDAO dao = new HistoryNoteDAO();

		UpdateItem updateItem1 = new UpdateItem(0, 27, "1997/03/07 11:12:14", 4, "before_title", "after_title",
				"before_content", "after_content");
		UpdateItem updateItem2 = new UpdateItem(0, 28, "1997/03/08 11:12:14", 4, "before_title", "after_title",
				"before_content", "after_content");
		UpdateItem updateItem3 = new UpdateItem(0, 32, "1997/03/02 11:12:14", 4, "before_title", "after_title",
				"before_content", "after_content");
		UpdateItem updateItem4 = new UpdateItem(0, 33, "1997/03/03 11:12:14", 4, "before_title", "after_title",
				"before_content", "after_content");

		cout("Ìí¼Ó" + dao.insertHistoryNote(updateItem1));
		dao.insertHistoryNote(updateItem2);
		dao.insertHistoryNote(updateItem3);
		dao.insertHistoryNote(updateItem4);

		List<UpdateItem> notes = dao.searchUpdateItem(40, "o");
		cout("search" + notes);

		notes = dao.findHistoryItems(40);
		cout("find" + notes);

		cout("É¾³ý" + dao.deleteHistoryNote(2));

		// cout("É¾³ýÈ«²¿" + dao.deleteAllHistoryNote(40));

	}

	public static void cout(String s) {
		System.out.println(s);
	}
}
