package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Pwd_notepad;
import com.util.PageUtil;

public class Pwd_notepadDao {
	public static List SelectAll() {
		Connection conn = BaseDao.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			ps = conn.prepareStatement("SELECT * FROM PWD_NOTEPAD WHERE TYPE>=0");
			rs = ps.executeQuery();
			while (rs.next()) {
				Pwd_notepad notepad = new Pwd_notepad(rs.getInt("id"), rs.getInt("userid"), rs.getString("title"),
						rs.getString("content"), rs.getString("stime"), rs.getInt("type"));
				list.add(notepad);
			}

		} catch (Exception e) {
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
					ps = conn.prepareStatement("SELECT * FROM PWD_NOTEPAD WHERE TITLE LIKE ?");
					ps.setString(1, "%" + content + "%");
					break;
				case 2:
					ps = conn.prepareStatement("SELECT * FROM PWD_NOTEPAD WHERE CONTENT LIKE ?");
					ps.setString(1, "%" + content + "%");
					break;
				default:
					break;
				}
			} else if (uid != 0) {
				ps = conn.prepareStatement("SELECT * FROM PWD_NOTEPAD WHERE USERID=? AND TYPE>=0",
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// 设置PreparedStatement的ResultSet为可滚动结果集););
				ps.setInt(1, uid);
			} else {
				ps = conn.prepareStatement("SELECT * FROM PWD_NOTEPAD");
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
				Pwd_notepad notepad = new Pwd_notepad(rs.getInt("id"), rs.getInt("userid"), rs.getString("title"),
						rs.getString("content"), rs.getString("stime"), rs.getInt("type"));
				list.add(notepad);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, null);
		}
		return list;
	}

	public static int delete(Pwd_notepad note) {
		String sql = "DELETE FROM PWD_notepad WHERE ID=? AND USERID=?";
		Object[] pas = { note.getId(), note.getUserid() };
		return BaseDao.executeUpdate(sql, pas);
	}

	public static int update(Pwd_notepad note) {
		String sql = "UPDATE PWD_NOTEPAD SET TYPE = ? WHERE ID=? AND USERID=?";
		Object[] pas = { note.getType(), note.getId(), note.getUserid() };
		return BaseDao.executeUpdate(sql, pas);
	}
}
