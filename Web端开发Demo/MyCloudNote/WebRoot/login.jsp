<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

		
<%
		String username = URLEncoder.encode(req.getParameter("username"), "utf-8");
		String password = URLEncoder.encode(req.getParameter("password"), "utf-8");

		System.out.println("\nnew   " + username + "jfjfjjfjjf------------------------>" + password);
		UsersDAO usersDao = new UsersDAO();

		System.out.println("gdggdgdgdggd------------------------->" + MD5Util.getMd5(password));
		String sssString = URLDecoder.decode(username, "utf-8");
		System.out.println(sssString);
 %>
</body>
</html>