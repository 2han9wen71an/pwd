<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../assets/js/theme.js"></script>

<!-- 头部 -->
<header>
	<%@include file="header-nav.jsp"%>
	<script type="text/javascript">
		$(function() {
			var mail = "${user.email}";
			if (mail.match(/((\d)*)@qq.com/)) {
				$("#avatar").attr("src", "https://q2.qlogo.cn/headimg_dl?dst_uin=" + mail.substr(0, mail.indexOf("@")) + "&spec=100");
			} else {
				$("#avatar").attr("src", "http://www.gravatar.com/avatar/" + hex_md5(mail) + "?s=32");
			}
		});
	</script>
</header>
<!-- 风格切换 -->
<div class="tpl-skiner">
	<div class="tpl-skiner-toggle am-icon-cog"></div>
	<div class="tpl-skiner-content">
		<div class="tpl-skiner-content-title">选择主题</div>
		<div class="tpl-skiner-content-bar">
			<span class="skiner-color skiner-white" data-color="theme-white"></span>
			<span class="skiner-color skiner-black" data-color="theme-black"></span>
		</div>
	</div>
</div>
<!-- 侧边导航栏 -->
<div class="left-sidebar">
	<!-- 用户信息 -->
	<div class="tpl-sidebar-user-panel">
		<div class="tpl-user-panel-slide-toggleable">
			<div class="tpl-user-panel-profile-picture">

				<img src="" alt="${user.username }" title="${user.username }"
					id="avatar">
			</div>
			<span class="user-panel-logged-in-text"> <i
				class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
				${user.username }
			</span> <a href="set_user.jsp" class="tpl-user-panel-action-link"> <span
				class="am-icon-pencil"></span> 账号设置
			</a>
		</div>
	</div>

	<!-- 菜单 -->
	<ul class="sidebar-nav">
		<li class="sidebar-nav-link"><a href="index.jsp" id="index_nav">
				<i class="am-icon-home sidebar-nav-link-logo"></i> 首页
		</a></li>
		<li class="sidebar-nav-link"><a href="pwd_list.jsp"
			id="pwd_list_nav"> <i class="am-icon-edit sidebar-nav-link-logo"></i>
				密码本
		</a></li>
		<li class="sidebar-nav-link"><a href="rand_key.jsp"
			id="rand_key_nav"> <i
				class="am-icon-envira sidebar-nav-link-logo"></i> 密码生成
		</a></li>

		<li class="sidebar-nav-link"><a href="remind.jsp" id="remind_nav">
				<i class="am-icon-clock-o sidebar-nav-link-logo"></i> 预约提醒
		</a></li>
		<li class="sidebar-nav-link"><a href="notepad.jsp"
			id="notepad_nav"> <i
				class="am-icon-sticky-note-o sidebar-nav-link-logo"></i>
				备忘录
		</a></li>

		<c:if test="${admin!=null }">
			<li class="sidebar-nav-link"><a href="javascript:;"
				class="sidebar-nav-sub-title"> <i
					class="am-icon-table sidebar-nav-link-logo"></i> 管理员操作 <span
					class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
			</a>
				<ul class="sidebar-nav sidebar-nav-sub">
					<li class="sidebar-nav-link"><a href="userlist.jsp"
						id="userlist_nav"> <span
							class="am-icon-angle-right sidebar-nav-link-logo"></span> 用户列表
					</a></li>
					<li class="sidebar-nav-link"><a href="set.do?method=SelectAll"
						id="setting_nav"> <span
							class="am-icon-angle-right sidebar-nav-link-logo"></span> 网站配置
					</a></li>
					<li class="sidebar-nav-link"><a href="notice.jsp"
						id="notice_nav"> <i
							class="am-icon-angle-right sidebar-nav-link-logo"></i> 网站公告
					</a></li>
				</ul></li>
		</c:if>
	</ul>
</div>