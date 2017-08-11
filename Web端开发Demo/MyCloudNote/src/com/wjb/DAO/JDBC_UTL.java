package com.wjb.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBC_UTL {
	/*
	 * 用于获得数据库连接
	 */

	private static ComboPooledDataSource cpds = new ComboPooledDataSource();

	public static Connection getConnection() {

		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
