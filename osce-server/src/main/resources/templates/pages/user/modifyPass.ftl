<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var publicKey = '${publicKey}';
        var basePath = '${basePath}';
    </script>
</head>

<body>

<div class="wrapper-content">

    <form class="layui-form" id="modifyPassForm">
        <div class="layui-form-item">
            <label class="layui-form-label">原密码<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="hidden" name="oldPassword" id="encryptOldPassword">
                <input type="password" id="clearOldPassword" lay-verify="required" lay-vertype="tips" autocomplete="off"
                       placeholder="请输入原密码"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">新密码<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="hidden" name="newPassword" id="encryptNewPassword">
                <input type="password" id="clearNewPassword" lay-verify="required|pass" lay-vertype="tips" autocomplete="off"
                       placeholder="请输入新密码"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="password" id="clearNewPassword2" lay-verify="required" lay-vertype="tips" autocomplete="off"
                       placeholder="请再次输入新密码"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="modifyPass">
                    <i class="iconfont icon-save-copy"></i> 保存
                </button>
            </div>
        </div>
    </form>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.jcryption.3.1.0.js"></script>
<script src="${contextPath}/biz/js/user/pass/modifyPassController.js"></script>
<script src="${contextPath}/biz/js/user/pass/modifyPassFormController.js"></script>

<script>
    $(function () {
        var modifyPassController = new ModifyPassController();
        modifyPassController.init(publicKey);
    });
</script>

</body>
</html>