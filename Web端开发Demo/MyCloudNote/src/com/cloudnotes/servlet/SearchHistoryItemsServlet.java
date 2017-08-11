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
 * ��ѯ�û��ʼ�Servlet
 * 
 * @author Administrator
 *
 */
public class SearchHistoryItemsServlet extends HttpServlet {

	private final static String TAG = "SearchHistoryItemsServlet";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		// ����û���ID;
		String user_id_string = req.getParameter("userId");
		String key_words = req.getParameter("key_words");
		int user_id = Integer.parseInt(user_id_string);
		// ��ѯ���û��ıʼ�notes
		HistoryNoteDAO noteDao = new HistoryNoteDAO();
		List<UpdateItem> notesList = noteDao.searchUpdateItem(user_id, key_words);
		// ���ʼǼ���ת��ΪJSON
		String json = JSON.toJSONString(notesList);

		System.out.println(TAG + "�����" + json);
		// ����ѯ���Ľ�����ص���ҳ��
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
