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
public class SearchHistoryItemsServlet extends HttpServlet {

	private final static String TAG = "SearchHistoryItemsServlet";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		// 获得用户的ID;
		String user_id_string = req.getParameter("userId");
		String key_words = req.getParameter("key_words");
		int user_id = Integer.parseInt(user_id_string);
		// 查询该用户的笔记notes
		HistoryNoteDAO noteDao = new HistoryNoteDAO();
		List<UpdateItem> notesList = noteDao.searchUpdateItem(user_id, key_words);
		// 将笔记集合转换为JSON
		String json = JSON.toJSONString(notesList);

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
