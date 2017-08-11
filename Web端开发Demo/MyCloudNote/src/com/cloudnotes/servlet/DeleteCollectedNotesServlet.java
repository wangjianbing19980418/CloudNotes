package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjb.model.NoteDAO;

/**
 * 查询用户笔记Servlet
 * 
 * @author Administrator
 *
 */
public class DeleteCollectedNotesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub

		// 获得用户的ID;
		// 获得一条笔记;
		System.out.println(req.getParameter("note_id"));
		int note_id = Integer.parseInt(req.getParameter("note_id"));

		System.out.println("取消收藏笔记" + note_id);
		NoteDAO noteDAO = new NoteDAO();
		boolean deleteCollectedNotesResult = noteDAO.deleteCollectedNote(note_id);
		System.out.println("取消收藏笔记结果" + deleteCollectedNotesResult);
		// 将查询到的结果返回到网页中
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
