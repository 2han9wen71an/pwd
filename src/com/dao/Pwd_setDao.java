package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.Pwd_set;

public class Pwd_setDao {
	public static Pwd_set SelectAll() {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pwd_set set = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM PWD_SET");
			rs = ps.executeQuery();
			while (rs.next()) {
				set = new Pwd_set(rs.getString("username"), rs.getString("pass"), rs.getInt("qq"),
						rs.getString("email"), rs.getString("title"), rs.getString("describe"),
						rs.getString("keywords"), rs.getInt("sign"), rs.getInt("email_sign"), rs.getString("ipadmin"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return set;
	}

	public static Pwd_set SelectUser(String user, String pwd) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pwd_set set = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM PWD_SET WHERE USERNAME=? AND PASS=?");
			ps.setString(1, user);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			while (rs.next()) {
				set = new Pwd_set(rs.getInt("id"), rs.getString("username"), rs.getString("pass"), rs.getInt("qq"),
						rs.getString("email"), rs.getString("title"), rs.getString("describe"),
						rs.getString("keywords"), rs.getInt("sign"), rs.getInt("email_sign"), rs.getString("ipadmin"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return set;
	}

	public static int UserUpdate(Pwd_set set) {
		String sql = "UPDATE PWD_SET SET USERNAME=?,PASS=?,EMAIL=?,QQ=? WHERE ID=?";
		Object[] pas = { set.getUsername(), set.getPass(), set.getEmail(), set.getQq(), set.getId() };
		return BaseDao.executeUpdate(sql, pas);
	}

	public static int SetUpdate(Pwd_set set) {
		String sql = "UPDATE PWD_SET SET title=?,describe=?,keywords=?,ipadmin=?,sign=?";
		Object[] pas = { set.getTitle(), set.getDescribe(), set.getKeywords(), set.getIpadmin(), set.getSign() };
		return BaseDao.executeUpdate(sql, pas);
	}
}
