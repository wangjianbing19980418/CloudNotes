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
public class GetLastInsertIdServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub

		// ����û���ID;
		// ���һ���ʼ�;

		NoteDAO noteDAO = new NoteDAO();
		int addNotesResult = noteDAO.getLastInsertId();
		System.out.println("�ղرʼǽ��" + addNotesResult);
		// ����ѯ���Ľ�����ص���ҳ��
		PrintWriter pWriter = resp.getWriter();
		pWriter.write("" + addNotesResult);
		pWriter.flush();
		pWriter.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
