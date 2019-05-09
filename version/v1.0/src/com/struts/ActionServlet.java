package com.struts;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ActionServlet extends HttpServlet {

	// 用来装载配置信息的
	private Map<String, ActionMapping> map = new HashMap<String, ActionMapping>();

	private final String XML_Path = "/WEB-INF/struts.xml";

	// 真正分配服务请求的
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 取得请求路径 项目名/xx.do
		String url = request.getRequestURI();

		// path取出来的就是
		String path = url.substring(url.lastIndexOf("/") + 1, url.indexOf("."));

		// 我们可以在map集合中，根据path这个键，取得配置文件中的对象
		ActionMapping am = map.get(path);
		ActionForm form = null;

		// 当对象不为空，意味着找到配置
		if (am != null) {

			// 给我们的参数填值
			if (am.getName() != null && !"".equals(am.getName())) {

				try {
					// 转乱码
					request.setCharacterEncoding("utf-8");

					// 首先根据配置文件中的name得到类对象
					Class formClass = Class.forName(am.getName());
					// 通过反射，类对象来创建对象
					form = (ActionForm) formClass.newInstance();

					Map<String, String> paramMap = new HashMap<String, String>();

					// 得到请求中所有的参数名
					Enumeration<String> names = request.getParameterNames();
					// 迭代出参数名
					while (names.hasMoreElements()) {
						// 得到参数名
						String name = (String) names.nextElement();

						String[] values = request.getParameterValues(name);

						for (String value : values) {
							paramMap.put(name, value);
						}

					}

					// 将map中的值，放进form对象中
					BeanUtils.populate(form, paramMap);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			try {

				// 通过 action实际文件所在路径 生成出类对象
				Class cls = Class.forName(am.getType());
				// 创建出action对象实例
				Action ac = (Action) cls.newInstance();
				// 调用实例中的执行方法，返回结果
				ActionForward af = ac.execute(request, response, form);
				if (af != null) {
					// 如果用户指定为重定向
					if (af.isRedirect() == true) {

						response.sendRedirect(af.getPath());

					} else {
						// 否则就是转发
						request.getRequestDispatcher(af.getPath()).forward(request, response);

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("没找到");
		}

	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		System.out.println("actionservlet初始化成功");
		// 一开始我们需要做的，就是将配置信息读取进来

		// 解析xml
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		InputStream in = null;
		try {

			// 得到服务器对象 全局对象
			ServletContext appcation = config.getServletContext();

			in = appcation.getResourceAsStream(XML_Path);

			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(in);

			// 得到所有的action节点
			NodeList list = doc.getElementsByTagName("action");

			for (int i = 0; i < list.getLength(); i++) {

				// 取得action
				Element e = (Element) list.item(i);

				String path = e.getAttribute("path");
				String type = e.getAttribute("type");
				String name = e.getAttribute("name");

				// 将取出来的值 装入对象中
				ActionMapping am = new ActionMapping(path, type, name);

				// 将对象放入集合中
				map.put(path, am);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (in != null) {
					in.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
