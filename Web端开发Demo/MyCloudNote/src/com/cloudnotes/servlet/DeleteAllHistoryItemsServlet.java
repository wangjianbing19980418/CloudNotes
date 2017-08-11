package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjb.model.HistoryNoteDAO;

public class DeleteAllHistoryItemsServlet extends HttpServlet {

	private final static String TAG = "DeleteAllHistoryItemsServlet";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub

		// ͨ�������е�notes_id�õ������ʼ�note��id
		int user_id = Integer.parseInt(req.getParameter("user_id"));

		// ɾ�������ʼ�
		HistoryNoteDAO historyNoteDAO = new HistoryNoteDAO();
		boolean deleteResult = historyNoteDAO.deleteAllHistoryNote(user_id);

		System.out.println(TAG + "�����" + deleteResult);
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
