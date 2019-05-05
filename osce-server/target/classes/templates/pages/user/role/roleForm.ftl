<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${formType}用户</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var contextPath = '${contextPath}';
        var formType = '${formType}';
    </script>
</head>

<body class="body-my">
<div class="wrapper-content">
    <form class="layui-form" id="roleform">
        <div hidden>
            <input name="roleId" hidden>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="required|name" lay-vertype="tips" autocomplete="off" class="layui-input"
                       placeholder="请输入角色名称">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色编码<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="code" lay-verify="required|code" lay-vertype="tips" autocomplete="off" class="layui-input"
                       placeholder="请输入角色编码">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色级别<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="level" lay-verify="required|level" lay-vertype="tips" autocomplete="off" class="layui-input"
                       placeholder="角色级别, 从1递增权限逐次减小">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">角色描述<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <textarea name="resume" lay-verify="required|resume" lay-vertype="tips" class="layui-textarea"
                          placeholder="请输入角色描述"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addRole">
                    <i class="iconfont icon-save-copy"></i> 保存
                </button>
                <#if formType == 'add'>
                    <button type="reset" class="layui-btn layui-btn-danger">
                        <i class="iconfont icon-reset"></i> 重新填写
                    </button>
                </#if>
            </div>
        </div>
    </form>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/user/role/roleFormController.js"></script>
<script>
    function fullForm(data) {
        $(document).ready(function () {
            $("#roleform").autofill(data);
        });

    }
</script>

</body>
</html>