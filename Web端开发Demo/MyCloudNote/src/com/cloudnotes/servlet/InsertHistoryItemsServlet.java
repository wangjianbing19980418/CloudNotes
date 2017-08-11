package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjb.model.HistoryNoteDAO;
import com.wjb.model.UpdateItem;

public class InsertHistoryItemsServlet extends HttpServlet {

	private final static String TAG = "InsertHistoryItemsServlet";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub

		// 获得一条笔记;
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		int notes_id = Integer.parseInt(req.getParameter("notes_id"));
		String update_time = req.getParameter("update_time");

		int update_type = Integer.parseInt(req.getParameter("update_type"));

		String before_title = null, before_content = null, after_title = null, after_content = null;
		switch (update_type) {
		case 1:
			// 增加笔记
			after_title = req.getParameter("after_title");
			after_content = req.getParameter("after_content");
			break;

		case 2:
			// 删除笔记
			before_title = req.getParameter("before_title");
			before_content = req.getParameter("before_content");
			break;

		case 3:
			// 查询笔记
			before_title = req.getParameter("before_title");
			before_content = req.getParameter("before_content");
			after_title = req.getParameter("after_title");
			after_content = req.getParameter("after_content");
			break;
		case 4:
			before_title = req.getParameter("before_title");
			before_content = req.getParameter("before_content");
			after_title = req.getParameter("after_title");
			after_content = req.getParameter("after_content");
			// 修改笔记
			break;
		default:
			getServletContext().log("error getParameters:", new IllegalStateException("没有正确的得到参数"));
			break;
		}

		UpdateItem updateItem = new UpdateItem(0, notes_id, update_time, update_type, before_title, after_title,
				before_content, after_content);
		// 将这条笔记插入到notes表中
		System.out.println(updateItem);
		HistoryNoteDAO noteDao = new HistoryNoteDAO();
		boolean insertResult = noteDao.insertHistoryNote(updateItem);

		System.out.print(TAG + "插入结果：" + insertResult);
		// 将插入的结果返回到网页中
		PrintWriter pw = resp.getWriter();
		pw.write("" + insertResult);
		pw.flush();
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
