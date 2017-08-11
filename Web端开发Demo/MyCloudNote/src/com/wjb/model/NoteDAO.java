package com.wjb.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wjb.DAO.BaseDAO;

public class NoteDAO extends BaseDAO {

	public int insertNote(Note note) {
		String sql = "insert into notes(title,content,creating_date,user_id) values(?,?,?,?)";
		int index = executeInsertQuery(sql,
				new Object[] { note.getTitle(), note.getContent(), note.getCreating_date(), note.getUser_id() });
		return index;
	}

	public boolean deleteNote(int id) {
		String sql = "delete from notes where notes_id=?";
		int rows = executeUpdate(sql, new Object[] { id });
		return rows > 0;
	}

	public boolean updateNote(Note note) {

		String sql = "update notes set title=?,content=? where notes_id=?";
		int rows = executeUpdate(sql, new Object[] { note.getTitle(), note.getContent(), note.getId() });
		return rows > 0;
	}

	public boolean collectNote(int note_id) {
		String sql = "update notes set isCollected=1 where notes_id=?";
		int rows = executeUpdate(sql, new Object[] { note_id });
		return rows > 0;
	}

	public boolean deleteCollectedNote(int note_id) {

		String sql = "update notes set isCollected=0 where notes_id=?";
		int rows = executeUpdate(sql, new Object[] { note_id });
		return rows > 0;
	}

	public List<Note> findNodes(int userId) {

		List<Note> notes = new ArrayList<Note>();
		ResultSet res = executeQuery("select * from notes where user_id=?", new Object[] { userId });
		try {
			while (res.next()) {
				int id = res.getInt("notes_id");
				String title = res.getString("title");
				String content = res.getString("content");
				String creating_date = res.getString("creating_date");

				Note note = new Note(id, title, content, creating_date, userId);
				notes.add(note);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}

	public List<Note> findCollectedNodes(int userId) {

		List<Note> notes = new ArrayList<Note>();
		ResultSet res = executeQuery("select * from notes where user_id=? and isCollected=1", new Object[] { userId });
		try {
			while (res.next()) {
				int id = res.getInt("notes_id");
				String title = res.getString("title");
				String content = res.getString("content");
				String creating_date = res.getString("creating_date");

				Note note = new Note(id, title, content, creating_date, userId);
				notes.add(note);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}

	public List<Note> searchNodes(int userId, String key_words) {

		List<Note> notes = new ArrayList<Note>();
		ResultSet res = executeQuery("select * from notes where user_id=? and " + "(title like '%" + key_words
				+ "%' or content like '%" + key_words + "%')", new Object[] { userId });
		try {
			while (res.next()) {
				int id = res.getInt("notes_id");
				String title = res.getString("title");
				String content = res.getString("content");
				String creating_date = res.getString("creating_date");

				Note note = new Note(id, title, content, creating_date, userId);
				notes.add(note);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}

	public List<Note> searchCollectedNodes(int userId, String key_words) {

		List<Note> notes = new ArrayList<Note>();
		ResultSet res = executeQuery("select * from notes where user_id=? and isCollected=1 and " + "(title like '%"
				+ key_words + "%' or content like '%" + key_words + "%')", new Object[] { userId });
		try {
			while (res.next()) {
				int id = res.getInt("notes_id");
				String title = res.getString("title");
				String content = res.getString("content");
				String creating_date = res.getString("creating_date");

				Note note = new Note(id, title, content, creating_date, userId);
				notes.add(note);
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}

	/**
	 * @return
	 * @enclosing_method:getLastInsertId
	 * @return:int
	 */
	public int getLastInsertId() {
		// TODO Auto-generated method stub
		String sql = "select LAST_INSERT_ID()";
		int rows = executeUpdate(sql, new Object[] {});
		return rows;
	}

	public static void main(String[] args) {
		NoteDAO dao = new NoteDAO();
		List<Note> notes = dao.searchNodes(40, "w");
		cout("" + notes);
	}

	public static void cout(String s) {
		System.out.println(s);
	}

}
