package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjb.model.Note;
import com.wjb.model.NoteDAO;

/**
 * 查询用户笔记Servlet
 * 
 * @author Administrator
 *
 */
public class UpdateNotesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 获得用户的ID;
		// 获得一条笔记;
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		int  note_id=Integer.parseInt(req.getParameter("note_id"));

		// 将生成一条笔记note
		Note note = new Note(note_id,title, content,"",0);

		System.out.println(note+"------------------->");
		NoteDAO noteDAO = new NoteDAO();
		boolean updateResult = noteDAO.updateNote(note);
		System.out.println(updateResult+"------------------->");
		// 将查询到的结果返回到网页中
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
