package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjb.model.Note;
import com.wjb.model.NoteDAO;

/**
 * ��ѯ�û��ʼ�Servlet
 * 
 * @author Administrator
 *
 */
public class UpdateNotesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// ����û���ID;
		// ���һ���ʼ�;
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		int  note_id=Integer.parseInt(req.getParameter("note_id"));

		// ������һ���ʼ�note
		Note note = new Note(note_id,title, content,"",0);

		System.out.println(note+"------------------->");
		NoteDAO noteDAO = new NoteDAO();
		boolean updateResult = noteDAO.updateNote(note);
		System.out.println(updateResult+"------------------->");
		// ����ѯ���Ľ�����ص���ҳ��
		PrintWriter pWriter = resp.getWriter();
		pWriter.write("" + updateResult);
		pWriter.flush();
		pWriter.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
