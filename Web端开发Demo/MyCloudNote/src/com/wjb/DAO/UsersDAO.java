package com.wjb.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO extends BaseDAO {

	/*
	 * 注册账户
	 */
	public boolean register(String username, String password) {
		int rows = executeUpdate("insert into users(username,password) values(?,?)",
				new Object[] { username, password });
		return rows > 0;
	}

	/*
	 * 登录用户
	 * 
	 * @param username
	 * 
	 * @param password
	 * 
	 * @return 用户ID
	 */
	public int login(String username, String password) {
		int id = 0;
		ResultSet res = executeQuery("select _id from users where username=? and password=? ",
				new Object[] { username, password });
		try {
			if (res != null) {
				while (res.next()) {
					id = res.getInt("_id");
				}
				res.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public static void main(String args[]) {

		UsersDAO dao = new UsersDAO();
		boolean result = dao.register("qwewer", "123");
		int id = dao.login("qwewer", "123");
		if (id > 0)
			cout("YES" + result);
		else
			cout("NO" + result);

		// String input = "http://www.altavista.com/cgi-bin/"
		// +
		// "query?pg=q&kl=XX&stype=stext&q=%2B%22Java+I%2FO%22&search.x=38&search.y=3";
		// String output = null;
		// try {
		// output = URLDecoder.decode(input, "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(output);
	}

	private static void cout(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}

}
