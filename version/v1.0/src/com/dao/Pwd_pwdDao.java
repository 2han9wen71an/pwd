package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Pwd_pwd;
import com.util.McryptUtil;
import com.util.PageUtil;

public class Pwd_pwdDao {
	/**
	 * 全部查询
	 * 
	 * @param int
	 *            id 密码id
	 * @param int
	 *            uid 用户id
	 * @param int
	 *            type 密码类型
	 * @param String
	 *            content 内容
	 * @param PageUtil
	 *            page 分页
	 * @return list 搜索集合
	 */
	public static List SelectAll(int id, int uid, int type, String content, PageUtil page) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			if (type != 0 && content != null) {
				switch (type) {
				case 1:
					ps = conn.prepareStatement("SELECT * FROM PWD_PWD WHERE TITLE LIKE ?");
					ps.setString(1, "%" + content + "%");
					break;
				case 2:
					ps = conn.prepareStatement("SELECT * FROM PWD_PWD WHERE USERNAME LIKE ?");
					ps.setString(1, "%" + content + "%");
					break;
				case 3:
					ps = conn.prepareStatement("SELECT * FROM PWD_PWD WHERE WEBURL LIKE ?");
					ps.setString(1, "%" + content + "%");
					break;
				default:
					break;
				}
			} else if (id != 0 && uid != 0) {
				ps = conn.prepareStatement("SELECT * FROM PWD_PWD WHERE ID=? AND USERID=?");
				ps.setInt(1, id);
				ps.setInt(2, uid);
			} else if (uid != 0) {
				if (uid == 1) {
					ps = conn.prepareStatement("SELECT * FROM PWD_PWD");
				} else {
					ps = conn.prepareStatement("SELECT * FROM PWD_PWD WHERE USERID=?");
					ps.setInt(1, uid);
				}
			} else {
				ps = conn.prepareStatement("SELECT * FROM PWD_PWD");
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
				Pwd_pwd pwd = null;
				// 判断用户是否设置二代密码
				if (rs.getInt("type") == 0) {
					pwd = new Pwd_pwd(rs.getInt("id"), rs.getInt("userid"), rs.getString("title"),
							rs.getString("descr"), rs.getString("username"), rs.getString("pass"),
							rs.getString("weburl"), rs.getString("intime"), rs.getString("lasttime"),
							rs.getString("tpass"), rs.getInt("status"), rs.getInt("type"),
							McryptUtil.decode(rs.getString("pass"), rs.getString("tpass")));
				} else {
					pwd = new Pwd_pwd(rs.getInt("id"), rs.getInt("userid"), rs.getString("title"),
							rs.getString("descr"), rs.getString("username"), rs.getString("pass"),
							rs.getString("weburl"), rs.getString("intime"), rs.getString("lasttime"),
							rs.getString("tpass"), rs.getInt("status"), rs.getInt("type"));
				}
				list.add(pwd);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return list;
	}

	/**
	 * 条件查询
	 * 
	 * @param int
	 *            id 密码id
	 * @param int
	 *            uid 用户id
	 * @param int
	 *            type 密码类型
	 * @param String
	 *            content 内容
	 * @param PageUtil
	 *            page 分页
	 * @return list 搜索集合
	 */
	public static List Select(int id, int uid, int type, String content, PageUtil page) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			if (type != 0 && content != null) {
				switch (type) {
				case 1:
					ps = conn.prepareStatement("SELECT * FROM PWD_PWD WHERE TITLE LIKE ?");
					ps.setString(1, "%" + content + "%");
					break;
				case 2:
					ps = conn.prepareStatement("SELECT * FROM PWD_PWD WHERE USERNAME LIKE ?");
					ps.setString(1, "%" + content + "%");
					break;
				case 3:
					ps = conn.prepareStatement("SELECT * FROM PWD_PWD WHERE WEBURL LIKE ?");
					ps.setString(1, "%" + content + "%");
					break;
				default:
					break;
				}
			} else if (id != 0 && uid != 0) {
				ps = conn.prepareStatement("SELECT * FROM PWD_PWD WHERE ID=? AND USERID=?");
				ps.setInt(1, id);
				ps.setInt(2, uid);
			} else if (uid != 0) {
				if (uid == 1) {
					ps = conn.prepareStatement("SELECT * FROM PWD_PWD");
				} else {
					ps = conn.prepareStatement("SELECT * FROM PWD_PWD WHERE USERID=?");
					ps.setInt(1, uid);
				}
			} else {
				ps = conn.prepareStatement("SELECT * FROM PWD_PWD");
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
				Pwd_pwd pwd = null;
				// 判断用户是否设置二代密码
				if (rs.getInt("type") == 0) {
					pwd = new Pwd_pwd(rs.getInt("id"), rs.getInt("userid"), rs.getString("title"),
							rs.getString("descr"), rs.getString("username"), rs.getString("weburl"),
							rs.getString("intime"), rs.getString("lasttime"), rs.getInt("status"), rs.getInt("type"),
							McryptUtil.decode(rs.getString("pass"), rs.getString("tpass")));
				} else {
					pwd = new Pwd_pwd(rs.getInt("id"), rs.getInt("userid"), rs.getString("title"),
							rs.getString("descr"), rs.getString("username"), rs.getString("weburl"),
							rs.getString("intime"), rs.getString("lasttime"), rs.getInt("status"), rs.getInt("type"));
				}
				list.add(pwd);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return list;
	}

	public static int Select(int id) {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		try {
			if (id == 1) {
				ps = conn.prepareStatement(
						"select COUNT(*) from PWD_PWD where trunc(to_date(lasttime,'yyyy-mm-dd hh24:mi:ss')) = trunc(sysdate)");
			} else {
				ps = conn.prepareStatement(
						"select COUNT(*) from PWD_PWD where trunc(to_date(lasttime,'yyyy-mm-dd hh24:mi:ss')) = trunc(sysdate) and userid=?");
				ps.setInt(1, id);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return i;
	}

	public static int insert(Pwd_pwd pwd) {
		String sql = "insert into Pwd_pwd values(SEQ_Pwd_pwd.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] pas = { pwd.getUserid(), pwd.getTitle(), pwd.getDescr(), pwd.getUsername(), pwd.getPass(),
				pwd.getWeburl(), pwd.getIntime(), pwd.getLasttime(), pwd.getTpass(), pwd.getStatus(), pwd.getType() };
		return BaseDao.executeUpdate(sql, pas);
	}

	public static int delete(Pwd_pwd pwd) {
		String sql = "DELETE FROM PWD_PWD WHERE ID=? AND USERID=?";
		Object[] pas = { pwd.getId(), pwd.getUserid() };
		return BaseDao.executeUpdate(sql, pas);
	}

	public static int update(Pwd_pwd pwd) {
		String sql = "UPDATE PWD_PWD SET TITLE = ?,DESCR=?,USERNAME=?,PASS=?,WEBURL=?,LASTTIME=?,TPASS=?,TYPE=? WHERE ID=?";
		Object[] pas = { pwd.getTitle(), pwd.getDescr(), pwd.getUsername(), pwd.getPass(), pwd.getWeburl(),
				pwd.getLasttime(), pwd.getTpass(), pwd.getType(), pwd.getId() };
		return BaseDao.executeUpdate(sql, pas);
	}

}
