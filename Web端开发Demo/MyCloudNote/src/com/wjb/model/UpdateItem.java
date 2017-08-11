package com.wjb.model;

/**
 * @Discribtion:
 * @author:Administrator
 * @date:Aug 9, 2017
 * @time:8:45:58 AM
 */
public class UpdateItem {

	private int history_id;
	private int notes_id;
	private String update_time;
	private int update_type;
	private String before_title;
	private String after_title;
	private String before_content;
	private String after_content;

	/**
	 * @return the history_id
	 */
	public int getHistory_id() {
		return history_id;
	}

	/**
	 * @param history_id
	 *            the history_id to set
	 */
	public void setHistory_id(int history_id) {
		this.history_id = history_id;
	}

	/**
	 * @return the notes_id
	 */
	public int getNotes_id() {
		return notes_id;
	}

	/**
	 * @param notes_id
	 *            the notes_id to set
	 */
	public void setNotes_id(int notes_id) {
		this.notes_id = notes_id;
	}

	/**
	 * @return the update_time
	 */
	public String getUpdate_time() {
		return update_time;
	}

	/**
	 * @param update_time
	 *            the update_time to set
	 */
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	/**
	 * @return the update_type
	 */
	public int getUpdate_type() {
		return update_type;
	}

	/**
	 * @param update_type
	 *            the update_type to set
	 */
	public void setUpdate_type(int update_type) {
		this.update_type = update_type;
	}

	/**
	 * @return the before_title
	 */
	public String getBefore_title() {
		return before_title;
	}

	/**
	 * @param before_title
	 *            the before_title to set
	 */
	public void setBefore_title(String before_title) {
		this.before_title = before_title;
	}

	/**
	 * @return the before_content
	 */
	public String getBefore_content() {
		return before_content;
	}

	/**
	 * @param before_content
	 *            the before_content to set
	 */
	public void setBefore_content(String before_content) {
		this.before_content = before_content;
	}

	/**
	 * @return the after_title
	 */
	public String getAfter_title() {
		return after_title;
	}

	/**
	 * @param after_title
	 *            the after_title to set
	 */
	public void setAfter_title(String after_title) {
		this.after_title = after_title;
	}

	/**
	 * @return the after_content
	 */
	public String getAfter_content() {
		return after_content;
	}

	/**
	 * @param after_content
	 *            the after_content to set
	 */
	public void setAfter_content(String after_content) {
		this.after_content = after_content;
	}

	/**
	 * @param history_id
	 * @param notes_id
	 * @param update_time
	 * @param update_type
	 * @param before_title
	 * @param after_title
	 * @param before_content
	 * @param after_content
	 */
	public UpdateItem(int history_id, int notes_id, String update_time, int update_type, String before_title,
			String after_title, String before_content, String after_content) {
		super();
		this.history_id = history_id;
		this.notes_id = notes_id;
		this.update_time = update_time;
		this.update_type = update_type;
		this.before_title = before_title;
		this.after_title = after_title;
		this.before_content = before_content;
		this.after_content = after_content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UpdateItem [notes_id=" + notes_id + ", update_time=" + update_time + ", update_type=" + update_type
				+ ", before_title=" + before_title + ", before_content=" + before_content + ", after_title="
				+ after_title + ", after_content=" + after_content + ", history_id=" + history_id + "]";
	}

	/**
	 * 
	 */
	public UpdateItem() {
		super();
	}
}
