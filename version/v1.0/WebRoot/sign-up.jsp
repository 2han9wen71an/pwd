<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="admin/header.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>注册</title>
	</head>

	<body>
		<script src="${pageContext.request.contextPath }/assets/js/theme.js"></script>
		<div class="am-g tpl-g">
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
			<div class="tpl-login">
				<div class="tpl-login-content">
					<a href="${pageContext.request.contextPath }/index.html">
						<div class="tpl-login-logo"></div>
					</a>
					<div class="tpl-login-title">
						注册用户<span style="float: right;"><a href="login.jsp"
						style="color:#999;">登录</a></span>
					</div>
					<span class="tpl-login-content-info"> 创建一个新的用户 </span>
					<style type="text/css">
						#sign-msg small {
							color: red;
						}
					</style>
					<form class="am-form tpl-form-line-form " id="sign-msg" method="post">
						<div class="am-form-group">
							<input type="email" class="tpl-form-input js-pattern-email" id="user-email" placeholder="邮箱" required name="email"> <small id="info"></small>
						</div>

						<div class="am-form-group">
							<input type="text" class="tpl-form-input" id="user-name" placeholder="用户名" required name="username" minlength="3">
							<small id="info"></small>
						</div>

						<div class="am-form-group">
							<input type="password" class="tpl-form-input" id="user-pass" placeholder="请输入密码" required name="pass" minlength="6"> <small id="info"></small>
						</div>

						<div class="am-form-group">
							<input type="password" class="tpl-form-input js-sync-pass" id="user-pass2" placeholder="再次输入密码" required name="pass2" minlength="6"> <small id="info"></small>
						</div>
						<div class="am-form-group">
							<div class="col-sm-10">
								<input type="text" style="width:calc(100% - 82px);float: left;" name="verifycode" class="tpl-form-input" required placeholder="验证码" id="verifycode" /> <img style="width:82px;float: right;" src="${pageContext.request.contextPath }/verifycode?<?=time();?>" onclick="re_code(this)" id="verifycode_img">
							</div>
						</div>

						<div class="am-form-group tpl-login-remember-me">
							<input id="remember-me" type="checkbox" name="read" minchecked="1" required> <label for="remember-me"> 我已阅读并同意 <a
							href="javascript:RegisterProtocol();" style="color:#999;">《用户注册协议》</a>
						</label>

						</div>
						<div class="am-form-group">

							<button type="button" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn" lay-filter="formGet" onclick="login_t()" id="submit" disabled>提交</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script src="${pageContext.request.contextPath }/assets/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath }/assets/js/amazeui.min.js"></script>
		<script src="${pageContext.request.contextPath }/assets/js/app.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/layui/layui.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/layui/css/layui.css">

		<script type="text/javascript">
			$(function() {
				$('#sign-msg').validator({
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

			function login_t() {
				var read = $('#remember-me').val();
				var email = $('#user-email').val();
				var username = $('#user-name').val();
				var pass = $('#user-pass').val();
				var pass2 = $('#user-pass2').val();
				var verifycode = $('#verifycode').val();
				if(pass != pass2) {
					layui.use('layer', function() {
						layer.msg('两次密码不一致')
					});
					return false;
				}
				if(!email && !username && !pass && !pass2 && !verifycode) {
					layui.use('layer', function() {
						layer.msg('所有内容都不能为空')
					});
					return false;
				}
				layui.use('layer', function() {
					layer.msg('请等待', {
						icon: 16,
						shade: 0.01
					});
				});
				$.post('user.do?method=sign_up', {
						email: email,
						username: username,
						pass: pass,
						read: read,
						verifycode: verifycode
					}, function(data) {
						var res = JSON.parse(data);
						if(res['code'] == 200) {
							layui.use('layer', function() {
								layer.open({
									type: 1,
									title: false,
									closeBtn: false,
									area: '300px;',
									shade: 0.8,
									id: 'LAY_layuipro',
									resize: false,
									btn: ['登录', '确认'],
									btnAlign: 'c',
									moveType: 1,
									content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' + res['msg'] + '<br/>请严格遵守《用户注册协议》使用账号，一经发现有违规操作将会禁用您的账号，严重违规者封禁IP<br/>请保存好自己的账号，尽量不要在公共场合登录使用,以免被盗用<br/>现在去登录您的账号吧！</div>',
									success: function(layero) {
										var btn = layero.find('.layui-layer-btn');
										btn.find('.layui-layer-btn0').attr({
											href: 'login.jsp'
										});
									}
								});
							});
							$('#verifycode').val('');
						} else {
							$('#verifycode').val('');
							$('#verifycode_img').click();
							layui.use('layer', function() {
								layer.msg('MSG:' + res['msg'])
							});
						}
					})
					.error(function(res) {
						$('#verifycode').val('');
						$('#verifycode_img').click();
						layui.use('layer', function() {
							layer.msg('ERROR:请求服务器失败')
						});
					})
			}

			function re_code(obj) {
				d = new Date();
				$(obj).attr("src", "verifycode?" + d.getTime());
			}

			function RegisterProtocol() {
				layui.use('layer', function() {
					layer.open({
						type: 2,
						title: '《用户注册协议》',
						shadeClose: true,
						shade: 0.8,
						area: ['380px', '90%'],
						content: 'RegisterProtocol.html'
					});
				});
			}
		</script>
	</body>

</html>