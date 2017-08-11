package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.wjb.model.HistoryNoteDAO;
import com.wjb.model.UpdateItem;

/**
 * 查询用户笔记Servlet
 * 
 * @author Administrator
 *
 */
public class FindHistoryItemsServlet extends HttpServlet {

	private final static String TAG = "FindHistoryItemsServlet";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		// 获得用户的ID;
		int user_id = Integer.parseInt(req.getParameter("userId"));
		// 查询该用户的笔记notes
		HistoryNoteDAO historyNoteDao = new HistoryNoteDAO();
		List<UpdateItem> historyItemsList = historyNoteDao.findHistoryItems(user_id);
		// 将笔记集合转换为JSON
		String json = JSON.toJSONString(historyItemsList);
		System.out.println(TAG + "结果：" + json);
		// 将查询到的结果返回到网页中
		PrintWriter pWriter = resp.getWriter();
		pWriter.write("" + json);
		pWriter.flush();
		pWriter.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
