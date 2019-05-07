package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Pwd_user;
import com.util.PageUtil;

public class Pwd_userDao {
	public static Pwd_user UserSelect(String user, String pwd, String mail) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pwd_user user_ = null;
		try {
			if (pwd == null || "".equals(pwd)) {
				ps = conn.prepareStatement("SELECT * FROM Pwd_user WHERE USERNAME = ? OR EMAIL=?");
				ps.setString(1, user);
				ps.setString(2, mail);
			} else if ((mail != null || user != null) && pwd != null) {
				ps = conn.prepareStatement("SELECT * FROM Pwd_user WHERE PASS=? AND (USERNAME = ? OR EMAIL = ? )");
				ps.setString(1, pwd);
				ps.setString(2, user);
				ps.setString(3, user);
			} else {
				ps = conn.prepareStatement("SELECT * FROM Pwd_user WHERE EMAIL=?");
				ps.setString(1, mail);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				user_ = new Pwd_user(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getInt("qq"),
						rs.getString("stime"), rs.getString("ltime"), rs.getString("sip"), rs.getString("lip"),
						rs.getString("token"), rs.getInt("status"), rs.getInt("error_num"), rs.getString("email_token"),
						rs.getLong("token_exptime"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return user_;
	}

	public static Pwd_user SelectToken(String token) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pwd_user user_ = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM Pwd_user WHERE TOKEN=?");
			ps.setString(1, token);
			rs = ps.executeQuery();
			while (rs.next()) {
				user_ = new Pwd_user(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getInt("qq"),
						rs.getString("stime"), rs.getString("ltime"), rs.getString("sip"), rs.getString("lip"),
						rs.getString("token"), rs.getInt("status"), rs.getInt("error_num"), rs.getString("email_token"),
						rs.getLong("token_exptime"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return user_;
	}

	public static int UserErrUpdate(String user) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = conn.prepareStatement("UPDATE PWD_USER SET ERROR_NUM=ERROR_NUM+1 WHERE USERNAME=?");
			ps.setString(1, user);
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return i;
	}

	public static List SelectAll(int id, PageUtil page) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			if (id != 0) {
				ps = conn.prepareStatement("SELECT * FROM Pwd_user WHERE ID = ?");
				ps.setInt(1, id);
			} else {
				ps = conn.prepareStatement("SELECT * FROM Pwd_user");
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
				Pwd_user user_ = new Pwd_user(rs.getInt("id"), rs.getString("username"), rs.getString("pass"),
						rs.getString("email"), rs.getInt("qq"), rs.getString("stime"), rs.getString("ltime"),
						rs.getString("sip"), rs.getString("lip"), rs.getString("token"), rs.getInt("status"),
						rs.getInt("error_num"), rs.getString("email_token"), rs.getLong("token_exptime"));
				list.add(user_);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return list;
	}

	public static List SelectBySearch(int type, String content) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			switch (type) {
			case 1:
				ps = conn.prepareStatement("SELECT * FROM PWD_USER WHERE ID LIKE ?");
				ps.setString(1, "%" + content + "%");
				break;
			case 2:
				ps = conn.prepareStatement("SELECT * FROM PWD_USER WHERE USERNAME LIKE ?");
				ps.setString(1, "%" + content + "%");
				break;
			case 3:
				ps = conn.prepareStatement("SELECT * FROM PWD_USER WHERE EMAIL LIKE ?");
				ps.setString(1, "%" + content + "%");
				break;
			case 4:
				ps = conn.prepareStatement("SELECT * FROM PWD_USER WHERE QQ LIKE ?");
				ps.setString(1, "%" + content + "%");
				break;
			case 5:
				ps = conn.prepareStatement("SELECT * FROM PWD_USER WHERE SIP LIKE ? OR LIP LIKE ?");
				ps.setString(1, "%" + content + "%");
				ps.setString(2, "%" + content + "%");
				break;
			default:
				break;
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Pwd_user user_ = new Pwd_user(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getInt("qq"), rs.getString("stime"), rs.getString("ltime"), rs.getString("sip"),
						rs.getString("lip"), rs.getString("token"), rs.getInt("status"), rs.getInt("error_num"),
						rs.getString("email_token"), rs.getLong("token_exptime"));
				list.add(user_);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return list;
	}

	public static Pwd_user SelectStatu(int id, String verify) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pwd_user user = null;
		try {
			if (verify != null) {
				ps = conn.prepareStatement("SELECT * FROM Pwd_user WHERE ID = ? AND EMAIL_TOKEN=?");
				ps.setInt(1, id);
				ps.setString(2, verify);
			} else {
				ps = conn.prepareStatement("SELECT * FROM Pwd_user WHERE ID = ?");
				ps.setInt(1, id);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				user = new Pwd_user(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getInt("qq"),
						rs.getString("stime"), rs.getString("ltime"), rs.getString("sip"), rs.getString("lip"),
						rs.getString("token"), rs.getInt("status"), rs.getInt("error_num"), rs.getString("email_token"),
						rs.getLong("token_exptime"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return user;
	}

	public static Pwd_user SelectEmailToken(String verify) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pwd_user user = null;
		try {
			if (verify != null) {
				ps = conn.prepareStatement("SELECT * FROM Pwd_user WHERE status = 0 AND EMAIL_TOKEN=?");
				ps.setString(1, verify);
			} else {
				ps = conn.prepareStatement("SELECT * FROM Pwd_user WHERE status = 0");
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				user = new Pwd_user(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getInt("qq"),
						rs.getString("stime"), rs.getString("ltime"), rs.getString("sip"), rs.getString("lip"),
						rs.getString("token"), rs.getInt("status"), rs.getInt("error_num"), rs.getString("email_token"),
						rs.getLong("token_exptime"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return user;
	}

	public static int UserUpdate(Pwd_user user) {
		String sql = "UPDATE PWD_USER SET USERNAME=?,PASS=?,EMAIL=?,QQ=? WHERE ID=?";
		Object[] pas = { user.getUsername(), user.getPass(), user.getEmail(), user.getQq(), user.getId() };
		return BaseDao.executeUpdate(sql, pas);
	}
	public static int UserPwdUpdate(Pwd_user user) {
		String sql = "UPDATE PWD_USER SET PASS=?,STATUS=? WHERE ID=?";
		Object[] pas = { user.getPass(),user.getStatus(), user.getId() };
		return BaseDao.executeUpdate(sql, pas);
	}

	public static int delete(Pwd_user user) {
		String sql = "DELETE FROM PWD_USER WHERE ID=?";
		Object[] pas = { user.getId() };
		return BaseDao.executeUpdate(sql, pas);
	}

	public static int update(Pwd_user user) {
		String sql = "UPDATE PWD_USER SET ltime = ?,lip=?,TOKEN=? WHERE ID=?";
		Object[] pas = { user.getLtime(), user.getLip(), user.getToken(), user.getId() };
		return BaseDao.executeUpdate(sql, pas);
	}

	public static int updateStatu(Pwd_user user) {
		String sql = "UPDATE PWD_USER SET STATUS = ? ,EMAIL_TOKEN=? ,TOKEN_EXPTIME =? WHERE ID=?";
		Object[] pas = { user.getStatus(), user.getEmail_token(), user.getToken_exptime(), user.getId() };
		return BaseDao.executeUpdate(sql, pas);
	}
}
