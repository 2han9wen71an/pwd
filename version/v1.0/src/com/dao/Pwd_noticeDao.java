package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Pwd_notice;

public class Pwd_noticeDao {
	public static List Select() {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			ps = conn.prepareStatement("SELECT * FROM PWD_NOTICE");
			rs = ps.executeQuery();
			while (rs.next()) {
				Pwd_notice notice = new Pwd_notice(rs.getInt("id"),rs.getString("content"), rs.getString("time"), rs.getInt("grade"));
				list.add(notice);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return list;
	}

	public static int delete(Pwd_notice notice) {
		String sql = "DELETE FROM PWD_notice WHERE ID=?";
		Object[] pas = {notice.getId()};
		return BaseDao.executeUpdate(sql, pas);
	}
}
