package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjb.model.NoteDAO;

/**
 * ��ѯ�û��ʼ�Servlet
 * 
 * @author Administrator
 *
 */
public class DeleteCollectedNotesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub

		// ����û���ID;
		// ���һ���ʼ�;
		System.out.println(req.getParameter("note_id"));
		int note_id = Integer.parseInt(req.getParameter("note_id"));

		System.out.println("ȡ���ղرʼ�" + note_id);
		NoteDAO noteDAO = new NoteDAO();
		boolean deleteCollectedNotesResult = noteDAO.deleteCollectedNote(note_id);
		System.out.println("ȡ���ղرʼǽ��" + deleteCollectedNotesResult);
		// ����ѯ���Ľ�����ص���ҳ��
		PrintWriter pWriter = resp.getWriter();
		pWriter.write("" + deleteCollectedNotesResult);
		pWriter.flush();
		pWriter.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
