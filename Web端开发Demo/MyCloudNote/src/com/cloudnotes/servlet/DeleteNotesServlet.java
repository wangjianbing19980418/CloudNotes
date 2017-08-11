package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjb.model.NoteDAO;

public class DeleteNotesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// ͨ�������е�notes_id�õ������ʼ�note��id
		int note_id = Integer.parseInt(req.getParameter("note_id"));

		// ɾ�������ʼ�
		NoteDAO noteDAO = new NoteDAO();
		boolean deleteResult = noteDAO.deleteNote(note_id);

		// ������Ľ�����ص���ҳ��
		PrintWriter pWriter = resp.getWriter();
		pWriter.write("" + deleteResult);
		pWriter.flush();
		pWriter.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
