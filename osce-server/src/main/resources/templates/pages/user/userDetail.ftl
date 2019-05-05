<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户基本资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var contextPath = '${contextPath}';
    </script>

</head>

<body>
<div class="wrapper-content">
    <form class="layui-form" id="userform">
        <div class="layui-col-md6">
            <div hidden>
                <input name="user_id" hidden>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">账户类型</label>
                    <div class="layui-input-inline">
                        <select name="role_type" lay-verify="required">
                            <option value="1">超级管理员</option>
                            <option value="2" selected=>普通用户</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">登录账号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="user_name" lay-verify="required|username" autocomplete="off"
                               class="layui-input layui-disabled" disabled>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-inline">
                        <input type="text" name="email" lay-verify="required|email" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">真实姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="real_name" lay-verify="required" class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-inline">
                        <select name="sex" lay-verify="required">
                            <option value="1" selected="">男</option>
                            <option value="2">女</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">联系手机</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phone_no" lay-verify="required|phone" class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <input type="checkbox" checked="" name="enabled" lay-skin="switch"
                           lay-filter="userEnabledSwitch" value="true" lay-text="活动|锁定">
                </div>
            </div>

            <div class="layui-form-item form-item-my5">
                <label class="layui-form-label">用户角色</label>
                <div class="layui-input-block">
                    <#list roles as role>
                    <#if (role.level != 0)>
                       <input type="checkbox" name="role" value="${role.roleId}"
                              lay-skin="primary" title="${role.name}"
                              <#if (role.checked == true)>checked</#if>
                    </#if>
                   </#list>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <input type="file" name="userFace" class="layui-upload-file" lay-title="掐指一算，我要换一个头像了">
            <p>由于是纯静态页面，所以只能显示一张随机的图片</p>
            <img src="" class="layui-circle" id="userFace">
        </div>



    </form>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/user/userFormController.js"></script>
<script>
    var formType = '${formType}';
    var publicKey = '${publicKey}';

    function fullForm(data) {
        $(document).ready(function () {
            $("#userform").autofill(data);
            layui.use('form', function () {
                layui.form.render();
            });
        });
    }
</script>

</body>
</html>