<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="${basePath}/biz/img/logo/favicon.ico" rel="shortcut icon">
    <title>注册</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/register.css">
    <script>
        var basePath = '${basePath}';
        var publicKey = '${publicKey}';
    </script>
</head>
<body>


<div class="platform-user-login platform-user-display-show" id="user-register" style="display: none;">
    <div class="platform-user-login-main">
        <div class="platform-user-login-box platform-user-login-header">
            <h2>${websiteName}</h2>
            <p>上海嘉奕医学科技有限公司</p>
        </div>
        <div class="platform-user-login-box platform-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="platform-user-login-icon layui-icon layui-icon-component"></label>
                <input type="text" name="orgName" id="orgName" lay-verify="required|orgName" lay-vertype="tips"
                       placeholder="机构名称" class="layui-input" autocomplete="off">
            </div>
            <div class="layui-form-item">
                <label class="platform-user-login-icon layui-icon layui-icon-username"></label>
                <input type="text" name="username" id="username" lay-verify="required|username" lay-vertype="tips"
                       placeholder="用户名，由字母、数字、下划线组成且至少3个字符" class="layui-input" autocomplete="off">
            </div>

            <div class="layui-form-item">
                <label class="platform-user-login-icon layui-icon layui-icon-cellphone"></label>
                <input type="text" name="phone" id="phone" lay-verify="required|phone" lay-vertype="tips"
                       placeholder="联系方式" class="layui-input" autocomplete="off">
            </div>

            <div class="layui-form-item">
                <label class="platform-user-login-icon layui-icon layui-icon-engine"></label>
                <input type="text" name="email" id="email" lay-verify="required" lay-vertype="tips"
                       placeholder="邮箱" class="layui-input" autocomplete="off">
            </div>
            <div class="layui-form-item">
                <div class="layui-row">
                    <div class="layui-col-xs7">
                        <label class="platform-user-login-icon layui-icon layui-icon-vercode"></label>
                        <input type="text" name="photoVercode" id="photoVercode"
                               lay-verify="required|photoVercode" lay-vertype="tips"
                               placeholder="图片验证码" class="layui-input" autocomplete="off">
                    </div>
                    <div class="layui-col-xs5">
                        <div style="margin-left: 10px;">
                            <image id="vercodeImage" style="width: 100%;height: 37px"
                                   src="${basePath}/login/verificationCode"
                                   title="点击图片更换一张 "/>
                        </div>
                    </div>
                </div>
            </div>
            #if(${sendSwitch} == 'Y')
                <div class="layui-form-item" >
                    <div class="layui-row">
                        <div class="layui-col-xs7">
                            <label class="platform-user-login-icon layui-icon layui-icon-vercode"></label>
                            <input type="text" name="emailVercode" id="emailVercode"
                                   lay-verify="required|emailVercode" lay-vertype="tips"
                                   placeholder="邮箱验证码" class="layui-input" autocomplete="off">
                        </div>
                        <div class="layui-col-xs5">
                            <div style="margin-left: 10px;">
                                <button type="button" class="layui-btn layui-btn-primary layui-btn-fluid"
                                        id="getEmailCode">获取验证码
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            #end
            <div class="layui-form-item">
                <label class="platform-user-login-icon layui-icon layui-icon-password"></label>
                <input type="hidden" name="password" id="encryptPassword">
                <input type="password" id="clearPassword" name="clearPassword"
                       lay-verify="required|pass" lay-vertype="tips"
                       autocomplete="off" placeholder="密码8-16位，需包含1个大写和小写字母和1个数字"
                       class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="platform-user-login-icon layui-icon layui-icon-password"></label>
                <input type="password" id="clearPassword2" name="clearPassword2"
                       lay-verify="required|pass" lay-vertype="tips"
                       autocomplete="off" placeholder="确认密码" class="layui-input">
            </div>

            <div class="layui-form-item">
                <input type="checkbox" name="agreement" lay-skin="primary"
                       autocomplete="off" title="同意用户协议" checked>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="user-register-submit">注 册</button>
            </div>
            <div class="layui-trans layui-form-item platform-user-login-other">
                <a href="${contextPath}/login" class="platform-user-jump-change platform-link layui-hide-xs">已有帐号登入</a>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.all.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.jcryption.3.1.0.js"></script>
<script src="${contextPath}/biz/js/user/register/registerFormController.js"></script>
<script src="${contextPath}/biz/js/user/register/registerController.js"></script>
<script>
    $(function () {
        var registerController = new RegisterController();
        registerController.init(publicKey);
    });
</script>
</body>
</html>