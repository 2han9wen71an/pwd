package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Pwd_plan;

public class Pwd_planDao {
	public static Pwd_plan Select(int uid) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pwd_plan plan = null;
		try {
			if (uid != 0) {
				ps = conn.prepareStatement("SELECT * FROM PWD_PLAN WHERE USERID=? AND TYPE>0");
				ps.setInt(1, uid);
			} else {
				ps = conn.prepareStatement("SELECT * FROM PWD_PLAN");
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				plan = new Pwd_plan(rs.getInt("userid"), rs.getString("username"), rs.getString("email"),
						rs.getString("stime"), rs.getInt("type"), rs.getString("content"), rs.getInt("status"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return plan;
	}

	public static List SelectAll(String time) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			ps = conn.prepareStatement("SELECT * FROM PWD_PLAN WHERE STIME>? AND STATUS=0");
			ps.setString(1, time);
			rs = ps.executeQuery();
			while (rs.next()) {
				Pwd_plan plan = new Pwd_plan(rs.getInt("id"),rs.getInt("userid"), rs.getString("username"), rs.getString("email"),
						rs.getString("stime"), rs.getInt("type"), rs.getString("content"), rs.getInt("status"));
				list.add(plan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return list;
	}
	public static int Update(Pwd_plan plan) {
		String sql = "UPDATE PWD_PLAN SET STATUS=? WHERE ID=?";
		Object[] pas = {plan.getStatus(),plan.getId()};
		return BaseDao.executeUpdate(sql, pas);
	}
}
