package com.wjb.model;

public class Note {

	private int id;
	private String title;
	private String content;
	private String creating_date;
	private int user_id;

	public Note(int note_id, String title, String content, String creating_date, int user_id) {
		super();
		this.id = note_id;
		this.title = title;
		this.content = content;
		this.creating_date = creating_date;
		this.user_id = user_id;
	}

	public Note() {

		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreating_date() {
		return creating_date;
	}

	public void setCreating_date(String creating_date) {
		this.creating_date = creating_date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", title=" + title + ", content=" + content + ", creating_date=" + creating_date
				+ ", user_id=" + user_id + "]";
	}

}
