package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjb.model.HistoryNoteDAO;
import com.wjb.model.UpdateItem;

/**
 * ��ѯ�û��ʼ�Servlet
 * 
 * @author Administrator
 *
 */
public class UpdateHistoryItemsServlet extends HttpServlet {

	private final static String TAG = "UpdateHistoryItemsServlet";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub

		// ����û���ID;
		// ���һ���ʼ�;
		String after_title = req.getParameter("after_title");
		String before_title = req.getParameter("before_title");
		String after_content = req.getParameter("after_content");
		String before_content = req.getParameter("before_content");
		int history_id = Integer.parseInt(req.getParameter("history_id"));
		String update_time = req.getParameter("update_time");

		// ������һ���ʼ�note
		UpdateItem updateItem = new UpdateItem(history_id, 0, update_time, 4, before_title, after_title, before_content,
				after_content);

		System.out.println(TAG + updateItem.toString());
		HistoryNoteDAO historyNoteDAO = new HistoryNoteDAO();
		boolean updateResult = historyNoteDAO.updateHistoryItem(updateItem);
		System.out.println(TAG + "���" + updateResult);
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
