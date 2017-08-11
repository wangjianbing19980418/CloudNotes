package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjb.model.Note;
import com.wjb.model.NoteDAO;

public class InsertNotesServlet extends HttpServlet {

	private final static String TAG = "InsertNotesServlet";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub

		// ���һ���ʼ�;
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String title = req.getParameter("title");
		String content = req.getParameter("content");

		String creating_date = req.getParameter("creating_date");
		int user_id = Integer.parseInt(req.getParameter("user_id"));

		// title = new String(title.getBytes("iso8859-1"), "UTF-8");
		// content = new String(content.getBytes("iso8859-1"), "UTF-8");

		// ������һ���ʼ�note
		Note note = new Note(0, title, content, creating_date, user_id);
		// �������ʼǲ��뵽notes����
		NoteDAO noteDao = new NoteDAO();
		int insertIndex = noteDao.insertNote(note);

		System.out.print(TAG + "-------insertIndex-------->" + insertIndex);
		// ������Ľ�����ص���ҳ��
		PrintWriter pw = resp.getWriter();
		pw.write("" + insertIndex);
		pw.flush();
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
