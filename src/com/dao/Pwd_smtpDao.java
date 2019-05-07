package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.Pwd_smtp;

public class Pwd_smtpDao {
	public static Pwd_smtp Select() {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pwd_smtp smtp = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM PWD_SMTP");
			rs = ps.executeQuery();
			while (rs.next()) {
				smtp = new Pwd_smtp(rs.getString("host"), rs.getInt("port"), rs.getString("username"),
						rs.getString("password"), rs.getString("sub"), rs.getInt("ssl"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return smtp;
	}

	public static int Update(Pwd_smtp smtp) {
		String sql = "UPDATE PWD_SMTP SET host=?,port=?,username=?,password=?,sub=?,ssl=?";
		Object[] pas = { smtp.getHost(), smtp.getPort(), smtp.getUsername(), smtp.getPassword(), smtp.getSub(),
				smtp.getSsl() };
		return BaseDao.executeUpdate(sql, pas);
	}
}
