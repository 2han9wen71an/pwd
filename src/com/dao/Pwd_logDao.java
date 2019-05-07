package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Pwd_log;
import com.util.PageUtil;

public class Pwd_logDao {
	public static List Select(int id, String uname, PageUtil page) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			if (id == 1) {
				ps = conn.prepareStatement("SELECT * FROM PWD_LOG", ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);// 设置PreparedStatement的ResultSet为可滚动结果集);
			} else if (uname != null) {
				ps = conn.prepareStatement("SELECT * FROM PWD_LOG WHERE USERNAME=?", ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);// 设置PreparedStatement的ResultSet为可滚动结果集);
				ps.setString(1, uname);

			}
			if (page != null) {
				ps.setMaxRows(page.getEndIndex());
			}
			rs = ps.executeQuery();
			if (page != null) {
				if (page.getBeginIndex() > 0) {
					rs.absolute(page.getBeginIndex());// 关键代码，直接移动游标为当前页起始记录处
				}
			}
			while (rs.next()) {
				Pwd_log log = new Pwd_log(rs.getInt("userid"), rs.getString("username"), rs.getInt("ev"),
						rs.getString("url"), rs.getString("ip"), rs.getString("ua"), rs.getString("json"),
						rs.getString("time"), rs.getString("record"));
				list.add(log);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return list;
	}
}
