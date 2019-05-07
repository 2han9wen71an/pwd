package com.dao;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用Dao 一般我们可以在里面定义一些通用的方法 例如增删改
 * 
 * @author hzy
 *
 */
public class CommonDao {

	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	// 新增通用方法
	public int save(Object obj) {

		int i = 0;

		// 得到连接对象
		conn = BaseDao.getConn();

		// 一旦要用到反射，必须得到类对象
		Class cls = obj.getClass();

		StringBuffer sql = new StringBuffer();

		sql.append("insert into " + cls.getSimpleName() + " values(SEQ_" + cls.getSimpleName() + ".nextval,");

		// 得到所有申明的属性 包括了私有化的
		Field[] fs = cls.getDeclaredFields();

		// 循环遍历 组装sql语句
		for (int j = 1; j < fs.length; j++) {
			sql.append("?");

			if (j < fs.length - 1) {
				sql.append(",");
			} else {
				sql.append(")");
			}

		}
		System.out.println("sql语句：" + sql);

		try {
			pst = conn.prepareStatement(sql.toString());

			// 开启设置值权限
			AccessibleObject.setAccessible(fs, true);

			// 我们又要遍历一次属性，这次遍历属性是为了注入值
			for (int j = 1; j < fs.length; j++) {
				pst.setObject(j, fs[j].get(obj));
			}

			i = pst.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			BaseDao.closeAll(conn, pst, rs);

		}

		return i;
	}

	// 删除
	public int delete(Class<?> cls, int id) {

		int i = 0;
		// 得到连接对象
		conn = BaseDao.getConn();

		// 取出id属性 id一般都是第一位
		Field f = cls.getDeclaredFields()[0];

		String sql = "delete from " + cls.getSimpleName() + " where " + f.getName() + "=?";

		try {
			pst = conn.prepareStatement(sql);

			pst.setObject(1, id);

			i = pst.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			BaseDao.closeAll(conn, pst, rs);
		}

		return i;

	}

	// 查询
	public List query(Class<?> cls) {

		List list = new ArrayList();

		// 得到连接对象
		conn = BaseDao.getConn();

		// 生成sql语句
		String sql = "select * from " + cls.getSimpleName();

		try {
			pst = conn.prepareStatement(sql);

			// 执行查询
			rs = pst.executeQuery();

			// 只要进入了循环，证明查到一条数据
			while (rs.next()) {

				// 通过反射，创建出对象
				Object obj = cls.newInstance();

				// 现在对象创建出来，还是个空的，我们需要给里面赋值
				Field[] fs = cls.getDeclaredFields();
				// 开启设置值权限
				AccessibleObject.setAccessible(fs, true);

				for (Field f : fs) {

					Object value = rs.getObject(f.getName());

					// 为什么要判断？是因为oracle中取出来的值是BigDecimal类型，不能直接放入int或double中
					if (value instanceof BigDecimal) {

						BigDecimal value_ = (BigDecimal) value;

						// 根据数字的类型，进行转型
						if (f.getType().getSimpleName().equalsIgnoreCase("int")) {
							value = value_.intValue();
						} else {
							value = value_.doubleValue();
						}

					}
					if (value instanceof java.sql.Timestamp) {
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						value = df.format(value);
					}
					if (value == null) {
						value = "为空";
					}
					f.set(obj, value);

				}

				// 将反射创建出来的对象，放进list集合中
				list.add(obj);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			BaseDao.closeAll(conn, pst, rs);

		}
		return list;
	}
}
