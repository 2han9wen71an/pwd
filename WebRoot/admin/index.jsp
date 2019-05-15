<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
	String title = "首页";
	request.setAttribute("title", title);
%>
<%@include file="header.jsp"%>

<script type="text/javascript">
	$(function() {
		var mail = "${user.email}";
		if (mail.match(/((\d)*)@qq.com/)) {
			$("#achievement").attr("src", "https://q2.qlogo.cn/headimg_dl?dst_uin=" + mail.substr(0, mail.indexOf("@")) + "&spec=100");
		} else {
			$("#achievement").attr("src", "http://www.gravatar.com/avatar/" + hex_md5(mail) + "?s=32");
		}
	});
</script>
<body data-type="index">
	<div class="am-g tpl-g">
		<%@include file="left-nav.jsp"%>
		<!-- 内容区域 -->
		<div class="tpl-content-wrapper">
			<%@include file="breadcrumb.jsp"%>
			<div class="row-content am-cf">
				<div class="row  am-cf">
					<div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
						<div class="widget widget-block am-cf">
							<div class="widget-block-header">今日增加记录</div>
							<div class="widget-statistic-body">
								<div class="widget-statistic-value">${count_day }</div>
								<div class="widget-block-description"></div>
								<span class="widget-statistic-icon am-icon-rocket"></span>
							</div>
						</div>
					</div>
					<div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
						<div class="widget widget-primary am-cf">
							<div class="widget-statistic-header">当前记录总计</div>
							<div class="widget-statistic-body">
								<div class="widget-statistic-value">${count_pwd }</div>
								<div class="widget-statistic-description"></div>
								<span class="widget-statistic-icon am-icon-credit-card-alt"></span>
							</div>
						</div>
					</div>
					<div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
						<div class="widget widget-purple am-cf">
							<div class="widget-statistic-header">当前活跃用户</div>
							<div class="widget-statistic-body">
								<div class="widget-statistic-value">${count_user }</div>
								<div class="widget-statistic-description"></div>
								<span class="widget-statistic-icon am-icon-support"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row am-cf">
					<div
						class="am-u-sm-12 am-u-md-12 am-u-lg-4 widget-margin-bottom-lg ">
						<div class="tpl-user-card am-text-center widget-body-lg">
							<div class="tpl-user-card-title">${user.username }</div>
							<div class="achievement-subheading"></div>
							<img class="achievement-image" src="" alt="${user.username }"
								title="${user.username }" id="achievement">
							<div class="achievement-description">
								上次登录时间：<strong> ${user.ltime } </strong><br /> 上次登录IP： <strong>
									${user.lip } </strong>
							</div>
						</div>
					</div>
					<div class="am-u-sm-12 am-u-md-8">
						<div class="widget am-cf widget-body-lg">
							<div class="widget-head am-cf">
								<div class="widget-title am-fl">公告</div>
								<div class="widget-function am-fr">
									<a href="javascript:;" class="am-icon-cog"></a>
								</div>
							</div>
							<div
								class="widget-body-md widget-body tpl-amendment-echarts am-fr">
								<div id="notice_list"></div>
							</div>
						</div>
					</div>

				</div>
				<div class="row am-cf">
					<div class="am-u-sm-12 am-u-md-12">
						<div class="widget am-cf widget-body-lg">
							<div class="widget-head am-cf">
								<div class="widget-title am-fl">事件</div>
								<div class="widget-function am-fr">
									<a href="javascript:;" class="am-icon-cog"></a>
								</div>
							</div>
							<div class="widget-body  widget-body-lg am-fr">
								<table class="layui-hide layui-bg-black" id="log_list"
									lay-filter="log_list"></table>
							</div>
						</div>
					</div>

				</div>

			</div>
		</div>
	</div>
	<script>
		layui.use('table', function() {
			var table = layui.table,
				form = layui.form;
			table.render({
				skin : 'nob',
				even : false,
				elem : '#log_list',
				url : 'log.do?method=log_list',
				cols : [
					[ {
						type : 'numbers'
					}, {
						field : 'ip',
						title : 'IP'
					}, {
						field : 'time',
						title : '时间'
					}, {
						field : 'record',
						title : '事件',
						sort : true
					} ]
				],
				page : true
			});
		});
		$.ajax({
			url : 'notice.do?method=notice_list',
			type : 'GET',
			dataType : 'json',
		})
			.done(function(res) {
				if (res['code'] == 0) {
					for (var i = 0; i < res['data'].length; i++) {
	
						$('#notice_list').append('<blockquote><p>' + res['data'][i]['content'] + '</p><small>' + res['data'][i]['time'] + '</small></blockquote><br/>');
					//$('#notice_list').append('<p>'+res['data'][i]['time']+' -> '+res['data'][i]['content']+'</p>');
					}
				} else {
					$('#notice_list').append(res['msg']);
				}
	
			})
			.fail(function() {
				$('#notice_list').append('公告获取失败');
			})
	</script>
	<script src="../assets/js/amazeui.min.js"></script>
	<script src="../assets/js/amazeui.datatables.min.js"></script>
	<script src="../assets/js/dataTables.responsive.min.js"></script>
	<script src="../assets/js/app.js"></script>

</body>