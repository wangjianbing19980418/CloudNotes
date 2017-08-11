package com.wjb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {

	public static int executeUpdate(String sql, Object[] args) {

		Connection conn = JDBC_UTL.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < args.length; i++) {
			try {
				ps.setObject(i + 1, args[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int rows = 0;
		try {
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return rows;
	}

	public static ResultSet executeQuery(String sql, Object[] args) {

		Connection conn = JDBC_UTL.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			ResultSet res = ps.executeQuery();
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		/*
		 * ���ﲻ�����ps�رգ�close,��ΪҪ����һ��resultSet, ���������ֱ�ӷ��صģ�
		 * �������Ϊ�����prepareStatement�ر�֮�� �ͻ���resҲ�ᶪʧ���ݣ� ���resҲ�ᱻĬ�ϵ�close
		 */
	}

	public static int executeInsertQuery(String sql, Object[] args) {

		Connection conn = JDBC_UTL.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys(); // equivalent to "SELECT
													// last_insert_id();"
			if (keys.next()) {
				return (keys.getInt(1));
			}
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		/*
		 * ���ﲻ�����ps�رգ�close,��ΪҪ����һ��resultSet, ���������ֱ�ӷ��صģ�
		 * �������Ϊ�����prepareStatement�ر�֮�� �ͻ���resҲ�ᶪʧ���ݣ� ���resҲ�ᱻĬ�ϵ�close
		 */
	}

	private static void cout(String string) {

		// TODO Auto-generated method stub
		System.out.println(string);
	}
}
