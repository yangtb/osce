<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>设备</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var idSkillCase = '${idSkillCase!}';
        var formType = '${formType!}';
    </script>
</head>

<body class="body-my">

<div class="wrapper-content">
    <form class="layui-form" id="skillDeviceForm">
        <div hidden>
            <input name="idSkillDevice" id="idSkillDevice" hidden>
            <input name="idDevice" id="idDevice" hidden>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">设备<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-inline">
                <input type="text" name="naDevice" id="naDevice" lay-verify="required" lay-vertype="tips"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">数量<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-inline">
                <input class="layui-input layui-input-number" min="1" step="1"
                       name="numDevice" autocomplete="off" lay-verify="required" lay-vertype="tips">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addSkillDevice">
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
<script src="${contextPath}/biz/js/biz/training/skill/skillDeviceFormController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

</body>
</html>