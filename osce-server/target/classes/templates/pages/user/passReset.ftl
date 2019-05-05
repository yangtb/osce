<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>密码重置</title>
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

    <form class="layui-form" id="resetPassForm">
        <div hidden>
            <input name="user_id" hidden>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="hidden" name="password" autocomplete="off" id="encryptPassword"/>
                <input type="password" id="clearPassword" lay-verify="required|pass" lay-vertype="tips" autocomplete="off"
                       placeholder="请输入重置密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"></label>
            <div class="layui-form-mid" style="color: #FF5722">8-16位，至少包含1个大写字母、1个小写字母和数字</div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="resetPsw">
                    <i class="iconfont icon-save-copy"></i> 保存
                </button>
            </div>
        </div>
    </form>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/common/js/jquery.jcryption.3.1.0.js"></script>
<script src="${contextPath}/biz/js/user/login/loginController.js"></script>
<script src="${contextPath}/biz/js/user/pass/resetPassFormController.js"></script>

<script>
    function fullForm(data) {
        $(document).ready(function () {
            $("#resetPassForm").autofill(data);
        });
    };

    $(function () {
        var loginController = new LoginController();
        loginController.init(publicKey);
    });
</script>

<script type="text/javascript">
    /*var suggestPsw = randPassword();
    $(".suggestPsw").html(suggestPsw);
    $("#clearPassword").val(suggestPsw);

    function randPassword() {
        var text = ['abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '1234567890', '~!@#$%^&*()_+";",./?<>'];
        var rand = function (min, max) {
            return Math.floor(Math.max(min, Math.random() * (max + 1)));
        }
        var len = rand(8, 16); // 长度为8-16
        var pw = '';
        for (i = 0; i < len; ++i) {
            var strpos = rand(0, 3);
            pw += text[strpos].charAt(rand(0, text[strpos].length));
        }
        return pw;
    }*/
</script>


</body>
</html>