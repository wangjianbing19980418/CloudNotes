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

		// 通过请求中的notes_id得到这条笔记note的id
		int user_id = Integer.parseInt(req.getParameter("user_id"));

		// 删除这条笔记
		HistoryNoteDAO historyNoteDAO = new HistoryNoteDAO();
		boolean deleteResult = historyNoteDAO.deleteAllHistoryNote(user_id);

		System.out.println(TAG + "结果：" + deleteResult);
		// 将插入的结果返回到网页中
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
