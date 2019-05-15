<%@page import="com.entity.Pwd_user"%>
<%@page import="com.dao.Pwd_userDao"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
	request.setAttribute("title", "密码找回");
	request.setAttribute("type", "reset_pwd");
	String verify = request.getParameter("reset_pwd_verify");
	if (verify != null) {
		long nowtime = new Date().getTime();
		Pwd_user user = Pwd_userDao.SelectEmailToken(verify);
		if (user != null) {
			if (nowtime > user.getToken_exptime()) {
				out.print(
						"<script language='javascript'>alert('您的链接有效期已过，请重新找回密码');window.location.href='./reset_pwd.jsp';</script>");
			} else {
				request.setAttribute("type", "reset_pwd_verify");
			}
		} else {
			out.print(
					"<script language='javascript'>alert('您的链接已失效');window.location.href='./reset_pwd.jsp';</script>");
		}
	}
%>
<%@include file="admin/header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<script src="assets/js/theme.js"></script>
	<div class="am-g tpl-g">
		<!-- 风格切换 -->
		<div class="tpl-skiner">
			<div class="tpl-skiner-toggle am-icon-cog"></div>
			<div class="tpl-skiner-content">
				<div class="tpl-skiner-content-title">选择主题</div>
				<div class="tpl-skiner-content-bar">
					<span class="skiner-colorskiner-white" data-color="theme-white"></span>
					<span class="skiner-color skiner-black" data-color="theme-black"></span>
				</div>
			</div>
		</div>
		<div class="tpl-login">
			<div class="tpl-login-content">
				<a href="/index.html">
					<div class="tpl-login-logo"></div>
				</a>
				<c:if test="${type == 'reset_pwd_verify' }">
					<div class="tpl-login-title">忘记密码</div>
					<span class="tpl-login-content-info">请填写新密码</span>
				</c:if>
				<c:if test="${type==''}">
					<div class="tpl-login-title">忘记密码</div>
					<span class="tpl-login-content-info">使用邮箱或者账号找回密码</span>
				</c:if>
				<style type="text/css">
#reset-msg small {
	color: red;
}
</style>
				<form class="am-form tpl-form-line-form" action="" method="post"
					id="reset-msg">
					<c:if test="${type == 'reset_pwd_verify' }">
						<input type="hidden" name="reset_pwd_verify" id="reset_pwd_verify"
							value="${verify }">
						<div class="am-form-group">
							<input type="text" class="tpl-form-input" id="user-pass"
								placeholder="输入新密码" required name="pass" minlength="6">
							<small id="info"></small>
						</div>
					</c:if>
					<c:if test="${type=='reset_pwd'}">
						<div class="am-form-group">
							<input type="text" class="tpl-form-input" id="user-name"
								placeholder="输入用户名或者邮箱" required name="user" minlength="3">
							<small id="info"></small>
						</div>
					</c:if>

					<div class="am-form-group">
						<div class="col-sm-10">
							<input type="text" style="width:calc(100% - 82px);float: left;"
								name="verifycode" class="tpl-form-input"
								required　placeholder="验证码" minlength="4" id="verifycode" /> <img
								style="width:82px;float: right;"
								src="${pageContext.request.contextPath }/verifycode?<?=time();?>"
								onclick="re_code(this)" id="verifycode_img">
						</div>
						<small id="info"></small>
					</div>
					<div class="am-form-group">
						<div class="am-u-sm-12">
							<div class="tpl-switch" style="text-align: right;">
								<a href="login.jsp" style="color:#999;">登录</a>| <a
									href="sign-up.jsp" style="color:#999;">注册</a>
							</div>
						</div>
					</div>
					<div class="am-form-group">
						<button type="button"
							class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn"
							name="submit" id="submit"
							<c:if test="${type == 'reset_pwd_verify' }">onclick="reset_pwd_verifys();"</c:if>
							<c:if test="${type=='reset_pwd'}">onclick="reset_pwd();"</c:if>>提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#reset-msg').validator({
				onValid: function(validity) {
					$(validity.field).closest('.am-form-group').find('#info').hide();
					$('#submit').removeAttr('disabled');
				},

				onInValid: function(validity) {
					var $field = $(validity.field);
					var $group = $field.closest('.am-form-group');
					var $alert = $group.find('#info');
					var msg = $field.data('validationMessage') || this.getValidationMessage(validity);
					$('#submit').attr('disabled', '');
					$alert.html(msg).show();
				}
			});
		});

		function re_code(obj) {
			d = new Date();
			$(obj).attr("src", "verifycode?" + d.getTime());
		}

		function reset_pwd() {
			var user = $('#user-name').val();
			var verifycode = $('#verifycode').val();
			if(!user && !verifycode) {
				layui.use('layer', function() {
					layer.msg('所有内容都不能为空')
				});
				return false;
			}
			$.post('user.do?method=reset_pwd', {
					username: user,
					verifycode: verifycode
				}, function(data) {
					var res=JSON.parse(data);
					if(res['code'] == 200) {
						layui.use('layer', function() {
							layer.msg(res['msg'])
						});
						$('#verifycode').val('');
					} else {
						layui.use('layer', function() {
							layer.msg(res['msg'])
						});
						$('#verifycode_img').click();
					}
				})
				.error(function(res) {
					layui.use('layer', function() {
						layer.msg('请求服务器失败')
					});
					$('#verifycode_img').click();
				})
		}
		<c:if test="${type == 'reset_pwd_verify' }">
		function getUrlParam(name)
{
var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
var r = window.location.search.substr(1).match(reg);  //匹配目标参数
if (r!=null) return unescape(r[2]); return null; //返回参数值
} 
		function reset_pwd_verifys() {
			var pass = $('#user-pass').val();
			var verifycode = $('#verifycode').val();
			//var reset_pwd_verify = $('#reset_pwd_verify').val();
			var reset_pwd_verify = getUrlParam('reset_pwd_verify');
			if(!pass && !verifycode) {
				layui.use('layer', function() {
					layer.msg('所有内容都不能为空')
				});
				return false;
			}
			$.post('user.do?method=reset_pwd_verify', {
					pass: pass,
					verifycode: verifycode,
					reset_pwd_verify: reset_pwd_verify
				}, function(data) {
					var res=JSON.parse(data);
					if(res['code'] == 200) {
						alert(res['msg']);
						window.location.href = './login.jsp';
					} else {
						layui.use('layer', function() {
							layer.msg(res['msg'])
						});
						$('#verifycode_img').click();
					}
				})
				.error(function(res) {
					layui.use('layer', function() {
						layer.msg('请求服务器失败')
					});
					$('#verifycode_img').click();
				})
		}
		</c:if>
	</script>
	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/app.js"></script>

</body>

</html>