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
public class FindHistoryItemsServlet extends HttpServlet {

	private final static String TAG = "FindHistoryItemsServlet";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		// ����û���ID;
		int user_id = Integer.parseInt(req.getParameter("userId"));
		// ��ѯ���û��ıʼ�notes
		HistoryNoteDAO historyNoteDao = new HistoryNoteDAO();
		List<UpdateItem> historyItemsList = historyNoteDao.findHistoryItems(user_id);
		// ���ʼǼ���ת��ΪJSON
		String json = JSON.toJSONString(historyItemsList);
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
